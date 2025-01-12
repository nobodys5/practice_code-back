package youngpil.backend.service;

import youngpil.backend.dto.request.PostUserRequestDto;
import youngpil.backend.dto.request.SigninRequestDto;

public interface AuthService {
    String Signup(PostUserRequestDto dto);
    String Signin(SigninRequestDto dto);
}
