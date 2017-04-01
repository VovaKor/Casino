/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softgroup.ua.service.mappers;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import softgroup.ua.api.Transaction;
import softgroup.ua.jpa.TransactionEntity;
import softgroup.ua.repository.UserRepository;
import softgroup.ua.service.exception.ParsingException;
import softgroup.ua.utils.EntityIdGenerator;

/**
 *
 * @author alexche
 */
@Component
public class TransactionMapper implements GenericMapper<TransactionEntity, Transaction> {
    
    @Autowired
    private UserRepository userRepository;
    
    public Transaction fromInternal(TransactionEntity transactionEntity) throws ParsingException {
        Transaction apiTransaction = null;
        if(null != transactionEntity) {
            apiTransaction = new Transaction();
            apiTransaction.amount = String.valueOf(transactionEntity.getAmount());
            apiTransaction.dateTime = String.valueOf(transactionEntity.getDateTime());
            apiTransaction.info = transactionEntity.getInfo();
            apiTransaction.loginId = transactionEntity.getUser().getLoginId();
            apiTransaction.transactionId = String.valueOf(transactionEntity.getTransactionId());
        } else {
            throw new ParsingException("Parse exception: TransactionEntity = null");
        }
        return apiTransaction;
    }
    
    public TransactionEntity toInternal(Transaction transaction) throws ParsingException {
        TransactionEntity internalTransaction = null;
        if (null != transaction) {
            internalTransaction = new TransactionEntity();
            if(null != transaction.transactionId) {
                internalTransaction.setTransactionId(Long.valueOf(transaction.transactionId));
            } else {
                internalTransaction.setTransactionId(Math.abs(EntityIdGenerator.random()));
            }
            internalTransaction.setAmount(new BigDecimal(transaction.amount));
            internalTransaction.setInfo(transaction.info);
            internalTransaction.setUser(userRepository.findOne(transaction.loginId));
        } else {
            throw new ParsingException("Parse exception: Transaction = null");
        }
        return internalTransaction;
    }
}
