package com.joaquinrouge.bazar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public void createClient(Client client) {
		// TODO Auto-generated method stub
		repository.save(client);
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
	public void editClient(Client client) {
		// TODO Auto-generated method stub
		repository.save(client);
	}
}
