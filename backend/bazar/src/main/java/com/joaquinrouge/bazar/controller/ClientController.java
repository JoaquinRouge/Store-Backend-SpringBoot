package com.joaquinrouge.bazar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.joaquinrouge.bazar.model.Client;

import com.joaquinrouge.bazar.service.IClientService;

@RestController	
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	private IClientService service;
	
	@GetMapping("/all")
	public List<Client> getClients(){
		return service.getAllClients();
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> createClient(@RequestBody Client client) {
	    try {
	        service.createClient(client);
	        return ResponseEntity.status(HttpStatus.CREATED).body(client); // 201
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear cliente");
	    }
	}
	
	@GetMapping("/get/{id}")
	public Client getClient(@PathVariable Long id) {
		return service.getClient(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteClient(@PathVariable Long id) {
		service.deleteClient(id);
	}
	
	@PutMapping("/edit")
	public void editClient(Client client) {
		service.editClient(client);
	}
	
}
