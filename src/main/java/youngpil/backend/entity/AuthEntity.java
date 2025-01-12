package youngpil.backend.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import youngpil.backend.dto.request.PostUserRequestDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name="auth")
@Table(name="signup")
@Builder
public class AuthEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String userId;
    String name;
    String email;
    String password;

    // service에서 dto에 있는 객체를 받기위한 용도
    public AuthEntity(PostUserRequestDto dto) {
        this.userId = getUserId();
        this.name = getName();
        this.email = getEmail();
        this.password = getPassword();
    }

   
}
