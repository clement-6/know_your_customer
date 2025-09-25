package upload.document.know_your_customer.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntityUpdateDto extends UserEntityCreateDto {

    private Long id;
    @NotNull
    private Long version;
}
