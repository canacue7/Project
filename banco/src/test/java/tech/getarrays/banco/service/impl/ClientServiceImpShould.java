package tech.getarrays.banco.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tech.getarrays.banco.entity.ClientEntity;
import tech.getarrays.banco.exception.UserNotFoundException;

@SpringBootTest
class ClientServiceImpShould {
	
	@Autowired
	ClientServiceImp clientServiceImp;

	@Test
	void return_list_when_its_called() {
		List<ClientEntity> clients = new ArrayList<>();

		try {
			clients = clientServiceImp.findAllClients();
		}catch(Exception e) {
			System.out.println("entró a catch");
			e.printStackTrace();
		}
		assertTrue(clients != null);
	}
	
	@Test
	void post_list_when_its_called() {
		ClientEntity clients = new ClientEntity();

		try {
			clients = clientServiceImp.addClient(clients);
		}catch(Exception e) {
			System.out.println("entró a catch");
			e.printStackTrace();
		}
		assertNotNull(clients);
	}
	
	void update_client_when_its_called() {
		ClientEntity clients = new ClientEntity();

		try {
			clients = clientServiceImp.updateClient(clients);
		}catch(Exception e) {
			System.out.println("entró a catch");
			e.printStackTrace();
		}
		assertNotNull(clients);
	}
	
	@Test
	void return_client_when_its_called() {
		ClientEntity client = null;
		Long a = (long) 5;
		try {
			client = clientServiceImp.findClientById(a);
		}catch(Exception e) {
			e.printStackTrace();
		}
		assertTrue(client != null);
		
	}
	
//	void delete_client_when_its_called() {
//		void client = null;
//		Long a = (long) 5;
//		try {
//			client = clientServiceImp.deleteClient(a);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		assertTrue(client != null);
//		
//	}
	
	
  //  public void deleteClient(Long id){clientRepo.deleteClientById(id); }

//	
//	public ClientEntity addClient(ClientEntity client){
//        client.setUsuario_codigo(UUID.randomUUID().toString());
//        return clientRepo.save(client);
//    }
	
//    public List<ClientEntity> findAllClients(){
//        return clientRepo.findAll();
//    
//        
//    public ClientEntity findClientById(Long id) throws  Exception{
//        return clientRepo.findClientById(id).orElseThrow(() -> new UserNotFoundException("Client with id: " + id +" was not found"));
//    }    

}
