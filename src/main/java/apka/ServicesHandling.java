package apka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public class ServicesHandling extends TimerTask {

	@Value("${gateway.ip}")
	String ip;
	@Value("${gateway.port}")
	String port;
	@Value("${gateway.request}")
	String urlRequest;
	String url = "http://"+ip+":"+port+"/"+urlRequest;
	private ArrayList<GetAllData> getAllDataArrayList = new ArrayList<GetAllData>();

	public void run() {
		RestTemplate rest = new RestTemplate();
		for (Client client : Configuration.getInstance().getCfg()) {
			if (client.getName().equals("GPS")){
				String getAllData_message = "{\"service\": \""+client.getName()+"\", \"method\": \""+client.getMethod()+"}";
				getAllDataArrayList.add(rest.postForObject(url, getAllData_message, GetAllData.class));
			}
			if (client.getName().equals("external")){
				GetAllData lastAllDataInfo = getAllDataArrayList.remove(getAllDataArrayList.size()-1);
				String uri = client.getIp() + ":" + client.getPort()+"/real_data/{lat}/{lon}/{line}/{ts}/{id}";
				Map<String,String> params = new HashMap<String,String>();
				params.put("lat", String.valueOf(lastAllDataInfo.getPos().getLatitude()));
				params.put("lon", String.valueOf(lastAllDataInfo.getPos().getLongitude()));
				//TODO
				params.put("line", "19");
				params.put("ts", lastAllDataInfo.getTimestamp());
				//TODO
				params.put("id", "1");
				rest.getForEntity(uri, null, params);
			}
		}

	}
}
