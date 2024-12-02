package youngpil.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import youngpil.backend.entity.SignupEntity;

public interface SignUpRepository extends JpaRepository<SignupEntity, String> {
    
}
