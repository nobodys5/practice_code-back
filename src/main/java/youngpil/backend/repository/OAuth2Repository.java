package youngpil.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import youngpil.backend.entity.OAuth2Entity;

public interface OAuth2Repository extends JpaRepository<OAuth2Entity, String>{
    boolean existsByUserId(String userId);
    boolean existsByTelNumber(String telNumber);
    
    OAuth2Entity findByUserId(String userId);
    OAuth2Entity findBySnsIdAndJoinPath(String sns, String joinPath);
}
