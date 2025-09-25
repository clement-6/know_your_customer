package upload.document.know_your_customer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import upload.document.know_your_customer.configuration.JwtUtils;
import upload.document.know_your_customer.dto.AuthDto;
import upload.document.know_your_customer.repository.UserEntityRepo;


import java.util.HashMap;
import java.util.Map;

import static upload.document.know_your_customer.ApiConstant.API_ROOT_SERVICE;

@RestController
@RequestMapping(API_ROOT_SERVICE)
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserEntityRepo userEntityRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> login(@RequestBody AuthDto user) {
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassWord()));
            if (authentication.isAuthenticated()){
                Map<String, Object> authData = new HashMap<>();
                authData.put("token", jwtUtils.generateToken(user.getEmail()));
                authData.put("type", "Bearer");
                return ResponseEntity.ok(authData);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username or passWord");
        }catch (AuthenticationException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username or passWord");
        }
    }

}
