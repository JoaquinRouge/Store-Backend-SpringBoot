package com.joaquinrouge.bazar.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.joaquinrouge.bazar.model.Client;
import com.joaquinrouge.bazar.repository.IClientRepository;

@Service
public class ClientService implements IClientService{

	@Autowired
	private IClientRepository repository;
	
	@Override
	public List<Client> getAllClients() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public ResponseEntity<?> createClient(Client client) {
		// TODO Auto-generated method stub
		repository.save(client);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(client);
			
	}

	@Override
	public Client getClient(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).orElse(null);
	}

	@Override
	public void deleteClient(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	public ResponseEntity<?> editClient(Long id,String nameSurname,int dni) {
		Client client = this.getClient(id);
		
		if(client != null) {
			client.setNameSurname(nameSurname);
			client.setDni(dni);
			
			repository.save(client);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
}
