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
import softgroup.ua.jpa.enums.Gender;
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
            //Role id was set to 4, but there isn't any role associated
            //with such id in AuthenticationUser, so this user
            //had no UserAuthority
            //            user.getRolesList().add(roleService.getRoleById(4));

            //If roleId > 1, which means that this authenticated user will have
            //ROLE_USER and ROLE_MODERATOR UserAuthority, but in GameControllerTest we need
            //ROLE_ROOT for AutomatService.updateAutomat method.
            //This causes to Access is denied exception

            user.getRolesList().add(roleService.getRoleById(1));
            UserDataEntity userData = new UserDataEntity("user");
            userData.setPassport("NV1235234");
            userData.setName("Liliana");
            userData.setSurname("Vess");
            userData.setBirthDay(new GregorianCalendar(1986, 3,14));
            userData.setTelephone("+309711122233");
            //Firstly gender wasn't set, that's why UserMapper failed
            //on 45-th row
            userData.setGender(Gender.MALE);
            user.setUserData(userData);
            //Role was set twice
//            user.getRolesList().add(roleService.getRoleById(3));
            userService.addUser(user);
            logger.debug("Common user was created from bootstrap");
        }
    }
}
