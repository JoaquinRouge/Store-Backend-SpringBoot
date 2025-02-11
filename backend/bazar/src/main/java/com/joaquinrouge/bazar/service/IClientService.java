package com.joaquinrouge.bazar.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.joaquinrouge.bazar.model.Client;

public interface IClientService {
	public List<Client> getAllClients();
	public ResponseEntity<?> createClient(Client client);
	public Client getClient(Long id);
	public void deleteClient(Long id);
	public void editClient(Client client);
}
