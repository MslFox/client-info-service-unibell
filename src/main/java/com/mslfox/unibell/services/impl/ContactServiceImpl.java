package com.mslfox.unibell.services.impl;

import com.mslfox.unibell.common.enums.ContactType;
import com.mslfox.unibell.dto.ContactDTO;
import com.mslfox.unibell.dto.ContactWithIdDTO;
import com.mslfox.unibell.entities.ContactEntity;
import com.mslfox.unibell.mapper.ContactMapper;
import com.mslfox.unibell.repositories.ClientRepository;
import com.mslfox.unibell.repositories.ContactRepository;
import com.mslfox.unibell.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService<ContactDTO,ContactWithIdDTO, ContactType> {
    private final ClientRepository clientRepository;
    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    @Override
    public List<ContactDTO> getContacts(long clientId, Optional<ContactType> contactType) {
        final List<ContactEntity> contactEntities;
        if (contactType.isPresent()) {
            contactEntities = contactRepository.findAllByTypeAndClientEntityId(contactType.get(), clientId);
        } else {
            contactEntities = contactRepository.findAllByClientEntityId(clientId);
        }
        return contactMapper.contactEntitiesToContactDTOs(contactEntities);
    }

    @Override
    public void saveContact(ContactWithIdDTO contactWithIdDTO) {
        final var clientEntity = clientRepository.findById(contactWithIdDTO.getClientId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Client not found with id: " + contactWithIdDTO.getClientId()));
        final var contactEntity = contactMapper.contactWithIdDTOToContactEntity(contactWithIdDTO);
        contactEntity.setClientEntity(clientEntity);
        try {
            contactRepository.save(contactEntity);
        } catch (DataIntegrityViolationException exception) {
            final var rootCause = exception.getRootCause();
            if (rootCause instanceof SQLException sqlException) {
                if (sqlException.getSQLState().equals("23505")) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            String.format("Contact with client id:'%s' type:'%s' and value:'%s' already exists",
                                    contactWithIdDTO.getClientId(),contactWithIdDTO.getType(), contactWithIdDTO.getValue()));
                } else {
                    throw exception;
                }
            }
        }
    }
}
