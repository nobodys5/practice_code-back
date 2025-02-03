package youngpil.backend.service;

import org.springframework.http.ResponseEntity;

import youngpil.backend.dto.request.IdCheckRequestDto;
import youngpil.backend.dto.request.PostUserRequestDto;
import youngpil.backend.dto.request.SigninRequestDto;
import youngpil.backend.dto.request.TelAuthCheckRequestDto;
import youngpil.backend.dto.request.TelAuthRequestDto;
import youngpil.backend.dto.response.ResponseDto;

public interface AuthService {
    String Signup(PostUserRequestDto dto);
    String Signin(SigninRequestDto dto);
    String Telauth(TelAuthRequestDto dto);
    String TelauthCheck(TelAuthCheckRequestDto dto);
    ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto);
}
