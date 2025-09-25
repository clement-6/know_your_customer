package upload.document.know_your_customer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import upload.document.know_your_customer.enums.UserType;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntityCreateDto {

    @NotBlank
    private String lastName;
    @NotBlank
    private String firstName;
    @NotBlank
    @Email
    private String email;
    private String phone;
    @NotBlank
    private String passWord;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd.MM.yyyy")
    private Date birthDate;
    @NotNull
    private UserType userType;
}
