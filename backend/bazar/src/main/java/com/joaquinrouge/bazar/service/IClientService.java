package com.joaquinrouge.bazar.service;

import java.util.List;

import com.joaquinrouge.bazar.model.Client;

public interface IClientService {
	public List<Client> getAllClients();
	public void createClient(Client client);
	public Client getClient(Long id);
	public void deleteClient(Long id);
	public void editClient(Client client);
}
