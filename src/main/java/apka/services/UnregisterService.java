package apka.services;

import java.util.Map;

import org.springframework.stereotype.Service;

import apka.Configuration;

@Service
public class UnregisterService {
	
	private String id;
	
	public String unregister(Map<String, String> id_json){
		
		id = id_json.get("id");
		Configuration.getInstance().deleteClient(id);
		Configuration.getInstance().saveJson();
		return "OK";
	}
}
