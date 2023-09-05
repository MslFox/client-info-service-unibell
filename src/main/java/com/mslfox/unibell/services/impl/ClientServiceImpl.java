package com.mslfox.unibell.services.impl;

import com.mslfox.unibell.dto.ClientNameDTO;
import com.mslfox.unibell.mapper.ClientMapper;
import com.mslfox.unibell.repositories.ClientRepository;
import com.mslfox.unibell.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService<ClientNameDTO> {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public List<ClientNameDTO> getClients() {
        final var clientEntities = clientRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        return clientMapper.clientEntitiesToClientNameDTOs(clientEntities);
    }

    @Override
    public ClientNameDTO getClientNameById(long id) {
        final var clientEntity = clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Client not found with id: " + id));
        return clientMapper.clientEntityToClientNameDTO(clientEntity);
    }


    @Override
    public void saveClient(ClientNameDTO clientNameDTO) {
        final var clientEntity = clientMapper.clientNameDTOToClientEntity(clientNameDTO);
        try {
            clientRepository.save(clientEntity);
        } catch (DataIntegrityViolationException exception) {
            final var rootCause = exception.getRootCause();
            if (rootCause instanceof SQLException sqlException) {
                if (sqlException.getSQLState().equals("23505")) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            String.format("Client name:'%s' already exists", clientNameDTO.getName()));
                } else {
                    throw exception;
                }
            }
        }
    }

}
