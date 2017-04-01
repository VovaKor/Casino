package softgroup.ua.engines.finance;

import java.math.BigDecimal;
import java.util.Date;
import softgroup.ua.jpa.UserEntity;

/**
 *
 * @author alexander
 */
public abstract class FinanceEngine {
    
    protected UserEntity user;
    protected boolean connected;
    
    public FinanceEngine(UserEntity user) {
        this.user = user;
    }
    
    public Date operate(BigDecimal amount) throws Exception {
        if(!connected)
            throw new Exception("Not connected");
        Date dateTime = null;
        if (amount.signum() == -1) {
            if (checkBalance(amount)) {
                dateTime =  takeOf(amount);
            } else {
                 throw new Exception("Not enough money");
            }
        } else {
            dateTime = accure(amount);
        }
        return dateTime;
    }
    public abstract boolean checkBalance(BigDecimal amount) throws Exception;
    public abstract boolean connect();
    protected abstract Date takeOf(BigDecimal amount) throws Exception;
    protected abstract Date accure(BigDecimal amount) throws Exception;
}
