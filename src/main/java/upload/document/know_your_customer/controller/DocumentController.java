package upload.document.know_your_customer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import upload.document.know_your_customer.dto.FileResponse;
import upload.document.know_your_customer.service.DocumentKYCService;


import java.io.IOException;
import java.util.List;

import static upload.document.know_your_customer.ApiConstant.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_ROOT_SERVICE)
public class DocumentController {

    private final DocumentKYCService kycService;

    @PostMapping(value = KYC + USER_KEY, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<FileResponse> upload(@RequestPart("file") MultipartFile file, @PathVariable String userKey, @RequestParam("type") String dto) throws IOException {
        return new ResponseEntity<>(kycService.uploadFile(file,userKey,dto), HttpStatus.CREATED);

    }

    @PatchMapping(KYC_KEY + "/rejected")
    public ResponseEntity<FileResponse> rejectedFile(@PathVariable String fileKey) {
        return new ResponseEntity<>(kycService.rejectedFile(fileKey),HttpStatus.OK);
    }

    @PatchMapping(KYC_KEY + "/approved")
    public ResponseEntity<FileResponse> approvedFile(@PathVariable String fileKey) {
        return new ResponseEntity<>(kycService.approvedFile(fileKey),HttpStatus.OK);
    }

    @GetMapping(KYC)
    public ResponseEntity<List<FileResponse>> getAllFiles(){
        return new ResponseEntity<>(kycService.getAllFiles(),HttpStatus.OK);
    }

    @GetMapping(KYC_KEY)
    public ResponseEntity<FileResponse> getFile(@PathVariable String fileKey){
        return new ResponseEntity<>(kycService.getFile(fileKey),HttpStatus.OK);
    }

    @GetMapping(KYC_KEY + "/download")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileKey){
        FileResponse file = kycService.getFile(fileKey);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(file.getFileTypes())).body(file.getFileData());
    }

    @DeleteMapping(KYC_KEY)
    public ResponseEntity<Void> deleteFile(@PathVariable String fileKey) {
        kycService.deleteFile(fileKey);
        return ResponseEntity.ok().build();
    }
}
