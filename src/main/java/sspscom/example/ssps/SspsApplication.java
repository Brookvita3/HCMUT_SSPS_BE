package sspscom.example.ssps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(NotionConfigProperties.class)
public class SspsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SspsApplication.class, args);
	}

}
