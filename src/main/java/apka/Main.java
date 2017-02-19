package apka;

import java.util.Timer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
		Timer timer = new Timer();
		timer.schedule(new ServicesHandling(), 0, 30000);
	}
}
