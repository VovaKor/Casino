package softgroup.ua.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 * @author Stanislav Rymar
 */
@XmlRootElement
public class User {

    private String loginId;
    private BigDecimal balance;
    private String email;
    private String password;
    private Calendar lastLoginDate;
    private UserData userData;


    public String getLoginId() {
        return loginId;
    }

    @XmlElement(required = true)
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

    @XmlElement(required = true)
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    @XmlElement(required = true)
    public void setPassword(String password) {
        this.password = password;
    }

    public Calendar getLastLoginDate() {
        return lastLoginDate;
    }

    @XmlElement
    public void setLastLoginDate(Calendar lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public UserData getUserData() {
        return userData;
    }

    @XmlElement
    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
