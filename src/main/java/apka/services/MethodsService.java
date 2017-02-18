package apka.services;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import apka.Client;
import apka.Configuration;

@Service
public class MethodsService {

	public String methods(String name) {
		for (Client client : Configuration.getInstance().getCfg()) {
			if (client.getName().equals(name)) {
				try {
					return "{\"methods\":" + new ObjectMapper().writeValueAsString(client.getMethods()) + "}";
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			}
		}
		return "SERVICE NOT FOUND";
	}
}
