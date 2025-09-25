package upload.document.know_your_customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class KnowYourCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KnowYourCustomerApplication.class, args);
	}

}
