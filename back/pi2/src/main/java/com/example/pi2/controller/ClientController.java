package com.example.pi2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.pi2.model.Client;
import com.example.pi2.service.ClientService;



@RestController
public class ClientController {

	@Autowired
	ClientService clientService;

	// http://localhost:8080/retrieve-all-Clients
	@GetMapping("/retrieve-all-Clients")
	@ResponseBody
	public List<Client> getClients() {
		List<Client> list = clientService.retrieveAllClients();
		return list;
	}

	// http://localhost:8080/retrieve-Client/{id}
	@GetMapping("/retrieve-Client/{id}")
	@ResponseBody
	public Client retrieveClient(@PathVariable("id") String id) {
		return clientService.retrieveClient(id);
	}

	// Ajouter : http://localhost:8080/add-Client
	@PostMapping("/add-Client")
	@ResponseBody
	public Client addClient(@RequestBody Client c) {
		Client cat = clientService.addClient(c);
		return cat;
	}

	// http://localhost:8080/remove-Client/{id}
	@DeleteMapping("/remove-Client/{id}")
	@ResponseBody
	public void removeClient(@PathVariable("id") Long id) {
		clientService.deleteClient(id);
	}

	// http://localhost:8080/modify-Client
	@PutMapping("/modify-Client")
	@ResponseBody
	public Client modifyClient(@RequestBody Client c) {
		return clientService.updateClient(c);
	}
	
}
