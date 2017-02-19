package apka;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public class Register {

    static Logger logger = Logger.getLogger(Register.class.getName());
    @Value("${gateway.ip}")
    String ip;
    @Value("${gateway.port}")
    String port;
    @Value("${gateway.register}")
    String urlRegister;
    String url = "http://"+ip+":"+port+"/"+urlRegister;
    RestTemplate rest = new RestTemplate();

    public void run(){
        for (Client client : Configuration.getInstance().getCfg()){
            String message = "{\"ip\": \""+client.getIp()+"\", \"port\": "+client.getPort()+", \"name\": "+client.getName()+", \"methods\": [\""+client.getMethod()+"\"]}";
            try {
                String ipService = rest.postForObject(url, message, String.class);
                logger.info("ip: "+ipService);
            } catch (Exception e){
                e.printStackTrace();
                System.exit(0);
            }

        }
    }
}
