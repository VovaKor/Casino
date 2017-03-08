package softgroup.ua.service;

import org.springframework.stereotype.Component;
import softgroup.ua.api.Role;
import softgroup.ua.jpa.RolesEntity;

/**
 * Created by Вова on 08.03.2017.
 */
@Component
public class RolesMapper {
    /**
     * Maps internal JPA model to external REST model
     * @param rolesEntity internal role model
     * @return external REST role model
     */
    public Role fromInternal(RolesEntity rolesEntity) {
        Role role = null;
        if (rolesEntity != null) {
            role = new Role();
            role.roleId = rolesEntity.getRolesId();
            role.roleName = rolesEntity.getRoleName();
            role.description = rolesEntity.getDescription();
        }
        return role;
    }
}
