package upload.document.know_your_customer.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import upload.document.know_your_customer.configuration.JwtUtils;
import upload.document.know_your_customer.dto.*;
import upload.document.know_your_customer.entity.UserEntity;
import upload.document.know_your_customer.enums.UserStatus;
import upload.document.know_your_customer.enums.UserType;
import upload.document.know_your_customer.exception.ExceptionsMessages;
import upload.document.know_your_customer.exception.ResourceNotFoundException;
import upload.document.know_your_customer.mappers.UserEntityMappers;
import upload.document.know_your_customer.repository.UserEntityRepo;
import upload.document.know_your_customer.service.CustomUserDetailService;
import upload.document.know_your_customer.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserEntityRepo userRepo;
    private final UserEntityMappers userMappers;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final CustomUserDetailService customUserDetails;

    public UserEntity getInternalUser(String userKey) {
        return userRepo.findUserEntityByUserKey(userKey).orElseThrow(() -> new ResourceNotFoundException(ExceptionsMessages.M_RESOURCE_NOT_FOUND, "User with key "+userKey+" not found"));
    }

    @Override
    public UserEntityResponse createUser(UserEntityCreateDto userDto) {
        UserEntity userEntity = userMappers.mapToEntity(userDto);
        userEntity.setPassWord(passwordEncoder.encode(userDto.getPassWord()));
        return userMappers.mapToResponse(userRepo.save(userEntity));
    }

    @Override
    public UserEntityResponse updateUser(UserEntityUpdateDto userDto, String userKey) {
        UserEntity user = getInternalUser(userKey);
        user = userMappers.mapToUpdate(userDto,user);
        return userMappers.mapToResponse(user);
    }

    @Override
    public UserEntityResponse getUserByUserKey(String userKey) {
        return userMappers.mapToResponse(getInternalUser(userKey));
    }

    @Override
    public UserEntityResponse fireUser(String userKey) {
        UserEntity user = getInternalUser(userKey);
        user.setStatus(UserStatus.FIRED);
        return userMappers.mapToResponse(user);
    }

    @Override
    public UserEntityResponse reActiveUser(String userKey) {
        UserEntity user = getInternalUser(userKey);
        user.setStatus(UserStatus.ACTIVE);
        return userMappers.mapToResponse(user);
    }



}
