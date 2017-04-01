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
import softgroup.ua.api.AddAutomatRequest;
import softgroup.ua.api.Automat;
import softgroup.ua.api.AutomatsListReply;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
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
public class AutomatControllerTest {


    @Autowired
    private MockMvc mockMvc;

    private AddAutomatRequest automatRequest;
    private ObjectMapper objectMapper;
    private String requestContent;
    private MvcResult result;
    private String responseContent;
    private AutomatsListReply automatsListReply;

    @Before
    public void setUp() throws Exception {
        automatRequest = new AddAutomatRequest();
        automatRequest.automat = new Automat();
        automatRequest.automat.automatId = 1000;
        automatRequest.automat.automatName = "Rest test";
        automatRequest.automat.description = "Rest test automat";
        objectMapper = new ObjectMapper();
    }

    @After
    public void tearDown() throws Exception {
        automatRequest = null;
        objectMapper = null;
        requestContent = null;
        result = null;
        responseContent = null;
        automatsListReply = null;
    }
    @Test
    public void addUpdateDeleteAutomat() throws Exception {
        /* Adding new automat test*/
        requestContent = objectMapper.writeValueAsString(automatRequest);
        result = mockMvc.perform(post("/automats/add")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestContent)
        )
                .andExpect(status().isOk())
                .andReturn();
        responseContent = result.getResponse().getContentAsString();
        automatsListReply = objectMapper.readValue(responseContent,AutomatsListReply.class);
        assertEquals("Return code isn't 0",0, automatsListReply.retcode.intValue());
        /* Updating new automat test*/
        automatRequest.automat.automatName = "New test rest name";
        automatRequest.automat.description = "New test description";
        requestContent = objectMapper.writeValueAsString(automatRequest);
        result = mockMvc.perform(post("/automats/update")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestContent)
        )
                .andExpect(status().isOk())
                .andReturn();
        responseContent = result.getResponse().getContentAsString();
        automatsListReply = objectMapper.readValue(responseContent,AutomatsListReply.class);
        assertEquals("Return code isn't 0",0, automatsListReply.retcode.intValue());
        /* Deleting new automat test*/
        mockMvc.perform(get("/automats/delete/"+automatRequest.automat.automatId)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isOk());
    }
    @Test
    public void getAutomatById() throws Exception {
        this.mockMvc.perform(get("/automats/byId/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Slot machine")));
    }

    @Test
    public void getAllAutomats() throws Exception {
        this.mockMvc.perform(get("/automats/all"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Slot machine")));
    }
}
