package youngpil.backend.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import youngpil.backend.dto.request.tool.PatchToolRequestDto;
import youngpil.backend.dto.request.tool.PostToolRequestDto;
import youngpil.backend.dto.response.ResponseDto;
import youngpil.backend.dto.response.getResponse.GetToolListResponseDto;
import youngpil.backend.dto.response.getResponse.GetToolResponseDto;
import youngpil.backend.entity.ToolEntity;
import youngpil.backend.repository.ToolRepository;
import youngpil.backend.service.ToolService;

@Service
@RequiredArgsConstructor
public class ToolServiceImplement implements ToolService{

    private final ToolRepository toolRepository;
    @Override
    public ResponseEntity<ResponseDto> postTool(PostToolRequestDto dto) {
        try {
            
            ToolEntity toolEntity = new ToolEntity(dto);
            toolRepository.save(toolEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.DatabaseError();
        }
        return ResponseDto.Success();
    }
    @Override
    public ResponseEntity<? super GetToolListResponseDto> getTool() {

        List<ToolEntity> toolEntities = new ArrayList<>();

        try {
            
            toolEntities = toolRepository.findByOrderByToolNumberDesc();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.DatabaseError();
        }
        return GetToolListResponseDto.success(toolEntities);
    }
    @Override
    public ResponseEntity<? super GetToolResponseDto> getToolSecond(Integer toolNumber) {
        ToolEntity toolEntity = null;

        try {
            toolEntity = toolRepository.findByToolNumber(toolNumber);
            if (toolEntity == null) return ResponseDto.NoExistTool();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.DatabaseError();
        }
        return GetToolResponseDto.success(toolEntity);
    }
    @Override
    public ResponseEntity<ResponseDto> patchTool(Integer toolNumber, PatchToolRequestDto dto) {
        try {
            
            ToolEntity toolEntity = toolRepository.findByToolNumber(toolNumber);
            if (toolEntity == null) return ResponseDto.NoExistTool();

            toolEntity.patch(dto);
            toolRepository.save(toolEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.DatabaseError();
        }
        return ResponseDto.Success();
    }
    @Override
    public ResponseEntity<ResponseDto> deleteTool(Integer toolNumber) {
        try {
            ToolEntity toolEntity = toolRepository.findByToolNumber(toolNumber);
            if (toolEntity == null) return ResponseDto.NoExistTool();

            toolRepository.delete(toolEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.DatabaseError();
        }
        return ResponseDto.Success();
    }
    
}
