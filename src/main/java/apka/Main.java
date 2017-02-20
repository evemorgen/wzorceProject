package apka;

import java.util.Timer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Main {

	@Value("${gateway.ip}")
	String ip;
	@Value("${gateway.port}")
	String port;
	@Value("${gateway.request}")
	String urlRequest;
	@Value("${gateway.tram}")
	String tramLine;
	@Value("${server.adress}")
	String serverAdress;

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
	@PostConstruct
	public void PostConstruct(){
		Timer timer = new Timer();
		timer.schedule(new ServicesHandling(ip,port,urlRequest,tramLine,serverAdress), 0, 30000);
	}
//	@Bean
//	public RestTemplate restTemplate(RestTemplateBuilder builder){
//		return builder.build();
//	}
}
