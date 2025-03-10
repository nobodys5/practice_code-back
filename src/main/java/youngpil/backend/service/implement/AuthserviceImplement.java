package youngpil.backend.service.implement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import youngpil.backend.common.utill.object.AuthNumberCreator;
import youngpil.backend.dto.request.IdCheckRequestDto;
import youngpil.backend.dto.request.PostUserRequestDto;
import youngpil.backend.dto.request.SigninRequestDto;
import youngpil.backend.dto.request.SigninRequestDtoSecond;
import youngpil.backend.dto.request.SignupRequestSecondDto;
import youngpil.backend.dto.request.TelAuthCheckRequestDto;
import youngpil.backend.dto.request.TelAuthRequestDto;
import youngpil.backend.dto.response.ResponseDto;
import youngpil.backend.dto.response.SignInResponseDto;
import youngpil.backend.entity.AuthEntity;
import youngpil.backend.entity.SecondSignupEntity;
import youngpil.backend.entity.SignupEntity;
import youngpil.backend.entity.TelAuthEntity;
import youngpil.backend.provider.JwtProvider;
import youngpil.backend.provider.SmsProvider;
import youngpil.backend.repository.AuthRepository;
import youngpil.backend.repository.SecondSignupRepository;
import youngpil.backend.repository.SignUpRepository;
import youngpil.backend.repository.TelAuthCheckRepository;
import youngpil.backend.repository.TelAuthRepository;
import youngpil.backend.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthserviceImplement implements AuthService {

    // private final AuthRepository authRepository;
    private final SignUpRepository signUpRepository;
    private final SecondSignupRepository secondSignupRepository ;
    private final TelAuthRepository telAuthRepository;
    private final TelAuthCheckRepository telAuthCheckRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final JwtProvider jwtProvider;
    private final SmsProvider smsProvider;
    @Override
    public String Signup(PostUserRequestDto dto) {

        try {
            String userId = dto.getUserId();
            
            boolean result = signUpRepository.existsByUserId(userId);
            if (result) return "존재하는 아이디입니다.";

            
            // password를 dto에서 가져온다.
            String password = dto.getPassword();
            // 가져온 패스워드를 encoding 해준다.
            String encodedPassword = passwordEncoder.encode(password);
            // encoding 한 패스워드로 dto에 값을 넣어준다.
            dto.setPassword(encodedPassword);

            // 아래의 코드들을 요약하기 위해 entity에서 클래스를 생성하여 dto를 setter로 받는다.
            // String name = dto.getName();
            // String email = dto.getEmail();
            // String password = dto.getPassword();
            
            SignupEntity userEntity = new SignupEntity(dto);
            signUpRepository.save(userEntity);
            return "성공";
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return "예외발생";
        }
    }
    @Override
    public String Signin(SigninRequestDto dto) {
        try {
            String userId = dto.getUserId();
            SignupEntity entity = signUpRepository.findByUserId(userId);
            if (entity == null) return "로그인 정보가 일치하지 않습니다.";
            
            
            String password = dto.getPassword();
            String passwordEncode = entity.getPassword();
            
            boolean mathced = passwordEncoder.matches(password, passwordEncode);
            if (!mathced) return "로그인 정보가 일치하지 않습니다.";

            String jwt = jwtProvider.create(userId);
            return jwt;

        } catch (Exception exception) {
            exception.printStackTrace();
            return "실패";
        }
    }
    @Override
    public ResponseEntity<ResponseDto> Telauth(TelAuthRequestDto dto) {

        String telNumber = dto.getTelNumber();
        try {
            boolean result = telAuthRepository.existsByTelNumber(telNumber);
            if (result) return ResponseDto.DuplicatedTelNumber();

            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.DatabaseError();
            
        }
        
            String authNumber = AuthNumberCreator.number();
            boolean isSend = smsProvider.SendMessage(telNumber, authNumber);
            if (!isSend) return ResponseDto.TelauthFail();

            try {
                TelAuthEntity telAuthEntity = new TelAuthEntity(telNumber, authNumber);
                telAuthRepository.save(telAuthEntity);
            
            } catch (Exception exception) {
                exception.printStackTrace();
                ResponseDto.DatabaseError();
            }
        return ResponseDto.Success();
    }
    @Override
    public ResponseEntity<ResponseDto> TelauthCheck(TelAuthCheckRequestDto dto) {

        try {
            String TelNumber = dto.getTelNumber();
            String AuthNumber = dto.getAuthNumber();
            
            boolean result = telAuthCheckRepository.existsByTelNumberAndAuthNumber(TelNumber, AuthNumber);
            if (!result) return ResponseDto.TelauthFail();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.DatabaseError();
        }
        return ResponseDto.Success();
    }
    @Override
    public ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto) {
        try {
            String userId = dto.getUserId();
            boolean result = signUpRepository.existsByUserId(userId);
            if (result) return ResponseDto.Duplicated();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.DatabaseError();
        }
        return ResponseDto.Success();
    }
    @Override
    public ResponseEntity<? super SignInResponseDto> Signin(SigninRequestDtoSecond dto) {
        String userId = dto.getUserId();
        String password = dto.getPassword();
        String accessToken = null;
        
        try {
            SecondSignupEntity secondSignupEntity = secondSignupRepository.findByUserId(userId);
            if (secondSignupEntity == null) return ResponseDto.Duplicated();

           String passwordEncord = secondSignupEntity.getPassword();
           boolean isMatched = passwordEncoder.matches(password, passwordEncord);
            if (!isMatched) return ResponseDto.SigninFail();

            accessToken = jwtProvider.create(userId);
            if (accessToken == null) return ResponseDto.TokenCreateFail();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.DatabaseError();
        }
        return SignInResponseDto.success(accessToken);
    }
    @Override
    public ResponseEntity<ResponseDto> SignupSecond(SignupRequestSecondDto dto) {
        String userId = dto.getUserId();
        String password = dto.getPassword();
        String telNumber = dto.getTelNumber();
        String authNumber = dto.getAuthNumber();
        try {
            boolean isExsitedId = secondSignupRepository.existsByUserId(userId); 
            if (isExsitedId) return ResponseDto.Duplicated();

            boolean isExsitedTelNumber = secondSignupRepository.existsByTelNumber(telNumber); 
            if (isExsitedTelNumber) return ResponseDto.DuplicatedTelNumber();

            boolean isMatched = telAuthCheckRepository.existsByTelNumberAndAuthNumber(telNumber, authNumber); 
            if (!isMatched) return ResponseDto.TelauthFail();

            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);


            SecondSignupEntity secondSignupEntity = new SecondSignupEntity(dto);
            secondSignupRepository.save(secondSignupEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.DatabaseError();
        }
        return ResponseDto.Success();
    }
   

}
