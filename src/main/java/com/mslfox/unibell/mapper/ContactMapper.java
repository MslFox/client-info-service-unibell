package com.mslfox.unibell.mapper;

import com.mslfox.unibell.dto.ContactDTO;
import com.mslfox.unibell.dto.ContactWithIdDTO;
import com.mslfox.unibell.entities.ContactEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    List<ContactDTO> contactEntitiesToContactDTOs(List<ContactEntity> contactEntities);
    @Mapping(target = "clientEntity", ignore = true)
    ContactEntity contactWithIdDTOToContactEntity(ContactWithIdDTO contactWithIdDTO);
}
