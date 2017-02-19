package apka;

import java.util.ArrayList;
import java.util.TimerTask;

import org.springframework.web.client.RestTemplate;

public class CheckModules extends TimerTask {

	public void run() {
		RestTemplate rest = new RestTemplate();
		ArrayList<Client> toRemove = new ArrayList<>();
		for (Client client : Configuration.getInstance().getCfg()) {
			try {
				System.out.println("SPRAWDZAM");
				rest.postForObject("http://" + client.getIp() + ":" + client.getPort() + "/healthcheck", null, String.class);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("KILLER IS DEAD");
				toRemove.add(client);
			}
		}
		Configuration.getInstance().getCfg().removeAll(toRemove);
	}
}
