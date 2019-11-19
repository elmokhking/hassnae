package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Client;
import com.example.demo.services.ClientService;

@RestController
public class MainController {

	ClientService clientService;

	public MainController(ClientService clientService) {
		super();
		this.clientService = clientService;
	}

	@RequestMapping("/{id}")
	public Client index(@PathVariable("id") Long id) {
		return clientService.getClient(id);
	}

	@RequestMapping("/")
	public List<Client> getAll() {
		return clientService.getAllClient();
	}

}