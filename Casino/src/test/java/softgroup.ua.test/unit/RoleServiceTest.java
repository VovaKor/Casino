package softgroup.ua.test.unit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import softgroup.ua.service.RoleService;

/**
 * Created by Вова on 08.03.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoleServiceTest {
    @Autowired
    RoleService roleService;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllRoles() throws Exception {
        int count = roleService.getAllRoles().size();
        assert(count>=4);
    }

}