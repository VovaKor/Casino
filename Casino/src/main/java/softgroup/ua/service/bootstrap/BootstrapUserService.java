package softgroup.ua.service.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softgroup.ua.authorization.AuthenticatedUser;
import softgroup.ua.jpa.RoleEntity;
import softgroup.ua.jpa.UserDataEntity;
import softgroup.ua.jpa.UserEntity;
import softgroup.ua.service.RoleService;
import softgroup.ua.service.UserService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author Stanislav Rymar
 * @author Vlad
 */
@Service
public class BootstrapUserService implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(BootstrapUserService.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Override
    @Transactional()
    public void afterPropertiesSet() throws Exception {
        logger.error("User Bootstrap");
        createSystemUser();
        createCommonUser();
    }

    @DependsOn("bootstrapRoles")
    private void createSystemUser() {
        login();
        if (userService.findUserById("admin") == null) {
            UserEntity user = new UserEntity("admin");
            user.setEmail("admin@admin.admin");
            user.setPassword(UserService.digest("12345"));
            user.getRolesList().add(roleService.getRoleById(1));
            userService.addUser(user);
            logger.debug("Admin user was created from bootstrap");
        }
    }

    private void login() {
        UserEntity bootstrapUser = new UserEntity("bootstrapUser", "123", BigDecimal.ZERO, "bootstrap@casino.com");
        List<RoleEntity> roles = Collections.singletonList(new RoleEntity(1,"root","Administrative user, has access to everything."));
        bootstrapUser.setRolesList(roles);
        AuthenticatedUser au = new AuthenticatedUser(bootstrapUser);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(au, null, au.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private void createCommonUser() {
        if(userService.findUserById("user") == null || userService.findUserDataById("user") == null){
            UserEntity user = new UserEntity("user");
            user.setBalance(new BigDecimal(10.90));
            user.setEmail("someemail@gmail.com");
            user.setPassword(UserService.digest("qwerty123"));
            user.getRolesList().add(roleService.getRoleById(4));
            UserDataEntity userData = new UserDataEntity("user");
            userData.setPassport("NV1235234");
            userData.setName("Liliana");
            userData.setSurname("Vess");
            userData.setBirthDay(new GregorianCalendar(1986, 3,14));
            userData.setTelephone("+309711122233");
            user.setUserData(userData);
            user.getRolesList().add(roleService.getRoleById(3));
            userService.addUser(user);
            logger.debug("Common user was created from bootstrap");
        }
    }
}
