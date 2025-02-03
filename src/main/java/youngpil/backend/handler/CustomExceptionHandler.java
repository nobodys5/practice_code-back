package youngpil.backend.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import youngpil.backend.dto.response.ResponseDto;

// 유효성 검사 예외 처리를 위한 핸들러
@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler({
        HttpMessageNotReadableException.class,
        MethodArgumentNotValidException.class
    })
    public ResponseEntity<ResponseDto> validException(Exception exception) {
        exception.printStackTrace();
        return ResponseDto.ValidationFail();
    }
}
