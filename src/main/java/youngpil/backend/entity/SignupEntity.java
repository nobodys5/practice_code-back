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
    @Id
    String userId;
    @Column(name="name", nullable = false)
    String name;
    @Column(name="password", nullable = false)
    String password;
    @Column(name="tel_Number", nullable = false, unique = true)
    String telNumber;

    public SignupEntity(SignupRequestDto dto) {
        this.userId = getUserId();
        this.name = getName();
        this.password = getPassword();
        this.telNumber = getTelNumber();
    }
}
