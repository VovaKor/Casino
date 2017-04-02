package softgroup.ua.test.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import softgroup.ua.api.LoginReply;
import softgroup.ua.api.LoginRequest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by alexander on 29.03.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AuthorizationControllerTest {

    public final static String AUTH_HTTP_HEADER ="X-Authorization";
    private static String token = null;
    @Autowired
    MockMvc mockMvc;

    @Test
    public void login() throws Exception {
        if(token!=null){
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
        LoginReply lr = om.readValue(reply, LoginReply.class);
        token = lr.token;
    }
}
