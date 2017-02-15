package apka.services;

import org.springframework.stereotype.Service;

import apka.Configuration;

@Service
public class UnregisterService {
		
	public String unregister(String id){
		Configuration.getInstance().deleteClient(id);
		Configuration.getInstance().saveJson();
		return "OK";
	}
}
