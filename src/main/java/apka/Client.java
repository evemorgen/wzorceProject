package apka;

import java.util.ArrayList;

public class Client {

	private Integer port;
	private String ip;
	private String name;
	private ArrayList<String> methods;
	
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<String> getMethods() {
		return methods;
	}
	public void setMethods(ArrayList<String> methods) {
		this.methods = methods;
	}
	
}
