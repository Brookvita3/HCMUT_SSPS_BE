package sspscom.example.ssps.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sspscom.example.ssps.Dto.Request.UserCreationRequest;
import sspscom.example.ssps.Entity.User;
import sspscom.example.ssps.Mapper.AdminMapper;
import sspscom.example.ssps.Mapper.StudentMapper;
import sspscom.example.ssps.Repository.UserRepository;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminService {

    EmailService emailService;
    UserRepository userRepository;
    StudentMapper studentMapper;
    AdminMapper adminMapper;
    PasswordEncoder passwordEncoder;

    public ResponseEntity<String> addStudent(UserCreationRequest userCreationRequest) {
        User student = studentMapper.toUser(userCreationRequest);
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        userRepository.save(student);
        var subject = "Create account successfully";
        var body = "Your account has been created successfully";
        emailService.sendMail(userCreationRequest.getEmail(), subject, body);
        return ResponseEntity.ok("Student added successfully");
    }

    public ResponseEntity<String> addAdmin(UserCreationRequest userCreationRequest) {
        User admin = adminMapper.toUser(userCreationRequest);
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        userRepository.save(admin);
        var subject = "Create account successfully";
        var body = "Your account has been created successfully";
        emailService.sendMail(userCreationRequest.getEmail(), subject, body);
        return ResponseEntity.ok("Admin added successfully");
    }
}
