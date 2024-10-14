package sspscom.example.ssps;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("notion")
public record NotionConfigProperties(String username, String password, String clientId, String clientSecret) {

}
