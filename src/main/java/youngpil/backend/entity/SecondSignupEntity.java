package youngpil.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import youngpil.backend.dto.request.SignupRequestSecondDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Login")
@Table(name = "signupsecond")
public class SecondSignupEntity {
    @Id
    private String userId;
    private String password;
    private String name;
    private String telNumber;
    private String joinPath;
    private String snsId;

    public SecondSignupEntity(SignupRequestSecondDto dto) {
        this.userId = dto.getUserId();
        this.password = dto.getPassword();
        this.name = dto.getName();
        this.telNumber = dto.getTelNumber();
        this.joinPath = dto.getJoinPath();
        this.snsId = dto.getSnsId();
    }
}
