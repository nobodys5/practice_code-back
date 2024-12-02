package youngpil.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="practice")
@Table(name="practice")
public class SignupEntity {
    @Id
    String userId;
    String name;
    String password;
    String telNumber;
}
