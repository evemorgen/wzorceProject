package apka;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by MICHALLL on 17.02.2017.
 */
public class GpsCommunication {

    private String url;
    RestTemplate restTemplate = new RestTemplate();

    public  GpsCommunication(String url){
        this.url = url;
    }

    public Healthcheck healthcheck(String message){
        return restTemplate.postForObject(url, message,Healthcheck.class);
    }

    public GetSatInfo getSatInfo(String message){
        return restTemplate.postForObject(url, message,GetSatInfo.class);
    }

    public GetPosInfo getPosInfo(String message){
        return restTemplate.postForObject(url, message,GetPosInfo.class);
    }

    public GetAllData getAllData(String message){
        return restTemplate.postForObject(url, message,GetAllData.class);
    }

    public List getRawFrames(String message){
        return restTemplate.postForObject(url, message, List.class);
    }
}
