package softgroup.ua.test.unit;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import softgroup.ua.jpa.AutomatEntity;
import softgroup.ua.jpa.GamesEntity;
import softgroup.ua.jpa.UserEntity;
import softgroup.ua.repository.UserRepository;
import softgroup.ua.repository.AutomatRepository;
import softgroup.ua.service.GamesService;

/**
 *
 * @author Anjey
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GamesServiceTest {

    @Autowired
    private GamesService gamesService;
    static Long gameId = Long.valueOf(100);
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AutomatRepository automatRepository;
    
    private UserEntity testUser;
    private AutomatEntity testAutomat;
    private GamesEntity testGame;

    @Before
    public void insertTestData() {
        testUser = new UserEntity();
        testUser.setLoginId("TestUser2");
        testUser.setPassword("passwd");
        testUser.setBalance(new BigDecimal(500));
        testUser.setEmail("test@casino.com");
        testUser.setLastLoginDate(new GregorianCalendar());
        userRepository.save(testUser);
        
        testAutomat = new AutomatEntity();
        testAutomat.setAutomatId(100);
        testAutomat.setAutomatName("TestAutomat");
        testAutomat.setDescription("TestAutomatDiscr");
        automatRepository.save(testAutomat);
        
    }

    @After
    public void deleteTestData() {
        automatRepository.delete(testAutomat.getAutomatId());
        userRepository.delete(testUser.getLoginId());
    }

    @Test
    public void addGameTest() {
        //GamesEntity game = new GamesEntity(new Long(100), new BigDecimal(500), new Date(System.currentTimeMillis()));
        GamesEntity game = new GamesEntity(gameId);
        game.setAmount(new BigDecimal(500.00));
        game.setDateTime(new GregorianCalendar(1990, 1, 8));
        game.setUser(testUser);
        game.setAutomat(testAutomat);
        gamesService.addGames(game);
        Assert.assertNotNull("New game wasn't found", gamesService.findGameById(game.getGameId()));
        gamesService.deleteGame(game.getGameId());
        Assert.assertNull("Can't delete game", gamesService.findGameById(game.getGameId())); 
    }

    
}