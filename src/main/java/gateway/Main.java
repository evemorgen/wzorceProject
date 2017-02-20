package gateway;

import java.util.Timer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import gateway.services.CheckModules;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
		Timer timer = new Timer();
		timer.schedule(new CheckModules(), 0, 30000);
	}
}
