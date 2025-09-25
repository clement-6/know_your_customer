package upload.document.know_your_customer.service;

import upload.document.know_your_customer.dto.UserEntityCreateDto;
import upload.document.know_your_customer.dto.UserEntityResponse;
import upload.document.know_your_customer.dto.UserEntityUpdateDto;
import upload.document.know_your_customer.entity.UserEntity;

public interface UserService {

    UserEntity getInternalUser(String userKey);
    UserEntityResponse createUser(UserEntityCreateDto userDto);

    UserEntityResponse updateUser(UserEntityUpdateDto userDto, String userKey);
    UserEntityResponse getUserByUserKey(String userKey);
    UserEntityResponse fireUser(String userKey);

    UserEntityResponse reActiveUser(String userKey);
}
