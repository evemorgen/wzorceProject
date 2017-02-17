package apka;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by MICHALLL on 17.02.2017.
 */
public class GpsCommunication {

    private final String get_sat_info_message = "{\"method\": \"get_sat_info\"}";
    private final String get_pos_info_message = "{\"method\": \"get_pos_info\"}";
    private final String get_all_data_message = "{\"method\": \"get_all_data\"}";
    private final String get_raw_frames_message = "{\"method\": \"get_raw_frames\",";

    RestTemplate restTemplate = new RestTemplate();


    public Healthcheck healthcheck(String url){
        return restTemplate.postForObject(url, null, Healthcheck.class);
    }

    public GetSatInfo get_sat_info(String url){
        return restTemplate.postForObject(url, get_sat_info_message,GetSatInfo.class);
    }

    public GetPosInfo get_pos_info(String url){
        return restTemplate.postForObject(url, get_pos_info_message,GetPosInfo.class);
    }

    public GetAllData get_all_data(String url){
        return restTemplate.postForObject(url, get_all_data_message,GetAllData.class);
    }

    public List get_raw_frames(int n, String url){
        return restTemplate.postForObject(url, get_raw_frames_message+" \""+n+"\": 10}", List.class);
    }
}
