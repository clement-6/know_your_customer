package upload.document.know_your_customer.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import upload.document.know_your_customer.dto.UserEntityCreateDto;
import upload.document.know_your_customer.dto.UserEntityResponse;
import upload.document.know_your_customer.dto.UserEntityUpdateDto;
import upload.document.know_your_customer.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserEntityMappers {


    UserEntity mapToEntity(UserEntityCreateDto userEntityCreateDto);


    UserEntity mapToUpdate(UserEntityUpdateDto userUpdateDto, @MappingTarget UserEntity userEntity);


    UserEntityResponse mapToResponse(UserEntity userEntity);
}
