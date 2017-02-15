package apka.services;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
		client.setId(this.md5());
		Configuration.getInstance().setClient(client);
		Configuration.getInstance().saveJson();
		return "{\"id\":\"" + client.getId() + "\"}";
	}
	
	private String md5(){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			String id = "xyz" + System.currentTimeMillis();
	        byte[] messageDigest = md.digest(id.getBytes());
	        BigInteger number = new BigInteger(1, messageDigest);
	        return number.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
