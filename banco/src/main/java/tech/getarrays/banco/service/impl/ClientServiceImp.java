package tech.getarrays.banco.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.banco.exception.UserNotFoundException;
import tech.getarrays.banco.entity.ClientEntity;
import tech.getarrays.banco.repository.ClientRepo;
import tech.getarrays.banco.service.ClientService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ClientServiceImp implements ClientService {
    private final ClientRepo clientRepo;

    @Autowired
    public ClientServiceImp(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    public ClientEntity addClient(ClientEntity client){
        client.setUsuario_codigo(UUID.randomUUID().toString());
        return clientRepo.save(client);
    }

    public List<ClientEntity> findAllClients(){
        return clientRepo.findAll();
    }

    public ClientEntity updateClient(ClientEntity client){
        return  clientRepo.save(client);
    }



    public ClientEntity findClientById(Long id) throws  Exception{
        return clientRepo.findClientById(id)
        		.orElseThrow(() -> new UserNotFoundException("Client with id: " + id +" was not found"));
    }

    public boolean deleteClient(Long id){clientRepo.deleteClientById(id);
    return true;}

}

