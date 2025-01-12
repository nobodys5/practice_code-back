package youngpil.backend.service;

import youngpil.backend.dto.request.PostUserRequestDto;

public interface AuthService {
    String Signup(PostUserRequestDto dto);
}
