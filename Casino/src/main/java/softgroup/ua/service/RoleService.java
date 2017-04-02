package softgroup.ua.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import softgroup.ua.jpa.RoleEntity;
import softgroup.ua.repository.RoleRepository;

import java.util.List;

/**
 * Created by Вова on 08.03.2017.
 */
@Service
@Transactional(timeout = 10)
public class RoleService {
    private static final Logger logger =  LoggerFactory.getLogger(RoleService.class);
    @Autowired
    RoleRepository roleRepository;

    public List<RoleEntity> getAllRoles(){
        return  roleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public RoleEntity getRoleById(int roleId) {
        return roleRepository.findByRoleId(roleId);
    }

    @Secured({"ROLE_ROOT"})
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public RoleEntity addRole(RoleEntity roleEntity) {
        logger.debug("Adding role \"%s\" with id \"%s\"", roleEntity.getRoleName(), roleEntity.getRoleId());
        roleEntity = roleRepository.save(roleEntity);
        return roleEntity;
    }

    @Secured({"ROLE_ROOT"})
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public RoleEntity updateRole(RoleEntity roleEntity) {
        logger.debug("Updating role \"%s\" with id \"%s\"", roleEntity.getRoleName(), roleEntity.getRoleId());
        roleEntity = roleRepository.save(roleEntity);
        return roleEntity;
    }

    @Secured({"ROLE_ROOT"})
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteRole(Integer roleId) {
        RoleEntity roleEntity = roleRepository.findOne(roleId);
        if (roleEntity != null) {
            logger.debug("Deleting role \"%s\" with id \"%s\"", roleEntity.getRoleName(), roleEntity.getRoleId());
            roleRepository.delete(roleEntity);
        }
    }
}
