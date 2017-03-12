package softgroup.ua.test.unit;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import softgroup.ua.jpa.TransactionEntity;
import softgroup.ua.jpa.User;
import softgroup.ua.repository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    private User testUser;
    private TransactionEntity testTransaction;

    @Before
    public void insertTestUserAndTransaction() {
        testUser = new User();
        testUser.setBalance(new BigDecimal(500));
        testUser.setEmail("test@casino.com");
        testUser.setLoginId("TransactionUser");
        testUser.setPassword("qwerty");
        testUser.setLastLoginDate(new GregorianCalendar());
        userRepository.save(testUser);

        testTransaction = new TransactionEntity(Long.valueOf(126), new Date(System.currentTimeMillis()), new BigDecimal(150));
        testTransaction.setInfo("Transaction information");
        testTransaction.setUser(testUser);
        transactionService.save(testTransaction);
    }

    @After
    public void deleteTestUserAndTransaction() {
        transactionService.delete(testTransaction.getTransactionId());
        userRepository.delete(testUser.getLoginId());
    }

    @Test
    public void addTransactionTest() {
        TransactionEntity transaction = new TransactionEntity(new Long(124), new Date(System.currentTimeMillis()), new BigDecimal(-50));
        transaction.setUser(testUser);
        transactionService.addTransaction(transaction);
        Assert.assertNotNull("New transaction wasn't found", transactionService.findTransactionById(transaction.getTransactionId()));
        Assert.assertNotNull("User wasn loaded", transactionService.findTransactionById(transaction.getTransactionId()).getUser());
        transactionService.deleteTransaction(transaction.getTransactionId());
        Assert.assertNull("Can't delete transaction", transactionService.findTransactionById(transaction.getTransactionId()));
    }

    @Test
    public void getAllTransactionsTest() {
        int transactionsAmount = transactionService.getAllTransactions().size();
        TransactionEntity transaction = new TransactionEntity(new Long(124), new Date(System.currentTimeMillis()), new BigDecimal(50));
        transaction.setUser(testUser);
        transactionService.addTransaction(transaction);
        Assert.assertTrue(transactionService.getAllTransactions().size() > transactionsAmount);
        transactionService.deleteTransaction(transaction.getTransactionId());
        Assert.assertEquals(transactionsAmount, transactionService.getAllTransactions().size());
    }

    @Test
    public void findTransactionByIdTest() {
        TransactionEntity transaction = transactionService.findTransactionById(testTransaction.getTransactionId());
        Assert.assertEquals(testTransaction.getTransactionId(), transaction.getTransactionId());
    }

    @Test
    public void findTransactionByDateTimeTest() {
        Assert.assertTrue(transactionService.findTransactionByDateTime(testTransaction.getDateTime()).size() >= 1);
    }

    @Test
    public void findTransactionByDateTimeBeforeTest() {
        Date date = new Date(System.currentTimeMillis() + 10000);
        Assert.assertTrue(transactionService.findTransactionByDateTimeBefore(date).size() >= 1);
    }

    @Test
    public void findTransactionByDateTimeAfterTest() {
        Date date = new Date(System.currentTimeMillis() - 10000);
        Assert.assertTrue(transactionService.findTransactionByDateTimeAfter(date).size() >= 1);
    }

    @Test
    public void findTransactionByDateTimeBeetwenTest() {
        Date startTime = new Date(System.currentTimeMillis() - 10000);
        Date endTime = new Date(System.currentTimeMillis() + 10000);
        Assert.assertTrue(transactionService.findTransactionByDateTimeBetween(startTime, endTime).size() >= 1);
    }

    @Test
    public void findTransactionByAmountTest() {
        BigDecimal amount = new BigDecimal(150);
        Assert.assertTrue(transactionService.findTransactionByAmount(amount).size() >= 1);
    }

    @Test
    public void findTransactionByAmountGreaterThanTest() {
        BigDecimal amount = new BigDecimal(100);
        Assert.assertTrue(transactionService.findTransactionByAmountGreaterThan(amount).size() >= 1);
    }

    @Test
    public void findTransactionByAmountLessThanTest() {
        BigDecimal amount = new BigDecimal(200);
        Assert.assertTrue(transactionService.findTransactionByAmountLessThan(amount).size() >= 1);
    }

    @Test
    public void findTransactionByInfoContainingTest() {
        String info = "information";
        Assert.assertTrue(transactionService.findTransactionByInfoContaining(info).size() >= 1);
    }
}
