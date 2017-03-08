package softgroup.ua.service.bootstrap;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softgroup.ua.jpa.UserEntity;
import softgroup.ua.repository.UserRepository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Vlad on 05.03.2017.
 */
@Service
public class BootstrapUserService implements InitializingBean {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional()
    public void afterPropertiesSet() throws Exception {
        createSystemUser();
    }

    private void createSystemUser() {
        if (userRepository.findOne("admin") == null) {
            UserEntity user = new UserEntity();
            user.setBalance(new BigDecimal(0));
            user.setEmail("admin@admin.admin");
            user.setLoginId("admin");
            user.setPassword("12345");
            user.setRolesId(1);
            user.setLastLoginDate(new Timestamp(new Date().getTime()));
            userRepository.save(user);
            System.out.println("Created system user admin");
        }
    }
}
