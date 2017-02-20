package apka.services;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import apka.Client;
import apka.Configuration;

@Service
public class MethodsService {

	static Logger log = Logger.getLogger(MethodsService.class.getName());
	
	public String methods(String name) {
		for (Client client : Configuration.getInstance().getCfg()) {
			if (client.getName().equals(name)) {
				try {
					String result = "{\"methods\":" + new ObjectMapper().writeValueAsString(client.getMethods()) + "}";
					log.info("got result: " + result);
					return result;
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			}
		}
		return "SERVICE NOT FOUND";
	}
}
