package sspscom.example.ssps.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import sspscom.example.ssps.Dto.Request.LoginFormRequest;
import sspscom.example.ssps.Service.AuthenticationService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {

    AuthenticationService authenticationService;

    @PostMapping("/login")
    public String login(@RequestBody LoginFormRequest loginForm) {
        var bool = authenticationService.checkLogin(loginForm.getUsername(), loginForm.getPassword());
        if(bool) return "login successful";
        else return "login failed";
    }
}
