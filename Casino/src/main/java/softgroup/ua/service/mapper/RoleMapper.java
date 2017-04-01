package softgroup.ua.service.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import softgroup.ua.api.Role;
import softgroup.ua.jpa.RoleEntity;
import softgroup.ua.repository.RoleRepository;

/**
 * Created by Вова on 08.03.2017.
 */
@Component
public class RoleMapper implements GenericMapper<RoleEntity, Role>{
    private static final Logger logger =  LoggerFactory.getLogger(RoleMapper.class);
    @Autowired
    RoleRepository roleRepository;
    /**
     * Maps internal JPA model to external REST model
     * @param roleEntity internal role model
     * @return external REST role model
     */
    @Override
    public Role fromInternal(RoleEntity roleEntity) {
        Role role = null;
        if (roleEntity != null) {
            role = new Role();
            role.roleId = roleEntity.getRoleId();
            role.roleName = roleEntity.getRoleName();
            role.description = roleEntity.getDescription();
        }
        return role;
    }
    /**
     * Maps external REST model to internal role entity;
     * If role does not exists in DB then creates new. If role already exists
     * then fetches role from DB and sets all fields from external REST model
     * @param role REST model
     * @return internal RoleEntity with all required fields set
     */
    @Override
    public RoleEntity toInternal(Role role) {
        RoleEntity roleEntity = null;
        if (role.roleId != null) {
            roleEntity = roleRepository.findOne(role.roleId);
        }
        if (roleEntity == null) {
            logger.debug("Creating new role");
            roleEntity = new RoleEntity();
            roleEntity.setRoleId(role.roleId);
        }
        logger.debug("Updating existing role");
        roleEntity.setRoleName(role.roleName);
        roleEntity.setDescription(role.description);
        return roleEntity;
    }
}
