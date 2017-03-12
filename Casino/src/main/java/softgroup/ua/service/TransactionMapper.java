/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softgroup.ua.service;

import org.springframework.stereotype.Component;
import softgroup.ua.api.Transaction;
import softgroup.ua.jpa.TransactionEntity;

/**
 *
 * @author alexche
 */
@Component
public class TransactionMapper {
    
    public Transaction fromInternal(TransactionEntity transactionEntity) {
        Transaction apiTransaction = null;
        if(null != transactionEntity) {
            apiTransaction = new Transaction();
            apiTransaction.amount = String.valueOf(transactionEntity.getAmount());
            apiTransaction.dateTime = String.valueOf(transactionEntity.getDateTime());
            //Check null
            apiTransaction.info = transactionEntity.getInfo();
            apiTransaction.loginId = transactionEntity.getUser().getLoginId();
            apiTransaction.transactionId = String.valueOf(transactionEntity.getTransactionId());
        }
        return apiTransaction;
    }
}
