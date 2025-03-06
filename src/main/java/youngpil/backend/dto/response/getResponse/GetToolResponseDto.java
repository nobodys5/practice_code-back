package youngpil.backend.dto.response.getResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import youngpil.backend.dto.response.ResponseCode;
import youngpil.backend.dto.response.ResponseDto;
import youngpil.backend.dto.response.ResponseMessage;
import youngpil.backend.entity.ToolEntity;

@Getter
public class GetToolResponseDto extends ResponseDto{
    
    private Integer toolNumber;
    private String name;
    private String purpose;
    private Integer count;

    private GetToolResponseDto(ToolEntity toolEntity) {
        super(ResponseCode.Success, ResponseMessage.Success);
        this.toolNumber = toolEntity.getToolNumber();
        this.name = toolEntity.getName();
        this.purpose = toolEntity.getPurpose();
        this.count = toolEntity.getCount();
    }

    public static ResponseEntity<GetToolResponseDto> success(ToolEntity toolEntity) {
        GetToolResponseDto responseBody = new GetToolResponseDto(toolEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
