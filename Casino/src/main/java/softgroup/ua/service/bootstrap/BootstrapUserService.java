package softgroup.ua.service.bootstrap;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softgroup.ua.jpa.User;
import softgroup.ua.repository.UserRepository;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

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
            User user = new User();
            user.setBalance(new BigDecimal(0));
            user.setEmail("admin@admin.admin");
            user.setLoginId("admin");
            user.setPassword("12345");
            user.setLastLoginDate(new GregorianCalendar());
            userRepository.save(user);
            System.out.println("Created system user admin");
        }
    }
}
