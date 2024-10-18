package sspscom.example.ssps.Dto.Request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginFormRequest {
    @NotNull(message = "username can not be null")
    String username;

    @NotNull(message = "password cannot be null")
    String password;

}
