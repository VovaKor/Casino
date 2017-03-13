package softgroup.ua.test.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import softgroup.ua.api.AddRoleRequest;
import softgroup.ua.api.Role;
import softgroup.ua.api.RolesListReply;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Вова on 08.03.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RoleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private AddRoleRequest roleRequest;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        roleRequest = new AddRoleRequest();
        roleRequest.role = new Role();
        roleRequest.role.roleId = 1000;
        roleRequest.role.roleName = "TestREST";
        roleRequest.role.description = "Test rest role";
        objectMapper = new ObjectMapper();
    }

    @After
    public void tearDown() throws Exception {
        roleRequest = null;
        objectMapper = null;
    }

    @Test
    public void addRole() throws Exception {
        String content = objectMapper.writeValueAsString(roleRequest);
        MvcResult result = mockMvc.perform(post("/roles/add")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content)
        )
                .andExpect(status().isOk())
                .andReturn();
        String reply = result.getResponse().getContentAsString();
        RolesListReply rolesListReply = objectMapper.readValue(reply,RolesListReply.class);
        assertEquals("Return code isn't 0",0, rolesListReply.retcode.intValue());

    }
    @Test
    public void getRoleById() throws Exception {
        this.mockMvc.perform(get("/roles/byId/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("root")));
    }
    @Test
    public void updateRole() throws Exception {
        roleRequest.role.roleName = "New test rest name";
        roleRequest.role.description = "New test description";
        String content = objectMapper.writeValueAsString(roleRequest);
        MvcResult result = mockMvc.perform(post("/roles/update")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content)
        )
                .andExpect(status().isOk())
                .andReturn();
        String reply = result.getResponse().getContentAsString();
        RolesListReply rolesListReply = objectMapper.readValue(reply,RolesListReply.class);
        assertEquals("Return code isn't 0",0, rolesListReply.retcode.intValue());

    }

    @Test
    public void deleteRole() throws Exception {
        mockMvc.perform(get("/roles/delete/"+roleRequest.role.roleId)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isOk());
    }
    @Test
    public void getAllRoles() throws Exception {
        this.mockMvc.perform(get("/roles/all"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("root")))
                .andExpect(content().string(containsString("moderator")))
                .andExpect(content().string(containsString("player")))
                .andExpect(content().string(containsString("guest")));
    }

}