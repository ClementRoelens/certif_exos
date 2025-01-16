package org.example.clientservice.service;

import org.example.clientservice.entity.Client;
import org.example.clientservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client getClientById(int id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClientById(int id) {
        clientRepository.deleteById(id);
    }
}
