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
import youngpil.backend.dto.request.SignupRequestDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name="test")
@Table(name="users")
@Builder
public class SignupEntity {
    @Id
    String userId;
    String name;
    String email;
    String password;

    // service에서 dto에 있는 객체를 받기위한 용도
    public SignupEntity(PostUserRequestDto dto) {
        this.userId = dto.getUserId();
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
    }

   
}
