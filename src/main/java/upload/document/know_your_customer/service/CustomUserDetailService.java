package upload.document.know_your_customer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import upload.document.know_your_customer.entity.UserEntity;
import upload.document.know_your_customer.repository.UserEntityRepo;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserEntityRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findByEmail(username);

        if (userEntity == null){
            throw new UsernameNotFoundException("User  not Found");
        }
        return new User(userEntity.getEmail(),userEntity.getEmail(), Collections.singletonList(new SimpleGrantedAuthority(String.valueOf(userEntity.getUserType()))));
    }
}
