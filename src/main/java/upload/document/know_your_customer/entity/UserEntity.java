package upload.document.know_your_customer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import upload.document.know_your_customer.enums.UserStatus;
import upload.document.know_your_customer.enums.UserType;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "USERS")
public class UserEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_GEN")
    @SequenceGenerator(name = "USERS_GEN", sequenceName = "USERS_SEQ", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "USER_KEY", nullable = false, unique = true, updatable = false)
    @UuidGenerator
    private String userKey;

    @Column(name = "LAST_NAME", nullable = false)
    private String  lastName;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Email
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String passWord;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "BIRTH_DATE", nullable = false)
    private Date birthDate;


    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;

    @Column(name = "USER_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;
}
