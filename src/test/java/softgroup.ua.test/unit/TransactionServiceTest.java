package softgroup.ua.test.unit;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import softgroup.ua.jpa.TransactionEntity;
import softgroup.ua.jpa.UserEntity;
import softgroup.ua.service.TransactionService;

/**
 *
 * @author alexander
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionServiceTest {
    
    @Autowired
    private TransactionService transactionService;
    
    private static UserEntity user;
    
    //This method will be removed after UserService creation
    @BeforeClass
    public static void insertTestUser() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/casino", "app", "qwerty");
            stmt = connection.createStatement();
            stmt.executeUpdate("INSERT INTO user (login_id , password, roles_id, balance, email, last_login_date) "
                + "VALUES (\"TestUser\", \"passwd\", 0, 500, \"test@casino.com\", \"2016-05-30 23:04:12\");");
        } finally {
            if (null != stmt) {
                stmt.close();
            }
            if (null != connection) {
                connection.close();
            }
        }
        
        user = new UserEntity();
        user.setLoginId("TestUser");
        user.setPassword("passwd");
        user.setBalance(new BigDecimal(500));
        user.setEmail("test@casino.com");
        user.setLastLoginDate(Timestamp.valueOf("2016-05-30 23:04:12"));
        user.setRolesId(0);
    }
    
    //This method will be removed after UserService creation
    @AfterClass
    public static void deleteTestUser() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/casino", "app", "qwerty");
            stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM user WHERE login_id = \"TestUser\";");
        } finally {
            if (null != stmt) {
                stmt.close();
            }
            if (null != connection) {
                connection.close();
            }
        }   
    }
    
    @Test
    public void addTransactionTest() throws Exception {
        TransactionEntity transaction = new TransactionEntity(new Long(124), new Date(System.currentTimeMillis()), new BigDecimal(-50));
        
        transaction.setUser(user);
        transactionService.addTransaction(transaction);
        TransactionEntity testTransaction = transactionService.getTransactionById(transaction.getTransactionId());
        
        Assert.assertNotNull("New transaction wasn't found", testTransaction);
        
        transactionService.deleteTransaction(transaction.getTransactionId());
        testTransaction = transactionService.getTransactionById(transaction.getTransactionId());
        
        Assert.assertNull("Can't delete transaction", testTransaction);
    }
    
    @Test
    public void getAllTransactionsTest() throws Exception {
        TransactionEntity transaction = new TransactionEntity(new Long(125), new Date(System.currentTimeMillis()), new BigDecimal(150));
        transaction.setUser(user);
        int transactionsNumber = transactionService.getAll().size();
        transactionService.addTransaction(transaction);
        Assert.assertEquals(transactionsNumber + 1, transactionService.getAll().size());
        transactionService.deleteTransaction(transaction.getTransactionId());
        Assert.assertEquals(transactionsNumber, transactionService.getAll().size());
    }
    
    @Test
    public void getTransactionByIdTest() throws Exception {
        Long transactionId = new Long(126);
        TransactionEntity transaction = new TransactionEntity(transactionId, new Date(System.currentTimeMillis()), new BigDecimal(150));
        transaction.setUser(user);        
        transactionService.addTransaction(transaction);
        
        Assert.assertEquals(transactionId, transactionService.getTransactionById(transactionId).getTransactionId());
        transactionService.deleteTransaction(transaction.getTransactionId());
    }
    
}
