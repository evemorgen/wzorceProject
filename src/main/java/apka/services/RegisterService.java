package apka.services;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import apka.Client;
import apka.Configuration;

@Service
public class RegisterService {
	
	private Client client;
	
	public String register(String registrationTO){
		try {
			client = new ObjectMapper().readValue(registrationTO, Client.class);
		}catch (IOException e) {
			e.printStackTrace();
		}

		Configuration.getInstance().setClient(client);
		Configuration.getInstance().saveJson();

		return registrationTO;
	}
}
