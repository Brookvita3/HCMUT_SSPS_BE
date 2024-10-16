package sspscom.example.ssps.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sspscom.example.ssps.Service.EmailService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class EmailController {
    EmailService emailService;

    @GetMapping("/sendEmail")
    public ResponseEntity<String> sendEmail() {
        emailService.sendMail("bach.tranchinhbach@hcmut.edu.vn", "Test", "hello");
        return ResponseEntity.status(HttpStatus.OK).body("Email sent");
    }
}
