/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softgroup.ua.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import softgroup.ua.api.TransactionsListReply;
import softgroup.ua.jpa.TransactionEntity;
import softgroup.ua.service.TransactionMapper;
import softgroup.ua.service.TransactionService;
import softgroup.ua.service.UserService;

/**
 *
 * @author alexche
 */
@RestController
public class TransactionsController {
    @Autowired
    TransactionService transactionService;
    @Autowired
    TransactionMapper transactionMapper;
    @Autowired
    UserService userService;
    
    @RequestMapping(path = "/transactions/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public TransactionsListReply getAllTransactions() {
        TransactionsListReply transactionsListReply = new TransactionsListReply();
        transactionService.findAll().stream().forEach((TransactionEntity t) -> {
            transactionsListReply.transactions.add(transactionMapper.fromInternal(t));
        });
        return transactionsListReply;
    }
    
    @RequestMapping(path = "/transactions/byloginid/{loginId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public TransactionsListReply getTransactionsByLoginId(@PathVariable String loginId) {
        TransactionsListReply transactionsListReply = new TransactionsListReply();
        transactionService.findTransactionsByUser(userService.findUserById(loginId)).stream().forEach((TransactionEntity t) -> {
            transactionsListReply.transactions.add(transactionMapper.fromInternal(t));
        });
        return transactionsListReply;
    }
}
