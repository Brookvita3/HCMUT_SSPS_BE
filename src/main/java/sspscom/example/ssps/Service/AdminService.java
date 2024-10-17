package sspscom.example.ssps.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sspscom.example.ssps.Dto.Request.StudentCreationRequest;
import sspscom.example.ssps.Entity.User;
import sspscom.example.ssps.Mapper.StudentMapper;
import sspscom.example.ssps.Repository.UserRepository;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminService {

    EmailService emailService;
    UserRepository userRepository;
    StudentMapper studentMapper;

    public ResponseEntity<String> addStudent(StudentCreationRequest studentCreationRequest) {
        User student = studentMapper.toUser(studentCreationRequest);
        userRepository.save(student);
        var subject = "Create account successfully";
        var body = "your account has been created successfully";
        emailService.sendMail(studentCreationRequest.getEmail(), subject, body);
        return ResponseEntity.ok("Student added successfully");
    }
}
