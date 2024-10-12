package sspscom.example.ssps.Controller;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sspscom.example.ssps.Service.StudentService;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class StudentController {
    StudentService studentService;

    @GetMapping("/infor")
    public ResponseEntity<String> getStudentInfor() {
        return studentService.getStudentInfor();
    }

}
