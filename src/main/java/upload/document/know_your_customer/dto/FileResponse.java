package upload.document.know_your_customer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import upload.document.know_your_customer.enums.DocumentStatus;
import upload.document.know_your_customer.enums.DocumentType;

import java.time.LocalDateTime;

@Getter
@Setter
public class FileResponse {

    private Long id;
    private String fileKey;
    private String fileName;
    private String fileTypes;
    private long fileSize;
    private String checksum;
    private byte[] fileData;
    private DocumentType type;
    private DocumentStatus status;
    private UserEntityResponse userEntity;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd.MM.yyyy HH:mm:ss")
    private LocalDateTime createdDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd.MM.yyyy HH:mm:ss")
    private LocalDateTime lastModifiedDate;
}
