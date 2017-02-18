package apka.services;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import apka.Client;
import apka.Configuration;
import apka.RequestModel;

@Service
public class RequestService {

	private String ip;
	private Integer port;
	private String url;
	private String data;
	
	public String request(RequestModel requestModel) {
		for (Client client : Configuration.getInstance().getCfg()) {
			if (client.getName().equals(requestModel.getService())) {
				this.ip = client.getIp();
				this.port = client.getPort();
			}
		}
		try {
			data = new ObjectMapper().writeValueAsString(requestModel.getData());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		url = ip + ":" + port;
		return "{\"url\":\"" + url + "\", \"method\": \"" + requestModel.getMethod() + ", \"data\": " + data + "}";

	}

}
