package apka;

import java.util.TimerTask;

import org.springframework.web.client.RestTemplate;

public class CheckModules extends TimerTask {

	public void run() {
		RestTemplate rest = new RestTemplate();
		for (Client client : Configuration.getInstance().getCfg()) {
			try {
				rest.postForObject(client.getIp() + ":" + client.getPort(), null, String.class);
			} catch (Exception e) {
				Configuration.getInstance().deleteClient(client.getId());
			}
		}
	}
}
