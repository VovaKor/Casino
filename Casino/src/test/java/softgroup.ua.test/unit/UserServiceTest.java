package softgroup.ua.test.unit;

import org.junit.Before;
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

import static org.junit.Assert.*;

/**
 * @author Stanislav Rymar
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserServiceTest {


    @Autowired
    private UserService userService;

    @Before
    public void testPreparation() {
        userService.deleteAllUser();

        User testUser1 = new User("testUser1", "asdfg234sdf", new BigDecimal(33.21), "test@email.com");
        UserData testUserData1 = new UserData("testUser1", "NM3244234", "Nikolas", "Bolas", new GregorianCalendar(1995, 12, 13), "+380931111111");
        testUser1.setUserData(testUserData1);
        userService.addUser(testUser1);

        User testUser2 = new User("testUser2", "cljkvd7s", new BigDecimal(123.43), "test@email.com");
        UserData testUserData2 = new UserData("testUser2", "NM3247756", "Chandra", "Nalaar", new GregorianCalendar(1986, 3, 32), "+380932222222");
        testUser2.setUserData(testUserData2);
        userService.addUser(testUser2);

        User testUser3 = new User("testUser3", "Fdsf83223sdgk2399yhdji", new BigDecimal(323.21), "test@email.com");
        UserData testUserData3 = new UserData("testUser3", "NM3247534", "Nikolas", "Markov", new GregorianCalendar(1991, 8, 19), "+380933333333");
        testUser3.setUserData(testUserData3);
        userService.addUser(testUser3);

    }

    @Test
    public void addUserTest() {
        String loginId = "Erik63";

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

        assertNotNull("User not added!", userService.findUserById(loginId));
    }

    @Test
    public void updateUserTest() {
        User user = userService.findUserById("testUser3");
        user.setEmail("test2@email.com");
        userService.updateUser(user);

        assertEquals("test2@email.com", userService.findUserById("testUser3").getEmail());
    }

    @Test
    public void findUserByIdTest() {
        assertNotNull("User was not found!", userService.findUserById("testUser2"));
        assertNull("User was found!", userService.findUserById("erikSanchez12"));
    }

    @Test
    public void findUserByNameTest() {
        assertEquals("Wrong number if users!", 2, userService.findUserByName("Nikolas").size());
        assertEquals("Wrong number if users!", 0, userService.findUserByName("Rik").size());
    }

    @Test
    public void findUserBySurnameTest() {
        assertEquals("Wrong number if users!", 1, userService.findUserBySurname("Bolas").size());
        assertEquals("Wrong number if users!", 0,userService.findUserBySurname("Sanchez").size());
    }

    @Test
    public void findUserByNameAndSurnameTest(){
        assertEquals("Wrong number if users!", 1, userService.findUserByNameAndSurname("Nikolas", "Markov").size());
        assertEquals("Wrong number if users!", 0, userService.findUserByNameAndSurname("Rik", "Sanchez").size());
    }

    @Test
    public void findUserByPassport(){
        assertNotNull("User was not found!", userService.findUserByPassport("NM3244234"));
        assertNull("User was found!", userService.findUserByPassport("NM987342"));
    }

    @Test
    public void findUserByTelephone(){
        assertNotNull("User was not found!", userService.findUserByTelephone("+380931111111"));
        assertNull("User was found!", userService.findUserByTelephone("+380931231233"));
    }


    @Test
    public void deleteUser() {
        userService.deleteUser("testUser1");
        assertNull("User not deleted!", userService.findUserById("testUser1"));
    }

    @Test
    public void deleteAllUser() {
        userService.deleteAllUser();
        assertEquals("Users tables still has a user!", 0, userService.getAllUser().size());
    }

}
