package youngpil.backend.service.implement;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import youngpil.backend.common.utill.object.AuthNumberCreator;
import youngpil.backend.dto.request.PostUserRequestDto;
import youngpil.backend.dto.request.SigninRequestDto;
import youngpil.backend.dto.request.TelAuthCheckRequestDto;
import youngpil.backend.dto.request.TelAuthRequestDto;
import youngpil.backend.entity.AuthEntity;
import youngpil.backend.entity.SignupEntity;
import youngpil.backend.entity.TelAuthEntity;
import youngpil.backend.provider.JwtProvider;
import youngpil.backend.provider.SmsProvider;
import youngpil.backend.repository.AuthRepository;
import youngpil.backend.repository.SignUpRepository;
import youngpil.backend.repository.TelAuthCheckRepository;
import youngpil.backend.repository.TelAuthRepository;
import youngpil.backend.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthserviceImplement implements AuthService {

    // private final AuthRepository authRepository;
    private final SignUpRepository signUpRepository;
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
    public String Telauth(TelAuthRequestDto dto) {

        try {
            String telNumber = dto.getTelNumber();
            boolean result = telAuthRepository.existsByTelNumber(telNumber);
            if (result) return "존재하는 전화번호 입니다.";

            String authNumber = AuthNumberCreator.number();

            boolean isSend = smsProvider.SendMessage(telNumber, authNumber);
            if(!isSend) return "인증번호가 틀립니다.";

            TelAuthEntity telAuthEntity = new TelAuthEntity(telNumber, authNumber);
            telAuthRepository.save(telAuthEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return "예외 발생";
        }
        return "성공";
    }
    @Override
    public String TelauthCheck(TelAuthCheckRequestDto dto) {

        try {
            String TelNumber = dto.getTelNumber();
            String AuthNumber = dto.getAuthNumber();
            
            boolean result = telAuthCheckRepository.existsByTelNumberAndAuthNumber(TelNumber, AuthNumber);
            if (!result) return "인증번호가 일치하지 않습니다.";
        } catch (Exception exception) {
            exception.printStackTrace();
            return "예외 발생";
        }
        return "성공";
    }

}
