package youngpil.backend.common.utill.object;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomeOAuth2User implements OAuth2User {

    private String name;
    private Map<String, Object> attributes;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean existed;

    public CustomeOAuth2User(String name, Map<String, Object> attributes, boolean existed) {
        this.name = name;
        this.attributes = attributes;
        this.authorities = AuthorityUtils.NO_AUTHORITIES;
        this.existed = existed;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
        
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    public boolean isExisted() {
        return existed;
    }
}
