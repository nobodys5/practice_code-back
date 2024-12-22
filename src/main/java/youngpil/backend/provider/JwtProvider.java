package youngpil.backend.provider;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {
    
    @Value("${jwt.secret}")
    private String secretkey;

    public String create(String name) {

        Date expiredDate = Date.from(Instant.now().plus(4, ChronoUnit.HOURS));
        
        
        Key key = Keys.hmacShaKeyFor(secretkey.getBytes(StandardCharsets.UTF_8));

        String jwt = Jwts.builder()
        .signWith(key, SignatureAlgorithm.HS256)
        .setSubject(name)
        .setIssuedAt(new Date())
        .setExpiration(expiredDate)
        .compact();

        return jwt;
    }
}
