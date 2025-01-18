package youngpil.backend.handler;

import java.io.IOException;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import youngpil.backend.common.utill.object.CustomeOAuth2User;

@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
 
    @Override
     public void onAuthenticationSuccess(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication
    ) throws IOException,ServletException {
        CustomeOAuth2User customeOAuth2User = (CustomeOAuth2User) authentication.getPrincipal();
        String userId = customeOAuth2User.getName();
        Map<String, Object> attributes = customeOAuth2User.getAttributes();
        boolean existed = customeOAuth2User.isExisted();

        if (existed) {
            String accessToken = (String) attributes.get("accessToken");
            response.sendRedirect("http://localhost:3000/sns-success?accessToken=" + accessToken + "&expiration=36000");
        }else {
            String snsId = (String) attributes.get("snsId");
            String joinPath = (String) attributes.get("joinPath");
            response.sendRedirect("http://localhost:3000/auth?snsId=" + snsId + "&joinPath=" + joinPath);
        } 
        
    }
}
