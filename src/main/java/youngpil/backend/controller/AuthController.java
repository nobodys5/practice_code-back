package youngpil.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import youngpil.backend.dto.request.IdCheckRequestDto;
import youngpil.backend.dto.request.PostUserRequestDto;
import youngpil.backend.dto.request.SigninRequestDto;
import youngpil.backend.dto.request.TelAuthCheckRequestDto;
import youngpil.backend.dto.request.TelAuthRequestDto;
import youngpil.backend.dto.response.ResponseDto;
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
        String result = authService.Telauth(dto);
        return result;
    } 

    @PostMapping("/telauth-check")
    public String telAuthCheck(
        @RequestBody @Valid TelAuthCheckRequestDto dto
    ) {
        String response = authService.TelauthCheck(dto);
        return response;
    }

    @PostMapping("/id-check")
    public ResponseEntity<ResponseDto> idCheck(
        @RequestBody @Valid IdCheckRequestDto dto
    ) {
        ResponseEntity<ResponseDto> response = authService.idCheck(dto);
        return response;
    }
}
