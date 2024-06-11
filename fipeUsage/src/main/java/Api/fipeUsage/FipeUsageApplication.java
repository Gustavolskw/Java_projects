package Api.fipeUsage;

import Api.fipeUsage.controller.Controller;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FipeUsageApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FipeUsageApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Controller controller = new Controller();
		controller.Start();
	}
}
