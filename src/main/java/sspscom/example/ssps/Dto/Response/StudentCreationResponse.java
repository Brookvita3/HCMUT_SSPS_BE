package sspscom.example.ssps.Dto.Response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentCreationResponse {
    String username;
    String password;
}
