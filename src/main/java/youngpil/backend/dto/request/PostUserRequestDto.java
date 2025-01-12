package youngpil.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostUserRequestDto {
    
   @NotBlank()
   String userId;
   @NotBlank()
   String name;
   @NotBlank()
   String email;
   @NotBlank()
    String password;
}
