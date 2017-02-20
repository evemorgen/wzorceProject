package gateway.services;

import java.util.ArrayList;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import gateway.config.Configuration;
import gateway.model.Client;

public class CheckModules extends TimerTask {

	static Logger log = Logger.getLogger(CheckModules.class.getName());
	
	public void run() {
		RestTemplate rest = new RestTemplate();
		ArrayList<Client> toRemove = new ArrayList<>();
		for (Client client : Configuration.getInstance().getCfg()) {
			try {
				log.info("sprawdzam " + client.getName());
				rest.postForObject("http://" + client.getIp() + ":" + client.getPort() + "/healthcheck", null, String.class);
			} catch (Exception e) {
				e.printStackTrace();
				log.info("usuwam " + client.getName());
				toRemove.add(client);
			}
		}
		Configuration.getInstance().getCfg().removeAll(toRemove);
	}
}
