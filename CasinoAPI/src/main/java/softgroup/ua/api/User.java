package softgroup.ua.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

/**
 * @author Stanislav Rymar
 */
@XmlRootElement
public class User {

    private String loginId;
    private BigDecimal balance;
    private String email;
    private String password;

    public String getLoginId() {
        return loginId;
    }
    @XmlElement
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public BigDecimal getBalance() {
        return balance;
    }
    @XmlElement
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }
    @XmlElement
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    @XmlElement
    public void setPassword(String password) {
        this.password = password;
    }
}
