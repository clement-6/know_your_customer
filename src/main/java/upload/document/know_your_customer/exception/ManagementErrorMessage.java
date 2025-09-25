package upload.document.know_your_customer.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import upload.document.know_your_customer.exception.models.ManagementError;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@ToString
public class ManagementErrorMessage {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'hh:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME ,pattern = "yyyy.MM.dd'T'HH:mm:ss", fallbackPatterns = {"YYYY-MM-dd"})
    private final LocalDateTime timestamp;
    private HttpStatus httpStatus;
    private final String code;
    private final String message;
    private final List<ManagementError> errors;

    public ManagementErrorMessage(HttpStatus status, String code, String message, List<ManagementError> errors) {
        this(LocalDateTime.now(), status, code, message, errors);
    }

    public ManagementErrorMessage(LocalDateTime timestamp, HttpStatus httpStatus, String code, String message, List<ManagementError> errors) {
        this.timestamp = timestamp;
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
        this.errors = errors;
    }
}
