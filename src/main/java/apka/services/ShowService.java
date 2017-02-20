package apka.services;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import apka.Client;
import apka.Configuration;

@Service
public class ShowService {

	static Logger log = Logger.getLogger(ShowService.class.getName());
	
	public String show() {

		Set<String> interfaces = new LinkedHashSet<>();
		for (Client client : Configuration.getInstance().getCfg()) {
			interfaces.add(client.getName());
		}

		String s = "";
		try {
			s = new ObjectMapper().writeValueAsString(interfaces);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		log.info("found services: " + s);
		return "{\"services\":" + s + "}";
	}
}
