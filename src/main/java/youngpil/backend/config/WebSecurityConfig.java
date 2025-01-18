package youngpil.backend.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import youngpil.backend.filter.JwtAuthenticationFilter;
import youngpil.backend.handler.OAuth2SuccessHandler;
import youngpil.backend.service.implement.OAuth2ServiceImplement;

@Configurable
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final OAuth2ServiceImplement oAuth2Service;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity security) throws Exception {

        security
        .httpBasic(HttpBasicConfigurer::disable)
        .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .csrf(CsrfConfigurer::disable)
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .authorizeHttpRequests(request -> request
        .requestMatchers("anyone/**", "/auth/**", "oauth2/callback/*").permitAll()
        // .requestMatchers(HttpMethod.GET, "/api/v1/test/jwt/*").permitAll()
        // .requestMatchers("admin/**").hasRole("ADMIN")
        // .requestMatchers("user/**").authenticated()
        // .requestMatchers(HttpMethod.POST, "/notice").hasRole("ADMIN")
        .anyRequest().authenticated()
        )

        .exceptionHandling(exceptionHandling -> exceptionHandling
        .authenticationEntryPoint(new FailedAuthenticationEntryPoint()))

        .oauth2Login(oauth2 -> oauth2
        .redirectionEndpoint(endpoint -> endpoint.baseUri("/oauth2/callback/*"))
        .authorizationEndpoint(endpoint -> endpoint.baseUri("/api/v1/auth/sns-sign-in"))
        .userInfoEndpoint(endpoint -> endpoint.userService(oAuth2Service))
        .successHandler(oAuth2SuccessHandler)
        
        )

        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return security.build();
    }
    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}

class FailedAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
            
                authException.printStackTrace();
                response.setContentType("application/json;charset=UTF-8");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("{\"message\":\"인증 및 인가에 실패하였습니다.\"}");
    }
    
}
