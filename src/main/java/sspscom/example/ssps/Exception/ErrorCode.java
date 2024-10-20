package sspscom.example.ssps.Exception;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND(1001, "user not found in database", HttpStatus.NOT_FOUND),
    PASSWORD_INCORRECT(1002, "password incorrect", HttpStatus.BAD_REQUEST);

    private final int ErrorCode;
    private final String ErrorMsg;
    private final HttpStatus httpStatus;
}
