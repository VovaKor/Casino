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
import softgroup.ua.api.AddFeedbackRequest;
import softgroup.ua.api.FeedbackListReply;
import softgroup.ua.api.LoginReply;
import softgroup.ua.api.LoginRequest;
import softgroup.ua.jpa.Feedback;
import softgroup.ua.service.mappers.FeedbackMapper;
import softgroup.ua.service.FeedbackService;

import java.sql.Timestamp;
import java.util.Date;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FeedbackControllerTest {

    public final static String AUTH_HTTP_HEADER = "X-Authorization";
    private static String token = null;


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private FeedbackMapper feedbackMapper;

    private Feedback feedback;

    @Before
    public void setUp() throws Exception {
        login();
        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(stamp.getTime());
        feedback = new Feedback();
        feedback.setFeedbackId(111l);
        feedback.setMessage("message");
        feedback.setEmail("alukin@gmail.com");
        feedback.setMessageTime(date);
        feedbackService.addFeedback(feedback);

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

    @After
    public void tearDown() {
        feedbackService.deleteFeedback(111l);
    }

    @Test
    public void getAllFeedbackTest() throws Exception {
        this.mockMvc.perform(get("/feedback/all").header(AUTH_HTTP_HEADER, token))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("111")))
                .andExpect(content().string(containsString("message")))
                .andExpect(content().string(containsString("alukin@gmail.com")));
    }

    @Test
    public void findFeedbackByIdTest() throws Exception {
        this.mockMvc.perform(get("/feedback/byId/111").header(AUTH_HTTP_HEADER, token))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("alukin")));
    }

    @Test
    public void addFeedbackTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        AddFeedbackRequest addFeedbackRequest = new AddFeedbackRequest();
        addFeedbackRequest.feedback = feedbackMapper.fromInternal(feedback);
        String content = objectMapper.writeValueAsString(addFeedbackRequest);
        MvcResult result = mockMvc.perform(post("/feedback/add")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header(AUTH_HTTP_HEADER, token)
                        .content(content)
        )
                .andExpect(status().isOk())
                .andReturn();
        String reply = result.getResponse().getContentAsString();
        FeedbackListReply feedbackListReply = objectMapper.readValue(reply, FeedbackListReply.class);
        assertEquals("Return code isn't 0", 0, feedbackListReply.retcode.intValue());
    }


}
