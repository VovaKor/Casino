package softgroup.ua.service.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softgroup.ua.jpa.RolesEntity;
import softgroup.ua.repository.RolesRepository;

/**
 * Created by Вова on 12.03.2017.
 */
@Service
public class BootstrapRoles implements InitializingBean {
    private static final Logger logger =  LoggerFactory.getLogger(BootstrapRoles.class);
    @Autowired
    private RolesRepository rolesRepository;
    @Override
    @Transactional
    public void afterPropertiesSet() throws Exception {
        createRoles();
    }
    private void createRoles() {
        if (rolesRepository.findByRolesId(1).isEmpty()) {
            rolesRepository.save(new RolesEntity(1,"root","Administrative user, has access to everything."));
            logger.debug("Created role \"root\"");
        }
        if (rolesRepository.findByRolesId(2).isEmpty()) {
            rolesRepository.save(new RolesEntity(2,"moderator","Can edit feedbacks, comments and content."));
            logger.debug("Created role \"moderator\"");
        }
        if (rolesRepository.findByRolesId(3).isEmpty()) {
            rolesRepository.save(new RolesEntity(3,"player","Can sign in, operate his balance, play games, edit his own personal data, view games history and site's content, leave comments and feedback."));
            logger.debug("Created role \"player\"");
        }
        if (rolesRepository.findByRolesId(4).isEmpty()) {
            rolesRepository.save(new RolesEntity(4,"guest","Can sign up, operate his temporary balance, view site's content, play games and leave feedback."));
            logger.debug("Created role \"guest\"");
        }
    }
}
