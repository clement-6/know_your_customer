package upload.document.know_your_customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upload.document.know_your_customer.entity.DocumentKYC;

import java.util.Optional;

@Repository
public interface DocumentKYCRepo extends JpaRepository<DocumentKYC, Long> {

    Optional<DocumentKYC> findDocumentKYCByFileKey(String fileKey);
}
