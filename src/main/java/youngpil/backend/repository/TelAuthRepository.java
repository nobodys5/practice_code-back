package youngpil.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import youngpil.backend.entity.TelAuthEntity;

@Repository
public interface TelAuthRepository extends JpaRepository<TelAuthEntity, String> {
    boolean existsByTelNumber(String telNumber);
}
