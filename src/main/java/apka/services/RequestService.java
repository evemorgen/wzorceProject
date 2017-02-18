package apka.services;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import apka.Client;
import apka.Configuration;
import apka.RequestModel;

@Service
public class RequestService {

        static Logger log = Logger.getLogger(RequestService.class.getName());
	private String ip;
	private Integer port;
	private String url;
	private String data;
        private String payload;
        private RestTemplate restTemplate = new RestTemplate();
        private String result;

	public String request(RequestModel requestModel) {
		for (Client client : Configuration.getInstance().getCfg()) {
			if (client.getName().equals(requestModel.getService())) {
                                log.info(String.format("found service %s on %s:%s", client.getName(), client.getIp(), client.getPort()));
				ip = client.getIp();
				port = client.getPort();
			}
		}
		try {
			data = new ObjectMapper().writeValueAsString(requestModel.getData());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		url = "http://" + ip + ":" + port + "/";
                if(data.equals("{}")) {
                    payload = String.format("{\"method\": \"%s\"}", requestModel.getMethod());
                } else {
                    payload = String.format("{\"method\": \"%s\", %s}", requestModel.getMethod(), data.substring(1, data.length()-1));
                }
                String result = restTemplate.postForObject(url, payload, String.class);
                log.info("got result: " + result);

                return result;
	}

}
