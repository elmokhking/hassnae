package com.example.clientApp.controllers;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.websocket.server.PathParam;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.example.clientApp.models.Client;
import com.google.gson.Gson;


@Controller
public class ClientController {
	
	private final static String MY_URL="http://localhost:8080/";
	private final static String MY_URL2="http://localhost:8080";
	private static String readUrl(String urlString) throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	}
	
	@RequestMapping("/findStudent")
	public String getClient(@PathParam("id")Long id,Model model) {
		Client client=new Client();
		String url=MY_URL+id;
		RestTemplate restTemplate = new RestTemplate();
		String resp=restTemplate.getForObject(url, String.class);
        JSONObject json = new JSONObject(resp);
        client.setId(json.getLong("id"));
        client.setName(json.getString("name"));
        client.setNomAgence(json.getString("nomAgence"));
        client.setRib(json.getString("rib"));
        model.addAttribute("client",client);
        return "hello.html";   
	}
	
	@RequestMapping("/showAll")
	public String getClient(Model model) {
		String url=MY_URL2;
		try {
			String json=readUrl(url);
			Gson gson=new Gson();
			Client[] clients=gson.fromJson(json,Client[].class);
			model.addAttribute("clients",clients);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "allClient.html";
	}
	
	@RequestMapping("/")
	public String helloPage() {
		return "pageAcceuil";
	}
}
