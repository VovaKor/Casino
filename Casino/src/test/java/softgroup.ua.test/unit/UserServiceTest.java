package softgroup.ua.test.unit;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import softgroup.ua.jpa.User;
import softgroup.ua.jpa.UserData;
import softgroup.ua.service.UserService;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

/**
 * @author Stanislav Rymar
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserServiceTest {

    @Autowired
    UserService userService;
    static String loginId = "testUser";

    @Test
    public void addAndGetUserTest(){
        User user = new User(loginId);
        UserData userData = new UserData(loginId);
        user.setBalance(new BigDecimal(100.00));
        user.setEmail("test@email.ru");
        user.setPassword("passwordHash");
        userData.setPassport("SOMETEXT");
        userData.setBirthDay(new GregorianCalendar(1991, 19, 8));
        userData.setName("Unit test name");
        userData.setSurname("Unit test surname");
        userData.setTelephone("Unit test telephone");
        user.setUserData(userData);
        userService.addUser(user);

        User user2 = userService.getUserById(loginId);
        assertNotNull("User not added!", user2);
    }

//    @Test
//    public void deleteUser(){
//        userService.deleteUser("admin");
//        User user = userService.getUserById("admin");
//        assertNull(user);
//    }

}
