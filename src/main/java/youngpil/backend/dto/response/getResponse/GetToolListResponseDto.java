package youngpil.backend.dto.response.getResponse;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import youngpil.backend.common.utill.object.Tool;
import youngpil.backend.dto.response.ResponseCode;
import youngpil.backend.dto.response.ResponseDto;
import youngpil.backend.dto.response.ResponseMessage;
import youngpil.backend.entity.ToolEntity;

@Getter
public class GetToolListResponseDto extends ResponseDto{
    
    private List<Tool> tools;

    private GetToolListResponseDto(List<ToolEntity> toolEntities) {
        super(ResponseCode.Success, ResponseMessage.Success);
        this.tools = Tool.getList(toolEntities);
    }
    public static ResponseEntity<GetToolListResponseDto> success(List<ToolEntity> toolEntities) {
        GetToolListResponseDto response = new GetToolListResponseDto(toolEntities);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
