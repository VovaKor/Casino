package softgroup.ua.test.unit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import softgroup.ua.authorization.AuthenticatedUser;
import softgroup.ua.jpa.AutomatEntity;
import softgroup.ua.jpa.GamesEntity;
import softgroup.ua.jpa.UserEntity;
import softgroup.ua.service.AutomatService;
import softgroup.ua.service.GamesService;
import softgroup.ua.service.UserService;
import softgroup.ua.utils.EntityIdGenerator;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Вова on 08.03.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AutomatServiceTest {
    @Autowired
    AutomatService automatService;
    @Autowired
    GamesService historyService;
    @Autowired
    UserService userService;

    private AutomatEntity testEntity;
    private Integer automatId;
    private GamesEntity gameHistory;
    private long historyId;

    @Before
    public void setUp() throws Exception {
        automatId = 1000;
        historyId = EntityIdGenerator.random();
        UserEntity userEntity = userService.authenticateUser("admin", "12345");
        AuthenticatedUser authenticatedUser = new AuthenticatedUser(userEntity);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(authenticatedUser, null, authenticatedUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        testEntity = new AutomatEntity(automatId,"Test automat","Test description");
        gameHistory = new GamesEntity(historyId, BigDecimal.TEN, new GregorianCalendar());
        //testEntity.getGamesList().add(gameHistory);
        gameHistory.setAutomat(testEntity);
        gameHistory.setUser(userEntity);

    }

    @After
    public void tearDown() throws Exception {
        automatId = null;
        testEntity = null;
        gameHistory = null;
    }
    @Test
    public void getAutomatById() throws Exception {
        assertNotNull("Automat not found",automatService.getAutomatById(1));
    }

    @Test
    public void addUpdateDeleteAutomat() throws Exception {
        /*Adding automat test*/
        automatService.addAutomat(testEntity);
        historyService.addGames(gameHistory);
        testEntity = automatService.getAutomatById(automatId);
        assertNotNull("Test automat not found.",testEntity);

        /*Updating automat test*/
        testEntity.getGamesList().add(gameHistory);
        testEntity.setAutomatName("updatedTestName");
        testEntity.setDescription("New test description");
        automatService.updateAutomat(testEntity);
        testEntity = automatService.getAutomatById(automatId);
        assertEquals("Automat name not updated","updatedTestName",testEntity.getAutomatName());
        assertEquals("Automat description not updated","New test description",testEntity.getDescription());
        assertEquals("Automat history not exist",1,testEntity.getGamesList().stream().filter(gamesEntity -> gamesEntity.getGameId()== historyId).count());
        /*Deleting automat test*/
        automatService.deleteAutomat(automatId);
        assertNull("Test automat not deleted.",automatService.getAutomatById(automatId));

        historyService.deleteGame(historyId);
        assertNull("Test history not deleted.",historyService.findGameById(historyId));
    }

    @Test
    public void getAllAutomats() throws Exception {
        int count = automatService.getAllAutomats().size();
        assert(count>=1);
    }
}
