package softgroup.ua.test.unit;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import softgroup.ua.jpa.Transaction;
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
    
    //This method will be removed after UserService creation
    public void insertTestUser() throws Exception {
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
    }
    
    //This method will be removed after UserService creation
    public void deleteTestUser() throws Exception {
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
    public void addTransaction() throws Exception {
        Transaction transaction = new Transaction(new Long(124), new Date(System.currentTimeMillis()), new BigDecimal(-50));
        
        UserEntity user = new UserEntity();
        user.setLoginId("TestUser");
        user.setPassword("passwd");
        user.setBalance(new BigDecimal(500));
        user.setEmail("test@casino.com");
        user.setLastLoginDate(Timestamp.valueOf("2016-05-30 23:04:12"));
        user.setRolesId(0);
        
        insertTestUser();
        
        transaction.setUser(user);
        transactionService.addTransaction(transaction);
        Transaction testTransaction = transactionService.getTransactionById(transaction.getTransactionId());
        
        Assert.assertNotNull("New transaction wasn't found", testTransaction);
        
        transactionService.deleteTransaction(transaction.getTransactionId());
        testTransaction = transactionService.getTransactionById(transaction.getTransactionId());
        
        Assert.assertNull("Can't delete transaction", testTransaction);
        
        deleteTestUser();
    }
    
}
