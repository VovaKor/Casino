package softgroup.ua.test.unit;

import java.math.BigDecimal;
import java.util.Date;
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
import softgroup.ua.jpa.User;
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
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AutomatRepository automatRepository;
    
    private User testUser;
    private AutomatEntity testAutomat;
    private GamesEntity testGame;

    @Before
    public void insertTestGame() {
        testUser = new User();
        testUser.setLoginId("TestUser");
        testUser.setPassword("passwd");
        testUser.setBalance(new BigDecimal(500));
        testUser.setEmail("test@casino.com");
        testUser.setLastLoginDate(new GregorianCalendar());
        userRepository.save(testUser);
        
        testAutomat = new AutomatEntity();
        testAutomat.setAutomatId(1);
        testAutomat.setAutomatName("TestAutomat");
        testAutomat.setDescription("TestAutomatDiscr");
        automatRepository.save(testAutomat);
        
        //testGame = new GamesEntity(Long.valueOf(100), new BigDecimal(500), new Date(System.currentTimeMillis()));
        //testGame.setAutomatId(testAutomat);
        //testGame.setUser(testUser);
        //gamesService.save(testGame);
    }

    @After
    public void deleteTestGame() {
        gamesService.delete(testGame.getGameId());
        //automatRepository.delete(testAutomat.getAutomatId());
        userRepository.delete(testUser.getLoginId());
    }

    @Test
    public void addGameTest() {
        GamesEntity game = new GamesEntity(Long.valueOf(100), new BigDecimal(500), new Date(System.currentTimeMillis()));
        game.setUser(testUser);
        gamesService.addGames(game);
        Assert.assertNotNull("New transaction wasn't found", gamesService.findGameById(game.getGameId()));
        gamesService.deleteGame(game.getGameId());
        Assert.assertNull("Can't delete transaction", gamesService.findGameById(game.getGameId())); 
    }

    
}