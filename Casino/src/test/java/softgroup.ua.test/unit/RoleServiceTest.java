package softgroup.ua.test.unit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import softgroup.ua.jpa.RoleEntity;
import softgroup.ua.service.RoleService;

/**
 * Created by Вова on 08.03.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoleServiceTest {


    @Autowired
    RoleService roleService;

    private RoleEntity roleEntity;
    private Integer roleId;

    @Before
    public void setUp() throws Exception {
        roleId = 100;
        roleEntity = new RoleEntity(roleId,"test","Test role");
    }

    @After
    public void tearDown() throws Exception {
        roleId = null;
        roleEntity = null;
    }
    @Test
    public void getRoleById() throws Exception {
        RoleEntity testRoleEntity = roleService.getRoleById(3);
        assertNotNull("Role not found",testRoleEntity);
    }

    @Test
    public void addUpdateDeleteRole() throws Exception {
        /*Adding role test*/
        roleService.addRole(roleEntity);
        RoleEntity testRoleEntity = roleService.getRoleById(roleId);
        assertNotNull("Test role not found.",testRoleEntity);
        /*Updating role test*/
        testRoleEntity.setRoleName("updatedTestName");
        testRoleEntity.setDescription("New test description");
        roleService.updateRole(testRoleEntity);
        testRoleEntity = roleService.getRoleById(roleId);
        assertEquals("Role name not updated","updatedTestName",testRoleEntity.getRoleName());
        assertEquals("Role description not updated","New test description",testRoleEntity.getDescription());
        /*Deleting role test*/
        roleService.deleteRole(roleId);
        testRoleEntity = roleService.getRoleById(roleId);
        assertNull("Test role not deleted.",testRoleEntity);
    }

    @Test
    public void getAllRoles() throws Exception {
        int count = roleService.getAllRoles().size();
        assert(count>=4);
    }

}