package youngpil.backend.service.implement;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import youngpil.backend.provider.JwtProvider;
import youngpil.backend.service.JwtService;

@Service
@RequiredArgsConstructor
public class JwtServiceImplement implements JwtService{
    private final JwtProvider jwtProvider;

    @Override
    public String getjwt(String name) {
        String jwt = jwtProvider.create(name);

        return jwt;
    }
    
}
