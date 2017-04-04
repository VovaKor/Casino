package softgroup.ua.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.annotation.Secured;
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

    @Secured({"ROLE_USER", "ROLE_ROOT"})
    public TransactionEntity addTransaction(TransactionEntity transaction) {
        transaction = transactionRepository.saveAndFlush(transaction);
        return transaction;
    }

    @Secured({"ROLE_ROOT"})
    public List<TransactionEntity> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Secured({"ROLE_USER", "ROLE_ROOT"})
    public TransactionEntity findTransactionById(Long transactionId) {
        return transactionRepository.findOne(transactionId);
    }
    
    public void deleteTransaction(Long transactionId) {
        transactionRepository.delete(transactionId);
    }

    @Secured({"ROLE_USER", "ROLE_ROOT"})
    public List<TransactionEntity> findTransactionByDateTime(Date dateTime) {
        return transactionRepository.findByDateTime(dateTime);
    }

    @Secured({"ROLE_USER", "ROLE_ROOT"})
    public List<TransactionEntity> findTransactionByDateTimeBefore(Date dateTime) {
        return transactionRepository.findByDateTimeBefore(dateTime);
    }

    @Secured({"ROLE_USER", "ROLE_ROOT"})
    public List<TransactionEntity> findTransactionByDateTimeAfter(Date dateTime) {
        return transactionRepository.findByDateTimeAfter(dateTime);
    }

    @Secured({"ROLE_USER", "ROLE_ROOT"})
    public List<TransactionEntity> findTransactionByDateTimeBetween(Date startDateTime, Date endDateTime) {
        return transactionRepository.findByDateTimeBetween(startDateTime, endDateTime);
    }

    @Secured({"ROLE_USER", "ROLE_ROOT"})
    public List<TransactionEntity> findTransactionByAmount(BigDecimal amount) {
        return transactionRepository.findByAmount(amount);
    }

    @Secured({"ROLE_USER", "ROLE_ROOT"})
    public List<TransactionEntity> findTransactionByAmountGreaterThan(BigDecimal amount) {
        return transactionRepository.findByAmountGreaterThan(amount);
    }

    @Secured({"ROLE_USER", "ROLE_ROOT"})
    public List<TransactionEntity> findTransactionByAmountLessThan(BigDecimal amount) {
        return transactionRepository.findByAmountLessThan(amount);
    }

    @Secured({"ROLE_USER", "ROLE_ROOT"})
    public List<TransactionEntity> findTransactionByInfoContaining(String info) {
        return transactionRepository.findByInfoContaining(info);
    }

    @Secured({"ROLE_USER", "ROLE_ROOT"})
    public List<TransactionEntity> findTransactionsByUser(UserEntity user) {
        return transactionRepository.findByUser(user);
    }
    
}
