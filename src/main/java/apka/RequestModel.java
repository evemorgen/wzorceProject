package apka;

import java.util.Map;

public class RequestModel {

	String service;
	String method;
	Map<String, Integer> data;
	
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Map<String, Integer> getData() {
		return data;
	}
	public void setData(Map<String, Integer> data) {
		this.data = data;
	}
}
