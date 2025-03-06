package youngpil.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import youngpil.backend.dto.request.TelAuthRequestDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "telNumber")
@Table(name = "tel_auth_number")
public class TelAuthEntity {
    @Id
    private String telNumber;
    private String authNumber;

}
