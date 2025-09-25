package upload.document.know_your_customer.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import upload.document.know_your_customer.enums.DocumentStatus;
import upload.document.know_your_customer.enums.DocumentType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "DOCUMENT_KYC")
public class DocumentKYC extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_GEN")
    @SequenceGenerator(name = "USERS_GEN", sequenceName = "USERS_SEQ", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "FILE_KEY", unique = true, nullable = false)
    @UuidGenerator
    private String fileKey;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "FILE_TYPE ")
    private String fileTypes;

    @Column(name = "FILE_SIZE")
    private long fileSize;

    @Column(name = "CHECKSUM")
    private String checksum;

    @Column(name = "FILE_DATA")
    private byte[] fileData;

    @Column(name = "TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private DocumentType type;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private DocumentStatus status=DocumentStatus.PENDING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERS_KEY", referencedColumnName = "USER_KEY")
    private UserEntity userEntity;


}
