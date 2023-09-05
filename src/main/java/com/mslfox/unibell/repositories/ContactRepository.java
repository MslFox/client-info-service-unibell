package com.mslfox.unibell.repositories;

import com.mslfox.unibell.common.enums.ContactType;
import com.mslfox.unibell.entities.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<ContactEntity, Long> {

    List<ContactEntity> findAllByClientEntityId(long id);

    List<ContactEntity> findAllByTypeAndClientEntityId(ContactType type, long id);
}
