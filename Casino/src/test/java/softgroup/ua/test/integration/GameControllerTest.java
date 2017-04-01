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
import softgroup.ua.api.AutomatsListReply;
import softgroup.ua.api.LoginReply;
import softgroup.ua.api.LoginRequest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Вова on 01.04.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final static String AUTH_HTTP_HEADER ="X-Authorization";
    private static String token = null;
    private MvcResult result;
    private String responseContent;
    private AutomatsListReply automatsListReply;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        if(token!=null){
            return;
        }
        LoginRequest rq = new LoginRequest();
        rq.login = "user";
        rq.password = "qwerty123";
        objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(rq);
        MvcResult result = mockMvc.perform(post("/auth")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content)
        )
                .andExpect(status().isOk())
                .andReturn();
        String reply = result.getResponse().getContentAsString();
        LoginReply lr = objectMapper.readValue(reply, LoginReply.class);
        token = lr.token;
    }

    @After
    public void tearDown() throws Exception {
        objectMapper = null;
        result = null;
        responseContent = null;
        automatsListReply = null;
    }

    @Test
    public void getGameResult() throws Exception {
        result = this.mockMvc.perform(get("/automats/byId/1/play")
                .header(AUTH_HTTP_HEADER, token)
        )
                .andExpect(status().isOk()).andReturn();
        responseContent = result.getResponse().getContentAsString();
        automatsListReply = objectMapper.readValue(responseContent,AutomatsListReply.class);
        assertEquals("Slots are empty",3, automatsListReply.automats.get(0).slots.size());
    }

}