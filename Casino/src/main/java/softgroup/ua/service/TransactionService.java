package softgroup.ua.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import softgroup.ua.jpa.TransactionEntity;
import softgroup.ua.jpa.UserEntity;
import softgroup.ua.repository.TransactionRepository;

/**
 *
 * @author alexander
 */
@Service
public class TransactionService extends GeneralServiceImp<TransactionEntity, Long>{
    
    @Autowired
    private TransactionRepository transactionRepository;
    
    @Override
    public JpaRepository<TransactionEntity, Long> getRepository() {
        return transactionRepository;
    }
    
    public TransactionEntity addTransaction(TransactionEntity transaction) {
        transaction = transactionRepository.saveAndFlush(transaction);
        return transaction;
    }
    
    public List<TransactionEntity> getAllTransactions() {
        return transactionRepository.findAll();
    }
    
    public TransactionEntity findTransactionById(Long transactionId) {
        return transactionRepository.findOne(transactionId);
    }
    
    public void deleteTransaction(Long transactionId) {
        transactionRepository.delete(transactionId);
    }

    public List<TransactionEntity> findTransactionByDateTime(Date dateTime) {
        return transactionRepository.findByDateTime(dateTime);
    }

    public List<TransactionEntity> findTransactionByDateTimeBefore(Date dateTime) {
        return transactionRepository.findByDateTimeBefore(dateTime);
    }
    
    public List<TransactionEntity> findTransactionByDateTimeAfter(Date dateTime) {
        return transactionRepository.findByDateTimeAfter(dateTime);
    }
    
    public List<TransactionEntity> findTransactionByDateTimeBetween(Date startDateTime, Date endDateTime) {
        return transactionRepository.findByDateTimeBetween(startDateTime, endDateTime);
    }
    
    public List<TransactionEntity> findTransactionByAmount(BigDecimal amount) {
        return transactionRepository.findByAmount(amount);
    }
    
    public List<TransactionEntity> findTransactionByAmountGreaterThan(BigDecimal amount) {
        return transactionRepository.findByAmountGreaterThan(amount);
    }
    
    public List<TransactionEntity> findTransactionByAmountLessThan(BigDecimal amount) {
        return transactionRepository.findByAmountLessThan(amount);
    }
    
    public List<TransactionEntity> findTransactionByInfoContaining(String info) {
        return transactionRepository.findByInfoContaining(info);
    }
    
    public List<TransactionEntity> findTransactionsByUser(UserEntity user) {
        return transactionRepository.findByUser(user);
    }
    
}
