package gateway.services;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import gateway.config.Configuration;
import gateway.model.Client;

@Component
public class CheckModules{

	static Logger log = Logger.getLogger(CheckModules.class.getName());
	
	@Scheduled(fixedRate=30000)
	public void check() {
		RestTemplate rest = new RestTemplate();
		ArrayList<Client> toRemove = new ArrayList<>();
		for (Client client : Configuration.getInstance().getCfg()) {
			try {
				log.info("sprawdzam " + client.getName());
				rest.postForObject("http://" + client.getIp() + ":" + client.getPort() + "/healthcheck", null, String.class);
			} catch (Exception e) {
				log.info("usuwam " + client.getName());
				toRemove.add(client);
			}
		}
		Configuration.getInstance().getCfg().removeAll(toRemove);
		Configuration.getInstance().saveJson();
	}
}
