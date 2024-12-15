package youngpil.backend.service;

import org.springframework.http.ResponseEntity;

import youngpil.backend.dto.request.SignupRequestDto;
import youngpil.backend.tool.ResponseDto;

public interface SignupService {
    ResponseEntity<String> Signup(SignupRequestDto dto);
    ResponseEntity<String> delete(String userId);
}
