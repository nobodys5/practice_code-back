package youngpil.backend.service;

import org.springframework.http.ResponseEntity;

import youngpil.backend.dto.request.tool.PatchToolRequestDto;
import youngpil.backend.dto.request.tool.PostToolRequestDto;
import youngpil.backend.dto.response.ResponseDto;
import youngpil.backend.dto.response.getResponse.GetToolListResponseDto;
import youngpil.backend.dto.response.getResponse.GetToolResponseDto;

public interface ToolService {
    
    ResponseEntity<ResponseDto> postTool(PostToolRequestDto dto);
    ResponseEntity<? super GetToolListResponseDto> getTool();
    ResponseEntity<? super GetToolResponseDto> getToolSecond(Integer toolNumber);
    ResponseEntity<ResponseDto> patchTool(Integer toolNumber, PatchToolRequestDto dto);
}
