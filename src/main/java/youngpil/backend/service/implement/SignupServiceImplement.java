package youngpil.backend.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import youngpil.backend.dto.request.SignupRequestDto;
import youngpil.backend.entity.SignupEntity;
import youngpil.backend.repository.SignUpRepository;
import youngpil.backend.service.SignupService;
import youngpil.backend.tool.ResponseDto;


@Service
@RequiredArgsConstructor
public class SignupServiceImplement implements SignupService{

    private final SignUpRepository signUpRepository;

    @Override
    public ResponseEntity<ResponseDto> Signup(SignupRequestDto dto) {
        String userId = dto.getUserId();
        // String name = dto.getName();
        // String password = dto.getPassword();
        // String telNumber = dto.getTelNumber();
        try {
            boolean result  = signUpRepository.existsByUserId(userId);
            if (!result) return ResponseDto.databaseError();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }
    
}
