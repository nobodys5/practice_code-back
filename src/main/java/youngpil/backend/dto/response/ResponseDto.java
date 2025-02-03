package youngpil.backend.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDto {

    private String code;
    private String message;

    public static ResponseEntity<ResponseDto> Success() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.Success, ResponseMessage.Success);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    public static ResponseEntity<ResponseDto> DatabaseError() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DATABASE_ERROR, ResponseMessage.DATABASE_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }
    public static ResponseEntity<ResponseDto> Duplicated() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DUPLICATED_USER_ID, ResponseMessage.DUPLICATED_USER_ID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
    public static ResponseEntity<ResponseDto> DuplicatedTelNumber() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DUPLICATED_TELNUMBER, ResponseMessage.DUPLICATED_TELNUMBER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
    public static ResponseEntity<ResponseDto> ValidationFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.Validation_Fail, ResponseMessage.Validation_Fail);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
