package youngpil.backend.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import youngpil.backend.dto.request.SignupRequestDto;
import youngpil.backend.service.SignupService;
import youngpil.backend.tool.ResponseDto;

@RestController
@RequestMapping("api/v1/test")
@RequiredArgsConstructor
public class TestController {
    private final SignupService signupService;

    @GetMapping("/t1")
    public String test() {
        return "test";
    }

    @PostMapping("/t2")
    public ResponseEntity<String> secondTest(
        @RequestBody @Valid SignupRequestDto dto
    ) {
        ResponseEntity<String> response = signupService.Signup(dto);
        return response;        
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteTest(
        @PathVariable("userId") String userId
    ) {
        ResponseEntity<String> response = signupService.delete(userId);
        return response;
    }

    @GetMapping("")
    public ResponseEntity<String> getsql() {
        ResponseEntity<String> response = signupService.getsql();
        return response;
    }
}
