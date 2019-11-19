package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.models.Client;
import com.example.demo.repositories.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

	ClientRepository clientRepository;

	public ClientServiceImpl(ClientRepository clientRepository) {
		super();
		this.clientRepository = clientRepository;
	}

	@Override
	public Client getClient(Long id) {
		return clientRepository.getOne(id);
	}

	@Override
	public List<Client> getAllClient() {

		return clientRepository.findAll();
	}

}
