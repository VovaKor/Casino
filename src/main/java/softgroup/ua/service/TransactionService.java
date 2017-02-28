package softgroup.ua.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softgroup.ua.jpa.Transaction;
import softgroup.ua.repository.TransactionRepository;

/**
 *
 * @author alexander
 */
@Service
public class TransactionService {
    
    @Autowired
    private TransactionRepository transactionRepository;
    
    public void addTransaction(Transaction transaction) {
        transactionRepository.saveAndFlush(transaction);
    }
    
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }
    
    public Transaction getTransactionById(Long transactionId) {
        return transactionRepository.findOne(transactionId);
    }
    
    public void deleteTransaction(Long transactionId) {
        transactionRepository.delete(transactionId);
    }
}
