package youngpil.backend.dto.response.getResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import youngpil.backend.dto.response.ResponseCode;
import youngpil.backend.dto.response.ResponseDto;
import youngpil.backend.dto.response.ResponseMessage;
import youngpil.backend.entity.SecondSignupEntity;

@Getter
public class GetSignInResponseDto extends ResponseDto{
    
    private String userId;
    private String name;
    private String telNumber;

    public GetSignInResponseDto(SecondSignupEntity secondSignupEntity) {
        super(ResponseCode.Success, ResponseMessage.Success);
        this.userId = secondSignupEntity.getUserId();
        this.name = secondSignupEntity.getName();
        this.telNumber = secondSignupEntity.getTelNumber();
    }

    public static ResponseEntity<GetSignInResponseDto> success(SecondSignupEntity secondSignupEntity) {
        GetSignInResponseDto responseBody = new GetSignInResponseDto(secondSignupEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
