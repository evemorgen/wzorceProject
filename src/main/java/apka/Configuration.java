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

	private List<Service> cfg = new ArrayList<Service>();

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
					TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, Service.class));

		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}
	}

	public List<Service> getCfg() {
		return cfg;
	}

	public void saveJson() {
		try {
			new ObjectMapper().writeValue(new File(FILENAME), this.cfg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
