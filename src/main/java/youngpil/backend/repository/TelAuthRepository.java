package youngpil.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelAuthRepository extends JpaRepository {
    boolean existsByTelNumber(String TelNumber);
}
