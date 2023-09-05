package com.mslfox.unibell.mapper;

import com.mslfox.unibell.dto.ClientNameDTO;
import com.mslfox.unibell.entities.ClientEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientEntity clientNameDTOToClientEntity(ClientNameDTO clientNameDTO);

    ClientNameDTO clientEntityToClientNameDTO(ClientEntity clientEntity);

    List<ClientNameDTO> clientEntitiesToClientNameDTOs(List<ClientEntity> clientEntities);
}
