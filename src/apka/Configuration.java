package apka;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Configuration {

	private static Configuration instance = null;

	public static final String FILENAME = "src/apka/config.json";

	private HashMap<String, Object> cfg;

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

			this.cfg = new ObjectMapper().readValue(new File(fileName), HashMap.class);

		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}
	}

	public Object getItem(String key) {
		return this.cfg.get(key);
	}

	public void setItem(String key, Object value) {
		this.cfg.put(key, value);
	}

	public void saveJson() {
		try {
			new ObjectMapper().writeValue(new File(FILENAME), this.cfg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
