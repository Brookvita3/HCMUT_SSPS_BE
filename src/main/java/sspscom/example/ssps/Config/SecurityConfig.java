package sspscom.example.ssps.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final String[] PUBLIC_GET_URL = {"/students"};
    private final String[] PUBLIC_POST_URL = {"/admin/add/student", "/auth/login"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/").permitAll();
                    auth.requestMatchers(HttpMethod.POST, PUBLIC_POST_URL).permitAll();
                    auth.anyRequest().authenticated();
                })
//                .formLogin(formLogin -> formLogin
//                        .usernameParameter("username")
//                        .passwordParameter("password")
//                        .loginPage("/auth/login")
//                        .permitAll()
//                )
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}


