package youngpil.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import youngpil.backend.entity.TelAuthEntity;

@Repository
public interface TelAuthCheckRepository extends JpaRepository<TelAuthEntity, String>{
    boolean existsByTelNumberAndAuthNumber(String telNumber, String authNumber);
}
