package youngpil.backend.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import youngpil.backend.entity.SignupEntity;
import youngpil.backend.provider.JwtProvider;
import youngpil.backend.repository.SignUpRepository;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final SignUpRepository signUpRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

            try {
                String token = parserBearerToken(request);
                if (token == null) {
                 filterChain.doFilter(request, response);
                 return;
                }    
     
                String subject = jwtProvider.validate(token);
                if (subject == null) {
                 filterChain.doFilter(request, response);
     
                 return;
                }

                // SignupEntity signupEntity = signUpRepository.findByUserId(subject);
                // if (signupEntity == null) {
                //     filterChain.doFilter(request, response);
                //     return;
                // }
                
                List<GrantedAuthority> roles = AuthorityUtils.NO_AUTHORITIES;
                if (subject.equals("qwer1234")) {
                    roles = new ArrayList<>();
                    roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                }

                // context를 생성하여 토큰 정보를 저장
                AbstractAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(subject, null, roles);

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                securityContext.setAuthentication(authenticationToken);   
                SecurityContextHolder.setContext(securityContext);             


            } catch (Exception exception) {
                exception.printStackTrace();
            }
            filterChain.doFilter(request, response);
            
          
    }

    private String parserBearerToken(HttpServletRequest request) {

        String authorization = request.getHeader("Authorization");

        boolean hasBearer = StringUtils.hasText(authorization);
        if (!hasBearer) return null;
        boolean isBearer = authorization.startsWith("Bearer ");
        if (!isBearer) return null;

        String token = authorization.substring(7);

        return token;
    }
}