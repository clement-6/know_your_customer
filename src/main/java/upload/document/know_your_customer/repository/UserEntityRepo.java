package upload.document.know_your_customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upload.document.know_your_customer.entity.UserEntity;
import java.util.Optional;

@Repository
public interface UserEntityRepo extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findUserEntityByUserKey(String userKey);

    UserEntity findByEmail(String email);
}
