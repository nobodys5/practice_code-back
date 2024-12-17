package youngpil.backend.entity;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Value;
import youngpil.backend.dto.request.SignupRequestDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name="movie")
@Table(name="users")
public class SignupEntity {
    @Id
    String userId;
    String name;
    String email;
    String password;

    // public SignupEntity(SignupRequestDto dto) {
    //     this.userId = getUserId();
    //     this.name = getName();
    //     this.email = getEmail();
    //     this.password = getPassword();
    // }

   
}
