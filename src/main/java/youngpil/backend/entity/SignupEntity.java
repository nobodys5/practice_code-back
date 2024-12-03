package youngpil.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import youngpil.backend.dto.request.SignupRequestDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="practice")
@Table(name="signup")
public class SignupEntity {
    String name;
    @Id
    String userId;
    String password;
    String telNumber;

    public SignupEntity(SignupRequestDto dto) {
        this.name = getName();
        this.userId = getUserId();
        this.password = getPassword();
        this.telNumber = getTelNumber();
    }
}
