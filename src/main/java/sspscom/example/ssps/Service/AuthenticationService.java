package sspscom.example.ssps.Service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sspscom.example.ssps.Dto.Response.LoginFormResponse;
import sspscom.example.ssps.Entity.User;
import sspscom.example.ssps.Exception.AppException;
import sspscom.example.ssps.Exception.ErrorCode;
import sspscom.example.ssps.Repository.UserRepository;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);
    @NonFinal
    @Value("${SECRET_KEY}")
    String SECRET_KEY;

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    String generateToken(User user) throws JOSEException {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("SSPS_HCMUT")
                .issueTime(new Date())
                .expirationTime(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1)))
//                .claim("role", buildScope(user.getRole()))
                .claim("scope", buildScope(user.getRole().name()))
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

    public LoginFormResponse checkLogin(String username, String password) throws JOSEException {
        var user = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        var pass = passwordEncoder.matches(password, user.getPassword());
        if (pass) {
            return LoginFormResponse.builder()
                    .token(generateToken(user))
                    .build();
        }
        else throw new AppException(ErrorCode.PASSWORD_INCORRECT);
    }
}
