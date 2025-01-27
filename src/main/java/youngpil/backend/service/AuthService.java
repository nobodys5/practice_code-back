package youngpil.backend.service;

import youngpil.backend.dto.request.PostUserRequestDto;
import youngpil.backend.dto.request.SigninRequestDto;
import youngpil.backend.dto.request.TelAuthCheckRequestDto;
import youngpil.backend.dto.request.TelAuthRequestDto;

public interface AuthService {
    String Signup(PostUserRequestDto dto);
    String Signin(SigninRequestDto dto);
    String Telauth(TelAuthRequestDto dto);
    String TelauthCheck(TelAuthCheckRequestDto dto);
}
