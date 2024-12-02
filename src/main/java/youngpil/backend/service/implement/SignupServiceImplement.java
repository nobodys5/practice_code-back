package youngpil.backend.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import youngpil.backend.dto.request.SignupRequestDto;
import youngpil.backend.repository.SignUpRepository;
import youngpil.backend.service.SignupService;
import youngpil.backend.tool.ResponseDto;


@Service
@RequiredArgsConstructor
public class SignupServiceImplement implements SignupService{

    @Override
    public ResponseEntity<ResponseDto> Signup(SignupRequestDto dto) {
        try {
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }
    
}
