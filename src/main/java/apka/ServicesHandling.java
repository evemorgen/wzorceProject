package apka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


public class ServicesHandling extends TimerTask {

	private String ip;
	private String port;
	private String urlRequest;
	private String tramLine;
	private String serverAdress;

	private static int idSurvey = 0;
	static Logger logger = Logger.getLogger(Service.class.getName());
	private ArrayList<GetAllData> getAllDataArrayList = new ArrayList<GetAllData>();

	public ServicesHandling(String ip, String port, String urlRequest, String tramLine, String serverAdress) {
		this.ip = ip;
		this.port = port;
		this.urlRequest = urlRequest;
		this.tramLine = tramLine;
		this.serverAdress = serverAdress;
	}

	public void run() {
		RestTemplate rest = new RestTemplate();
		for (Service service : Configuration.getInstance().getCfg()) {
			if (service.getName().equals("GPS")){
				String url = "http://"+ip+":"+port+"/"+urlRequest;
				String getAllData_message = "{\"service\": \""+ service.getName()+"\", \"method\": \""+ service.getMethod()+"\", \"data\": {}}";
				try {
					getAllDataArrayList.add(rest.postForObject(url, getAllData_message, GetAllData.class));
				} catch (Exception e){
					System.out.println("Problem with GPS: "+e.getMessage());
				}
			}

			if (service.getName().equals("GPRS") && !getAllDataArrayList.isEmpty()){

				String url = "http://"+ip+":"+port+"/"+urlRequest;
				GetAllData lastAllDataInfo = getAllDataArrayList.remove(getAllDataArrayList.size()-1);
				String urlServer = serverAdress+"=lat: "+lastAllDataInfo.getPos().getLatitude()+"lon: "+lastAllDataInfo.getPos().getLongitude()
						+"tram line: "+tramLine+"time stamp: "+lastAllDataInfo.getTimestamp();
				String getToServer = "\"url\": \""+urlServer+"\"";

				String saveData_message = "{\"service\": \""+ service.getName()+"\", \"method\": \""+ service.getMethod()+"\", \"data\": {"+getToServer+"}}";

				try {
					String response = rest.postForObject(url, saveData_message, String.class);
					logger.info("response: "+response);
				} catch (Exception e){
					System.out.println("Problem with GPRS: "+e.getMessage());
				}
			}
		}

	}
}
