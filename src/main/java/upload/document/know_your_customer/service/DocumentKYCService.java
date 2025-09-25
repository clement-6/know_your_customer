package upload.document.know_your_customer.service;

import org.springframework.web.multipart.MultipartFile;
import upload.document.know_your_customer.dto.FileResponse;


import java.io.IOException;
import java.util.List;


public interface DocumentKYCService {

    FileResponse uploadFile(MultipartFile file, String userKey, String dto) throws IOException;

    List<FileResponse> getAllFiles();

    FileResponse getFile(String fileKey);

    FileResponse rejectedFile(String fileKey);

    FileResponse approvedFile(String fileKey);

    void deleteFile(String fileKey);

}
