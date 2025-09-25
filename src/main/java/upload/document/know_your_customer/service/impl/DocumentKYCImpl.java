package upload.document.know_your_customer.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import upload.document.know_your_customer.dto.FileResponse;
import upload.document.know_your_customer.entity.DocumentKYC;

import upload.document.know_your_customer.entity.UserEntity;
import upload.document.know_your_customer.enums.DocumentType;
import upload.document.know_your_customer.exception.ExceptionsMessages;
import upload.document.know_your_customer.exception.ResourceNotFoundException;
import upload.document.know_your_customer.mappers.DocumentKYCMappers;
import upload.document.know_your_customer.repository.DocumentKYCRepo;
import upload.document.know_your_customer.service.DocumentKYCService;
import upload.document.know_your_customer.service.UserService;
import upload.document.know_your_customer.utils.KycUtils;

import java.io.IOException;
import java.util.List;

import static upload.document.know_your_customer.enums.DocumentStatus.APPROVED;
import static upload.document.know_your_customer.enums.DocumentStatus.REJECTED;

@Service
@RequiredArgsConstructor
public class DocumentKYCImpl implements DocumentKYCService {

    private final DocumentKYCRepo kycRepo;
    private final DocumentKYCMappers kycMappers;

    private final UserService userService;

    private DocumentKYC getInternalFile(String fileKey) {
        return kycRepo.findDocumentKYCByFileKey(fileKey).orElseThrow(() -> new ResourceNotFoundException(ExceptionsMessages.M_RESOURCE_NOT_FOUND, "file Not found"));
    }

    @Override
    public FileResponse uploadFile(MultipartFile file, String userKey, String dto) throws IOException {
        UserEntity user = userService.getInternalUser(userKey);
        DocumentKYC files  = new DocumentKYC();
        files.setFileName(file.getOriginalFilename());
        files.setFileTypes(file.getContentType());
        Validate.isTrue(KycUtils.EXTENSIONS_AUTORISEES.contains(files.getFileTypes()), "Invalid file type");
        Validate.isTrue(files.getFileSize() <= 1000000, "the file must not exceed 5MB");
        files.setFileData(file.getBytes());
        files.setFileSize(file.getSize());
        files.setType(DocumentType.valueOf(dto));
        files.setUserEntity(user);
        files.setChecksum(KycUtils.checkSumValue(file.getInputStream()));
        return kycMappers.mapToResponse(kycRepo.save(files));
    }

    @Override
    public List<FileResponse> getAllFiles() {
        return kycRepo.findAll().stream().map(kycMappers::mapToResponse).toList();
    }

    @Override
    public FileResponse getFile(String fileKey) {
        return kycMappers.mapToResponse(getInternalFile(fileKey));
    }

    @Override
    public FileResponse rejectedFile(String fileKey) {
        DocumentKYC file = getInternalFile(fileKey);
        file.setStatus(REJECTED);
        return kycMappers.mapToResponse(kycRepo.save(file));
    }

    @Override
    public FileResponse approvedFile(String fileKey) {
        DocumentKYC file = getInternalFile(fileKey);
        file.setStatus(APPROVED);
        return kycMappers.mapToResponse(kycRepo.save(file));
    }

    @Override
    public void deleteFile(String fileKey) {
        DocumentKYC file = getInternalFile(fileKey);
        kycRepo.delete(file);
    }
}
