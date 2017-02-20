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
	private static int idSurvey = 0;
	private ArrayList<GetAllData> getAllDataArrayList = new ArrayList<GetAllData>();

	public void run() {
		RestTemplate rest = new RestTemplate();
		for (Service service : Configuration.getInstance().getCfg()) {
			if (service.getName().equals("GPS")){
				String getAllData_message = "{\"service\": \""+ service.getName()+"\", \"method\": \""+ service.getMethod()+"}";
				getAllDataArrayList.add(rest.postForObject(url, getAllData_message, GetAllData.class));
			}
			if (service.getName().equals("external")){
				GetAllData lastAllDataInfo = getAllDataArrayList.remove(getAllDataArrayList.size()-1);
				String uri = service.getIp() + ":" + service.getPort()+"/real_data/{lat}/{lon}/{line}/{ts}/{id}";
				Map<String,String> params = new HashMap<String,String>();
				params.put("lat", String.valueOf(lastAllDataInfo.getPos().getLatitude()));
				params.put("lon", String.valueOf(lastAllDataInfo.getPos().getLongitude()));
				params.put("line", "19");
				params.put("ts", lastAllDataInfo.getTimestamp());
				params.put("id", String.valueOf(idSurvey));
				++idSurvey;
				rest.getForEntity(uri, null, params);
			}
		}

	}
}
