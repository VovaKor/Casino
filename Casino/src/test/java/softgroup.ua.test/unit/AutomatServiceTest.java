package softgroup.ua.test.unit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import softgroup.ua.jpa.AutomatEntity;
import softgroup.ua.service.AutomatService;

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

    private AutomatEntity testEntity;
    private Integer testId;

    @Before
    public void setUp() throws Exception {
        testId = 1000;
        testEntity = new AutomatEntity(testId,"Test automat","Test description");
    }

    @After
    public void tearDown() throws Exception {
        testId = null;
        testEntity = null;
    }
    @Test
    public void getAutomatById() throws Exception {
        assertNotNull("Automat not found",automatService.getAutomatById(1));
    }

    @Test
    public void addUpdateDeleteAutomat() throws Exception {
        /*Adding automat test*/
        automatService.addAutomat(testEntity);
        assertNotNull("Test automat not found.",automatService.getAutomatById(testId));
        /*Updating automat test*/
        testEntity.setAutomatName("updatedTestName");
        testEntity.setDescription("New test description");
        automatService.updateAutomat(testEntity);
        testEntity = automatService.getAutomatById(testId);
        assertEquals("Automat name not updated","updatedTestName",testEntity.getAutomatName());
        assertEquals("Automat description not updated","New test description",testEntity.getDescription());
        /*Deleting automat test*/
        automatService.deleteAutomat(testId);
        assertNull("Test automat not deleted.",automatService.getAutomatById(testId));
    }

    @Test
    public void getAllAutomats() throws Exception {
        int count = automatService.getAllAutomats().size();
        assert(count>=1);
    }
}
