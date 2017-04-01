package softgroup.ua.authorization;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import softgroup.ua.jpa.RoleEntity;
import softgroup.ua.jpa.UserEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by alexander on 29.03.17.
 */
public class AuthenticatedUser implements UserDetails {

    private final UserEntity user;
    Collection<UserAuthority> authorities = new ArrayList<>();

    public AuthenticatedUser(UserEntity user) {
        this.user = user;
        List<RoleEntity> rolesList = user.getRolesList();
        for(RoleEntity r : rolesList){
            if(r.getRoleId() == 1) {
                authorities.add(new UserAuthority(AuthorityName.ROLE_ROOT));
            }
            if(r.getRoleId() <= 2) {
                authorities.add(new UserAuthority(AuthorityName.ROLE_MODERATOR));
            }
            if(r.getRoleId() <= 3) {
                authorities.add(new UserAuthority(AuthorityName.ROLE_USER));
            }
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLoginId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
