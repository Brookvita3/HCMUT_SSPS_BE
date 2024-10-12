package sspscom.example.ssps.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import sspscom.example.ssps.Service.EmailService;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class EmailController {
    EmailService emailService;

    @GetMapping("/email")
    public ResponseEntity<String> sendEmail() {
        emailService.sendMail("bach.tranchinhbach@gmail.com", "Test", "hello");
        return ResponseEntity.status(HttpStatus.OK).body("Email sent");
    }
}
