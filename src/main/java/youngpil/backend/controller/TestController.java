package youngpil.backend.controller;

import org.springframework.http.ResponseEntity;
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
import youngpil.backend.service.JwtService;
import youngpil.backend.service.SignupService;

@RestController
@RequestMapping("api/v1/test")
@RequiredArgsConstructor
public class TestController {
    private final SignupService signupService;
    private final JwtService jwtService;

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

    @GetMapping("/jwt/{name}")
    public String getjwt(
        @PathVariable("name") String name
    ) {
        String response = jwtService.getjwt(name);
        return response;
    }
    @PostMapping("/validate")
    public String getvalidate(@RequestBody String jwt) {
        String response = jwtService.validjwt(jwt);
        return response; 
        
    }
}
