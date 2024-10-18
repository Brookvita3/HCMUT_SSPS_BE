package sspscom.example.ssps.Service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sspscom.example.ssps.Entity.User;
import sspscom.example.ssps.Repository.UserRepository;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {

    @NonFinal
    @Value("${SECRET_KEY}")
    String SECRET_KEY;

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    String generateToken(User user) throws JOSEException {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .issuer("SSPS_HCMUT")
                .subject(user.getUsername())
                .issueTime(new Date())
                .expirationTime(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(1)))
                .claim("scope", buildScope(user.getRole()))
                .jwtID(UUID.randomUUID().toString())
                .build();
        Payload payload = new Payload(jwtClaimsSet.toString());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        jwsObject.sign(new MACSigner(SECRET_KEY));
        return jwsObject.serialize();
    }

    String buildScope(String role) {
        return role;
    }

    public boolean checkLogin(String username, String password) {
        var user = userRepository.findByUsername(username);
        return user.filter(value -> passwordEncoder.matches(password, value.getPassword())).isPresent();
    }
}
