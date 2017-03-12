package softgroup.ua.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softgroup.ua.jpa.RoleEntity;
import softgroup.ua.repository.RoleRepository;

import java.util.List;

/**
 * Created by Вова on 08.03.2017.
 */
@Service
public class RoleService {
    private static final Logger logger =  LoggerFactory.getLogger(RoleService.class);
    @Autowired
    RoleRepository roleRepository;
    public List<RoleEntity> getAllRoles(){
        return  roleRepository.findAll();
    }

    public RoleEntity getRoleById(int roleId) {
        return roleRepository.findByRoleId(roleId);
    }

    public RoleEntity addRole(RoleEntity roleEntity) {
        logger.debug("Adding role \"%s\" with id \"%s\"", roleEntity.getRoleName(), roleEntity.getRoleId());
        roleEntity = roleRepository.save(roleEntity);
        return roleEntity;
    }

    public RoleEntity updateRole(RoleEntity roleEntity) {
        logger.debug("Updating role \"%s\" with id \"%s\"", roleEntity.getRoleName(), roleEntity.getRoleId());
        roleEntity = roleRepository.save(roleEntity);
        return roleEntity;
    }

    public void deleteRole(Integer roleId) {
        RoleEntity roleEntity = roleRepository.findOne(roleId);
        if (roleEntity != null) {
            logger.debug("Deleting role \"%s\" with id \"%s\"", roleEntity.getRoleName(), roleEntity.getRoleId());
            roleRepository.delete(roleEntity);
        }
    }
}
