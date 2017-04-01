package softgroup.ua.authorization;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by alexander on 29.03.17.
 */
public class UserAuthority implements GrantedAuthority {

    private String authority;

    public UserAuthority(AuthorityName authorityName) {
        this.authority = authorityName.name();
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
