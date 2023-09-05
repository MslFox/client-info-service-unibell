package com.mslfox.unibell.services;

import java.util.List;
import java.util.Optional;

public interface ContactService<ContactDTO, ContactWithIdDTO, ContactType> {
    List<ContactDTO> getContacts(long id, Optional<ContactType> contactType);

    void saveContact(ContactWithIdDTO contactWithIdDTO);
}
