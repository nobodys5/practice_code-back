package youngpil.backend.service;

import org.springframework.http.ResponseEntity;

import youngpil.backend.dto.response.getResponse.GetSignInResponseDto;

public interface NurseService {
    ResponseEntity<? super GetSignInResponseDto> getSignIn(String userId);
}
