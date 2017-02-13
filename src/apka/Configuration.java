package apka;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;

public class Configuration {

    private static Configuration instance = null;

    /**
     * FILENAME is the file location of the configuration JSON file
     */
    public static final String FILENAME = "src/apka/config.json";

    /**
     * LOGGER is an instance of the Logger class so that we can do proper
     * logging
     */
    private static final Logger LOGGER = Logger.getLogger(Configuration.class.getName());

    /**
     * Get the Instance of this class There should only ever be one instance of
     * this class and other classes can use this static method to retrieve the
     * instance
     *
     * @return Configuration the stored Instance of this class
     */
    public static Configuration getInstance() {
        if (Configuration.instance == null) {
            Configuration.instance = new Configuration();
        }

        return Configuration.instance;
    }

    /**
     * The loaded JSON Config Object, This should be a key-value pair of options
     */
    private JSONObject config = null;

// TODO zmienne do configu
    private Integer gpsPort;
    private Integer gprsPort;
    private String gpsIP;
    private String gprsIP;

    /**
     * Constructor This is private so it cannot be instantiated by anyone other
     * than this class Try and load the current Config, if no config was found,
     * try to create a new one
     */
    private Configuration() {
        boolean result = this.loadConfig(FILENAME);

        if (!result) {
            if (!this.createConfig(FILENAME)) {
                System.exit(0); //Catastrophic
            }
        }
    }

    /**
     * Load the Configuration specified at fileName
     *
     * @param fileName
     * @return boolean did this load succeed?
     */
    private boolean loadConfig(String fileName) {
        String line;
        String content = "";

        try {
            /**
             * Loop across all of the lines of the files to get the entire file
             */
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            while ((line = reader.readLine()) != null) {
                content += line;
            }
            reader.close();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            return false;
        }
        /**
         * IF there is no content, the file must not be valid
         */
        if (content.length() <= 0) {
        	System.out.println("ELO");
            return false;
        }

        /**
         * Parse the JSON object and set the necessary class attributes
         */
        this.config = new JSONObject(content);

        this.gpsPort = this.config.getInt("gpsPort");
        this.gprsPort = this.config.getInt("gprsPort");
        this.gpsIP = this.config.getString("gpsIP");
        this.gprsIP = this.config.getString("gprsIP");

        return true;
    }

    /**
     * Create a brand new config file with some default values
     *
     * @param fileName
     * @return
     */
    private boolean createConfig(String fileName) {
        this.gpsIP = "0.0.0.0";
        this.gpsPort = 1911;
        this.gprsIP = "0.0.0.1";
        this.gprsPort = 9111;

        return this.saveConfig(fileName);
    }

    /**
     * Save the current Configuration values out to a file to be retrieved in in
     * the future
     *
     * @param fileName
     * @return
     */
    private boolean saveConfig(String fileName) {
        try {
            PrintWriter configFile = new PrintWriter(fileName, "UTF-8");

            /**
             * Create a JSON Object that we can stringify and save
             */
            JSONObject json = new JSONObject();
            json.put("gpsIP", this.gpsIP);
            json.put("gprsIP", this.gprsIP);
            json.put("gpsPort", this.gpsPort);
            json.put("gprsPort", this.gprsPort);

            LOGGER.log(Level.INFO, null, json.toString());

            configFile.write(json.toString());

            configFile.close();

        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }

    /**
     * Refresh Interval Getter
     *
     * @return Integer Refresh Interval
     */
    public Integer getGpsPort() {
        return gpsPort;
    }

    /**
     * Refresh Interval Setter
     *
     * @param refreshInterval
     */
    public void setGpsPort(Integer gpsPort) {
        this.gpsPort = gpsPort;
        this.saveConfig(FILENAME);
    }

    /**
     * Content Limit Getter
     *
     * @return Integer Content Limit
     */
    public Integer getGprsPort() {
        return gprsPort;
    }

    /**
     * Content Limit Setter
     *
     * @param contentLimit
     */
    public void setGprsPort(Integer gprsPort) {
        this.gprsPort = gprsPort;
        this.saveConfig(FILENAME);
    }

    /**
     * Sort Attribute Getter
     *
     * @return String Sort Attribute
     */
    public String getGpsIP() {
        return gpsIP;
    }

    /**
     * Sort Attribute Setter
     *
     * @param sortAttribute
     */
    public void setGpsIP(String gpsIP) {
        this.gpsIP = gpsIP;
        this.saveConfig(FILENAME);
    }

    /**
     * Sort Direction Getter
     *
     * @return String Sort Direction
     */
    public String getGprsIP() {
        return gprsIP;
    }

    /**
     * Sort Direction Setter
     *
     * @param sortDirection
     */
    public void setGprsIP(String gprsIP) {
        this.gprsIP = gprsIP;
        this.saveConfig(FILENAME);
    }
}
