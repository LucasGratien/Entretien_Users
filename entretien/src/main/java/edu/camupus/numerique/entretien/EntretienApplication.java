package edu.camupus.numerique.entretien;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class EntretienApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntretienApplication.class, args);
	}

}
