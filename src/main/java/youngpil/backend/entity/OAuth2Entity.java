package youngpil.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import youngpil.backend.dto.request.OAuth2SignupRequestDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "movies")
@Table(name = "oauth2")
public class OAuth2Entity {
    @Id
    private String userId;
    private String password;
    private String name;
    private String telNumber;
    private String joinPath;
    private String snsId;

    public OAuth2Entity(OAuth2SignupRequestDto dto) {
        this.userId = dto.getUserId();
        this.password = dto.getPassword();
        this.name = dto.getName();
        this.telNumber = dto.getTelNumber();
        this.joinPath = dto.getJoinPath();
        this.snsId = dto.getSnsId();
    }
}
