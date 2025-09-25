package upload.document.know_your_customer.mappers;

import org.mapstruct.Mapper;
import upload.document.know_your_customer.dto.FileResponse;
import upload.document.know_your_customer.entity.DocumentKYC;


@Mapper(componentModel = "spring", uses = {UserEntityMappers.class})
public interface DocumentKYCMappers {


    FileResponse mapToResponse(DocumentKYC documentKYC);

}
