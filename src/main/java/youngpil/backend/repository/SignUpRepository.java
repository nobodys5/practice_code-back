package youngpil.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import youngpil.backend.entity.SignupEntity;
import java.util.List;


@Repository
public interface SignUpRepository extends JpaRepository<SignupEntity, String> {
    boolean existsByUserId(String userId);    
     

    // SignupEntity findByUserId(String userId);
}
