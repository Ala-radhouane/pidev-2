package com.example.pi2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pi2.model.Client;
import com.example.pi2.repository.ClientRepository;



@Service
public class ClientService {

	@Autowired
	ClientRepository cRepository;

	
	public List<Client> retrieveAllClients() {
		// TODO Auto-generated method stub
		return (List<Client>) cRepository.findAll();
	}

	
	public Client addClient(Client c) {
		// TODO Auto-generated method stub
		return cRepository.save(c);
	}

	
	public void deleteClient(Long id) {
		// TODO Auto-generated method stub
		cRepository.deleteById((long) id);
	}

	
	public Client updateClient(Client c) {
		// TODO Auto-generated method stub
		return cRepository.save(c);
	}

	
	public Client retrieveClient(String id) {
		// TODO Auto-generated method stub
		return cRepository.findById(Long.parseLong(id)).orElse(null);
	}
	
	
}
