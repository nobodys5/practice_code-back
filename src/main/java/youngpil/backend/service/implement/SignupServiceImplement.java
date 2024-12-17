package youngpil.backend.service.implement;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
    public ResponseEntity<String> Signup(SignupRequestDto dto) {
        String userId = dto.getUserId();
        String name = dto.getName();
        String email = dto.getEmail();
        String password = dto.getPassword();

        boolean result = signUpRepository.existsById(userId);
        if (result) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 존재하는 값입니다.");

        SignupEntity entity = new SignupEntity(userId, name, email, password);
        signUpRepository.save(entity);
       
        return ResponseEntity.status(HttpStatus.CREATED).body("성공");
    }

    @Override
    public ResponseEntity<String> delete(String userId) {

        // signUpRepository.deleteById(userId);

        SignupEntity signupEntity = signUpRepository.findById(userId).get();
        signUpRepository.delete(signupEntity);
        return ResponseEntity.status(HttpStatus.OK).body("성공");
    }

    @Override
    public ResponseEntity<String> getsql() {

        List<SignupEntity> signupEntity = signUpRepository.getsql("홍길동");

        return ResponseEntity.status(HttpStatus.OK).body(signupEntity.toString());

    }

    
    
}
