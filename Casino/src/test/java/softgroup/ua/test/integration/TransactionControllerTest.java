/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softgroup.ua.test.integration;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import softgroup.ua.jpa.TransactionEntity;
import softgroup.ua.service.TransactionService;
import softgroup.ua.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.containsString;
import softgroup.ua.jpa.User;
import softgroup.ua.utils.EntityIdGenerator;
/**
 *
 * @author alexche
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TransactionControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    TransactionService transactionService;
    @Autowired
    UserService userService;
    
    TransactionEntity transactionEntity;
    User testUser;
    
    @Before
    public void setUp() {
        testUser = new User();
        testUser.setBalance(new BigDecimal(30));
        testUser.setEmail("test@casino.com");
        testUser.setLoginId("TestTransactionUser");
        testUser.setPassword("qwerty");
        testUser.setLastLoginDate(new GregorianCalendar());
        userService.addUser(testUser);
        
        transactionEntity = new TransactionEntity(EntityIdGenerator.random(), new Date(System.currentTimeMillis()), new BigDecimal(150));
        transactionEntity.setInfo("Transaction information");
        transactionEntity.setUser(userService.getUserById("admin"));
        transactionService.save(transactionEntity);
    }
    
    @After
    public void tearDown() {
        transactionService.deleteTransaction(transactionEntity.getTransactionId());
        userService.deleteUser(testUser.getLoginId());
    }
    
    @Test
    public void getAllTransactionsTest() throws  Exception {
        this.mockMvc.perform(get("/transactions/all"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(transactionEntity.getUser().getLoginId())))
                .andExpect(content().string(containsString(String.valueOf(transactionEntity.getTransactionId()))))
                .andExpect(content().string(containsString(String.valueOf(transactionEntity.getAmount()))));
    }
}
