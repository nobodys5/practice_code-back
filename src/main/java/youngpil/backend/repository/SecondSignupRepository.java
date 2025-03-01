package youngpil.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import youngpil.backend.entity.SecondSignupEntity;

@Repository
public interface SecondSignupRepository extends JpaRepository<SecondSignupEntity, String> {
    boolean existsByUserId(String userId); 
    boolean existsByTelNumber(String telNumber); 
    SecondSignupEntity findByUserId(String userId);
    SecondSignupEntity findFirstBySnsIdAndJoinPath(String sns, String joinPath);
    SecondSignupEntity findBySnsIdAndJoinPath(String sns, String joinPath);
}
