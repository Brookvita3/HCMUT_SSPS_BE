package sspscom.example.ssps.Controller;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sspscom.example.ssps.Dto.Request.UserCreationRequest;
import sspscom.example.ssps.Service.AdminService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminController {

    AdminService adminService;

    @PostMapping("/add/student")
    public ResponseEntity<String> addStudent(@RequestBody UserCreationRequest request) {
        return adminService.addStudent(request);
    }

    @PostMapping("/add/admin")
    public ResponseEntity<String> addAdmin(@RequestBody UserCreationRequest request) {
        return adminService.addAdmin(request);
    }
}
