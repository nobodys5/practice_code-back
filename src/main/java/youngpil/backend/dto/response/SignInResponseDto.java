package youngpil.backend.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;

@Getter
public class SignInResponseDto extends ResponseDto {
    
    private String accessToken;
    private Integer expiration;

    private SignInResponseDto(String accessToken) {
        // 부모 클래스에서 빈 생성자가 없을 경우 자식 클래스에서 부모클래스 호출해줘야함
        super(ResponseCode.Success, ResponseMessage.Success);
        this.accessToken = accessToken;
        // 만료기간시간과 일치해야 오류가 나지않는다.
        this.expiration = 10 * 60 * 60;
    }

    public static ResponseEntity<SignInResponseDto> success(String accessToken) {
        SignInResponseDto responseBody = new SignInResponseDto(accessToken);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
