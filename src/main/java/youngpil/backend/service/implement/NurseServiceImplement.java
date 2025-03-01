package youngpil.backend.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import youngpil.backend.dto.response.ResponseDto;
import youngpil.backend.dto.response.getResponse.GetSignInResponseDto;
import youngpil.backend.entity.SecondSignupEntity;
import youngpil.backend.repository.SecondSignupRepository;
import youngpil.backend.service.NurseService;

@Service
@RequiredArgsConstructor
public class NurseServiceImplement implements NurseService {
    private final SecondSignupRepository secondSignupRepository;
    
    @Override
    public ResponseEntity<? super GetSignInResponseDto> getSignIn(String userId) {
        SecondSignupEntity secondSignupEntity = null;
        try {
            
            secondSignupEntity = secondSignupRepository.findByUserId(userId);
            if (secondSignupEntity == null) return ResponseDto.NoExistUserId();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.DatabaseError();
        }
        return GetSignInResponseDto.success(secondSignupEntity);
    }
    
}
