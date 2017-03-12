package softgroup.ua.service.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softgroup.ua.jpa.RoleEntity;
import softgroup.ua.repository.RoleRepository;

/**
 * Created by Вова on 12.03.2017.
 */
@Service
public class BootstrapRoles implements InitializingBean {
    private static final Logger logger =  LoggerFactory.getLogger(BootstrapRoles.class);
    @Autowired
    private RoleRepository roleRepository;
    @Override
    @Transactional
    public void afterPropertiesSet() throws Exception {
        createRoles();
    }
    private void createRoles() {
        if (roleRepository.findByRoleId(1)== null) {
            roleRepository.save(new RoleEntity(1,"root","Administrative user, has access to everything."));
            logger.debug("Created role \"root\"");
        }
        if (roleRepository.findByRoleId(2)== null) {
            roleRepository.save(new RoleEntity(2,"moderator","Can edit feedbacks, comments and content."));
            logger.debug("Created role \"moderator\"");
        }
        if (roleRepository.findByRoleId(3)== null) {
            roleRepository.save(new RoleEntity(3,"player","Can sign in, operate his balance, play games, edit his own personal data, view games history and site's content, leave comments and feedback."));
            logger.debug("Created role \"player\"");
        }
        if (roleRepository.findByRoleId(4)== null) {
            roleRepository.save(new RoleEntity(4,"guest","Can sign up, operate his temporary balance, view site's content, play games and leave feedback."));
            logger.debug("Created role \"guest\"");
        }
    }
}
