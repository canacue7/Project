package tech.getarrays.banco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.banco.exception.UserNotFoundException;
import tech.getarrays.banco.entity.ClientEntity;
import tech.getarrays.banco.repository.ClientRepo;

import java.util.List;
import java.util.UUID;


public interface ClientService {

    public ClientEntity addClient(ClientEntity client);

    public List<ClientEntity> findAllClients();

    public ClientEntity updateClient(ClientEntity client);



    public ClientEntity findClientById(Long id) throws Exception;

    public boolean deleteClient(Long id);

}

