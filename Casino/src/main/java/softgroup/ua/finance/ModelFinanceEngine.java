/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softgroup.ua.finance;

import java.math.BigDecimal;
import java.util.Date;
import softgroup.ua.jpa.User;

/**
 *
 * @author alexander
 */
public class ModelFinanceEngine extends FinanceEngine {

    public ModelFinanceEngine(User user) {
        super(user);
    }

    @Override
    public boolean connect() {
        connected = true;
        return connected;
    }
    
    @Override
    protected Date takeOf(BigDecimal amount) throws Exception {
        user.setBalance(user.getBalance().subtract(amount.abs()));
        return new Date(System.currentTimeMillis());
    }

    @Override
    protected Date accure(BigDecimal amount) throws Exception {
        user.setBalance(user.getBalance().add(amount));
        return new Date(System.currentTimeMillis());
    }

    @Override
    public boolean checkBalance(BigDecimal amount) throws Exception {
        if (!connected)
            throw new Exception("Not connected");
        return user.getBalance().compareTo(amount.abs()) == 1;
    }
    
}
