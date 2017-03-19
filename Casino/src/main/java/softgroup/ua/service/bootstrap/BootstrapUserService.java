package softgroup.ua.service.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softgroup.ua.jpa.User;
import softgroup.ua.jpa.UserData;
import softgroup.ua.service.UserService;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

/**
 * Created by Vlad on 05.03.2017.
 */
@Service
public class BootstrapUserService implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(BootstrapUserService.class);

    @Autowired
    private UserService userService;

    @Override
    @Transactional()
    public void afterPropertiesSet() throws Exception {
        createSystemUser();
        createCommonUser();
    }

    private void createSystemUser() {
        if (userService.findUserById("admin") == null) {
            User user = new User("admin");
            user.setBalance(new BigDecimal(0));
            user.setEmail("admin@admin.admin");
            user.setPassword("12345");
            userService.addUser(user);
            logger.debug("Admin user was created from bootstrap");
        }
    }

    private void createCommonUser() {
        if(userService.findUserById("user") == null || userService.findUserDataById("user") == null){
            User user = new User("user");
            user.setBalance(new BigDecimal(10.90));
            user.setEmail("someemail@gmail.com");
            user.setPassword("qwerty123");
            UserData userData = new UserData("user");
            userData.setPassport("NV1235234");
            userData.setName("Liliana");
            userData.setSurname("Vess");
            userData.setBirthDay(new GregorianCalendar(1986, 3,14));
            userData.setTelephone("+309711122233");
            user.setUserData(userData);
            userService.addUser(user);
            logger.debug("Common user was created from bootstrap");
        }
    }
}
