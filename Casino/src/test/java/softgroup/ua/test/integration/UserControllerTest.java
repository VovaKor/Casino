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
import softgroup.ua.api.*;
import softgroup.ua.jpa.UserEntity;
import softgroup.ua.repository.UserRepository;
import softgroup.ua.service.UserService;
import softgroup.ua.service.mappers.UserMapper;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Stanislav Rymar
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {

    final static String AUTH_HTTP_HEADER = "X-Authorization";
    private static String token = null;
    private UserEntity userEntity;

    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    @Before
    public void setUp() throws Exception {
        login();
        userEntity = new UserEntity("integrationUserTest", "asdfg234sdf", new BigDecimal(12.21), "test@email.com");
        userRepository.save(userEntity);
    }

    @After
    public void tearDown() throws Exception {
        userRepository.delete(userEntity.getLoginId());
    }

    public void login() throws Exception {
        if (token != null) {
            return;
        }
        LoginRequest rq = new LoginRequest();
        rq.loginId = "admin";
        rq.password = "12345";
        ObjectMapper om = new ObjectMapper();
        String content = om.writeValueAsString(rq);
        MvcResult result = mockMvc.perform(post("/auth")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content)
        )
                .andExpect(status().isOk())
                .andReturn();
        String reply = result.getResponse().getContentAsString();
        token = om.readValue(reply, LoginReply.class).token;
    }


    @Test
    public void getAllUsersTest() throws Exception {
        this.mockMvc.perform(get("/users/all")
                .header(AUTH_HTTP_HEADER, token))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(userEntity.getLoginId())))
                .andExpect(content().string(containsString(String.valueOf(userEntity.getEmail()))));
    }

    @Test
    public void getUserByIdTest() throws Exception {
        this.mockMvc.perform(get("/users/integrationUserTest")
                .header(AUTH_HTTP_HEADER, token))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(userEntity.getLoginId())));
    }

    @Test
    public void addUser() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setUser(userMapper.fromInternal(userEntity));
        String content = objectMapper.writeValueAsString(addUserRequest);
        MvcResult result = mockMvc.perform(post("/register")
                .header(AUTH_HTTP_HEADER, token)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content)
        )
                .andExpect(status().isOk())
                .andReturn();
        String reply = result.getResponse().getContentAsString();
        UserListReply userListReply = objectMapper.readValue(reply, UserListReply.class);
        assertEquals("Return code isn't 0",0, userListReply.retcode.intValue());
    }
}
