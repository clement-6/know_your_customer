package upload.document.know_your_customer.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upload.document.know_your_customer.dto.UserEntityCreateDto;
import upload.document.know_your_customer.dto.UserEntityResponse;
import upload.document.know_your_customer.dto.UserEntityUpdateDto;
import upload.document.know_your_customer.service.UserService;
import static upload.document.know_your_customer.ApiConstant.*;

@RestController
@RequestMapping(API_ROOT_SERVICE)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(USER)
    public ResponseEntity<UserEntityResponse> createUser(@RequestBody @Valid @NotNull UserEntityCreateDto user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }
    @PutMapping(USER_KEY)
    public ResponseEntity<UserEntityResponse> updateUser(@RequestBody @Valid @NotNull UserEntityUpdateDto userUpdateDto, @PathVariable(name = "userKey") String userKey) {
        return new ResponseEntity<>(userService.updateUser(userUpdateDto,userKey), HttpStatus.OK);
    }
    @GetMapping(USER_KEY)
    public ResponseEntity<UserEntityResponse> getUserById(@PathVariable(name = "userKey") String userKey) {
        return new ResponseEntity<>(userService.getUserByUserKey(userKey), HttpStatus.OK);
    }

    @PutMapping(FIRE)
    public ResponseEntity<UserEntityResponse> fireUser(@PathVariable(name = "userKey") String userKey) {
        return new ResponseEntity<>(userService.fireUser(userKey), HttpStatus.OK);
    }

    @PutMapping(ACTIVATE)
    public ResponseEntity<UserEntityResponse> reActiveUser(@PathVariable(name = "userKey") String userKey) {
        return new ResponseEntity<>(userService.reActiveUser(userKey), HttpStatus.OK);
    }

}
