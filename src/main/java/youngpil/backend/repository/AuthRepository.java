package youngpil.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import youngpil.backend.entity.AuthEntity;
import youngpil.backend.entity.SignupEntity;
import java.util.List;


@Repository
public interface AuthRepository extends JpaRepository<AuthEntity, String> {
    boolean existsByUserId(String userId);    
    // boolean existsByName(String name);    
    // boolean existsByEmail(String email);    
    // boolean existsByPassword(String password);    

    //  @Query(value = 
    // "SELECT * " +
    // "FROM users " +
    // "WHERE name = :name ",
    // nativeQuery = true
    // )
    // List<SignupEntity> getsql(
    //     @Param("name") String name
    // );

    // SignupEntity findByUserId(String userId);
}
