package sspscom.example.ssps.Config;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

// use for handle unauthenticated request
public interface JWTAuthenticationEntryPoint extends AuthenticationEntryPoint {
    @Override
    default void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        response.sendRedirect("/auth/login");
    }
}
