package youngpil.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import youngpil.backend.dto.request.tool.PostToolRequestDto;
import youngpil.backend.dto.response.ResponseDto;
import youngpil.backend.dto.response.getResponse.GetToolListResponseDto;
import youngpil.backend.dto.response.getResponse.GetToolResponseDto;
import youngpil.backend.service.ToolService;

@RestController
@RequestMapping("/api/v1/tool")
@RequiredArgsConstructor
public class ToolController {
    
    private final ToolService toolService;

    @PostMapping(value = {"", "/"})
    public ResponseEntity<ResponseDto> postTool(
        @RequestBody @Valid PostToolRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = toolService.postTool(requestBody);
        return response;
    }

    @GetMapping(value = {"", "/"})
    public ResponseEntity<? super GetToolListResponseDto> getTool() {
        ResponseEntity<? super GetToolListResponseDto> response = toolService.getTool();
        return response;
    }
    @GetMapping("/{toolNumber}")
    public ResponseEntity<? super GetToolResponseDto> getToolSeconds(
        @PathVariable("toolNumber") Integer toolNumber
    ) {
        ResponseEntity<? super GetToolResponseDto> response = toolService.getToolSecond(toolNumber);
        return response;
    }
}
