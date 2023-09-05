package com.mslfox.unibell.services;

import java.util.List;

public interface ClientService<ClientNameDTO> {
    List<ClientNameDTO> getClients();

    ClientNameDTO getClientNameById(long id);

    void saveClient(ClientNameDTO clientNameDTO);
}
