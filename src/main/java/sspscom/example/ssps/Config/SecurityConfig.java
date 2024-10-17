package sspscom.example.ssps.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final String[] PUBLIC_URL = {"/students", "/admin/add/student"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/").permitAll();
                    auth.requestMatchers(PUBLIC_URL).permitAll();
                    auth.anyRequest().authenticated();
                })
//                .oauth2Login(withDefaults())
//                .formLogin(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

}


