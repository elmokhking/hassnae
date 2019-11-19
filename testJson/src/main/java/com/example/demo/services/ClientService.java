package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Client;

public interface ClientService {

	public Client getClient(Long id);

	public List<Client> getAllClient();

}
