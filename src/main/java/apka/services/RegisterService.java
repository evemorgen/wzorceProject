package apka.services;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import apka.Configuration;

@Service
public class RegisterService {
	
	private HashMap<String, Object> data;
	
	public String register(String registrationTO){
		try {
			data = new ObjectMapper().readValue(registrationTO, HashMap.class);
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		for(String key : data.keySet()){
			Configuration.getInstance().setItem(key, data.get(key));
		}
		Configuration.getInstance().saveJson();

		return registrationTO;
	}
}
