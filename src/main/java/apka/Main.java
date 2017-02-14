package apka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
		MediatorHub mediatorHub = new MediatorHub();
		
		JobWorker gps = new JobWorker((String) Configuration.getInstance().getItem("GpsIP"), (Integer) Configuration.getInstance().getItem("GpsPort"), mediatorHub);
		JobWorker gprs = new JobWorker((String) Configuration.getInstance().getItem("GprsIP"), (Integer) Configuration.getInstance().getItem("GprsPort"), mediatorHub);
		
		mediatorHub.addJobWorker(gprs);

		gps.sendMessage();
 
		System.out.println(Configuration.getInstance().getItem("gpsIP"));

	}
}
