package youngpil.backend.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import youngpil.backend.dto.request.PostUserRequestDto;
import youngpil.backend.dto.request.SigninRequestDto;
import youngpil.backend.dto.request.TelAuthRequestDto;
import youngpil.backend.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("")
    public String getAuth(
        @AuthenticationPrincipal String principal
    ) {
        return principal;
    }

    @PostMapping("/signup")
    public String signUp(
        @RequestBody @Valid PostUserRequestDto dto
    ) {
        String result = authService.Signup(dto);
        return result;
    }

    @PostMapping("/sign-in")
    public String signin(
        @RequestBody @Valid SigninRequestDto requestBody
    ) {
        String result = authService.Signin(requestBody);
        return result;
    }

    @PostMapping("/tel-auth")
    public String telAuth(
        @RequestBody @Valid TelAuthRequestDto dto

    ) {
        return "성공";
    } 
    
}
