package youngpil.backend.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignupRequestDto {
    String name;
    String userId;
    String password;
    String telNumber;
}
