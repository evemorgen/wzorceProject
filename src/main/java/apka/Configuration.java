package apka;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class Configuration {

	private static Configuration instance = null;

	public static final String FILENAME = "src/main/java/apka/config.json";

	private List<Client> cfg = new ArrayList<Client>();

	private static final Logger LOGGER = Logger.getLogger(Configuration.class.getName());

	public static Configuration getInstance() {
		if (Configuration.instance == null) {
			Configuration.instance = new Configuration();
		}

		return Configuration.instance;
	}

	private Configuration() {
		this.loadConfig(FILENAME);
	}

	private void loadConfig(String fileName) {
		try {
			cfg = new ObjectMapper().readValue(new File(fileName),
					TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, Client.class));

		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}
	}

	public List<Client> getCfg() {
		return cfg;
	}

	public Client getClient(int index) {
		return this.cfg.get(index);
	}

	public void setClient(Client client) {
		this.cfg.add(client);
	}

	public void deleteClient(String id) {
		this.cfg.remove(this.findIndexById(id));
	}

	public void saveJson() {
		try {
			new ObjectMapper().writeValue(new File(FILENAME), this.cfg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private int findIndexById(String id) {
		for (Client client : cfg) {
			if (client.getId().equals(id))
				return cfg.indexOf(client);
		}
		return -1;
	}

}
