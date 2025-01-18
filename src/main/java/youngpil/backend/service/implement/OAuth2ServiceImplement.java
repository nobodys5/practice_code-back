package youngpil.backend.service.implement;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import youngpil.backend.common.utill.object.CustomeOAuth2User;
import youngpil.backend.entity.OAuth2Entity;
import youngpil.backend.provider.JwtProvider;
import youngpil.backend.repository.OAuth2Repository;

@Service
@RequiredArgsConstructor
public class OAuth2ServiceImplement extends DefaultOAuth2UserService {
    private final JwtProvider jwtProvider;
    private final OAuth2Repository oAuth2Repository;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException{

        OAuth2User oAuth2User = super.loadUser(request);
        String registration = request.getClientRegistration().getClientName().toLowerCase();

        String snsId = getSnsId(oAuth2User, registration);

        OAuth2Entity oAuth2Entity = oAuth2Repository.findBySnsIdAndJoinPath(snsId, snsId); 
        
        CustomeOAuth2User customeOAuth2User = null;

        if (oAuth2Entity == null) {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("snsId", snsId);
            attributes.put("joinPath", registration);
            customeOAuth2User = new CustomeOAuth2User(snsId, attributes, false);
        } else {
            String userId = oAuth2Entity.getUserId();
            String token = jwtProvider.create(userId);
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("accessToken", token);

            customeOAuth2User = new CustomeOAuth2User(userId, attributes, true);
        }

        return customeOAuth2User;
    }

    private String getSnsId(OAuth2User oAuth2User, String registration) {
        String snsId = null;

        if (registration.equals("kakao")) {
            snsId = oAuth2User.getName();
        }
        if (registration.equals("naver")) {
            Map<String, String> response = (Map<String,String>) oAuth2User.getAttributes().get("response");
            snsId = response.get("id");   
        }

        return snsId;
    }
}
