package upload.document.know_your_customer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import upload.document.know_your_customer.enums.UserStatus;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserEntityResponse extends UserEntityUpdateDto {

    private String userKey;
    private UserStatus status;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd.MM.yyyy HH:mm:ss")
    private LocalDateTime createdDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd.MM.yyyy HH:mm:ss")
    private LocalDateTime lastModifiedDate;

}
