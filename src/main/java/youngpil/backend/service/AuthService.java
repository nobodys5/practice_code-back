package youngpil.backend.service;

import org.springframework.http.ResponseEntity;

import youngpil.backend.dto.request.IdCheckRequestDto;
import youngpil.backend.dto.request.PostUserRequestDto;
import youngpil.backend.dto.request.SigninRequestDto;
import youngpil.backend.dto.request.SigninRequestDtoSecond;
import youngpil.backend.dto.request.SignupRequestSecondDto;
import youngpil.backend.dto.request.TelAuthCheckRequestDto;
import youngpil.backend.dto.request.TelAuthRequestDto;
import youngpil.backend.dto.response.ResponseDto;
import youngpil.backend.dto.response.SignInResponseDto;

public interface AuthService {
    String Signup(PostUserRequestDto dto);
    ResponseEntity<ResponseDto> SignupSecond(SignupRequestSecondDto dto);
    String Signin(SigninRequestDto dto);
    ResponseEntity<ResponseDto> Telauth(TelAuthRequestDto dto);
    ResponseEntity<ResponseDto> TelauthCheck(TelAuthCheckRequestDto dto);
    ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto);
    ResponseEntity<? super SignInResponseDto> Signin(SigninRequestDtoSecond dto);
}
