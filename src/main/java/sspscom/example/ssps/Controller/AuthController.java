package sspscom.example.ssps.Controller;

import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sspscom.example.ssps.Dto.APIResponse;
import sspscom.example.ssps.Dto.Request.LoginFormRequest;
import sspscom.example.ssps.Dto.Response.LoginFormResponse;
import sspscom.example.ssps.Service.AuthenticationService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {

    AuthenticationService authenticationService;


    @PostMapping("/login")
    public APIResponse<LoginFormResponse> login(@RequestBody LoginFormRequest loginForm) throws JOSEException {
        return APIResponse.<LoginFormResponse>builder()
                .data(authenticationService.checkLogin(loginForm.getUsername(), loginForm.getPassword()))
                .message("this is your token")
                .build();
    }

    @GetMapping("/login")
    public ResponseEntity<String> loginPage() {
        return ResponseEntity.ok().body("This is login page");
    }
}
