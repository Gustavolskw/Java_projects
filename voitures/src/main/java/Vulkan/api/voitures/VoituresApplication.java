package Vulkan.api.voitures;

import Vulkan.api.voitures.control.Controller;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VoituresApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(VoituresApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Controller controller = new Controller();
		controller.escolha();
	}
}
