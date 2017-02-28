package softgroup.ua.jpa;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


@Entity
@Table(name = "user", schema = "casino")
public class UserEntity {
    private String loginId;
    private String password;
    private int rolesId;
    private BigDecimal balance;
    private String email;
    private Timestamp lastLoginDate;

    @Id
    @Column(name = "login_id")
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "roles_id")
    public int getRolesId() {
        return rolesId;
    }

    public void setRolesId(int rolesId) {
        this.rolesId = rolesId;
    }

    @Basic
    @Column(name = "balance")
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "last_login_date")
    public Timestamp getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Timestamp lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (rolesId != that.rolesId) return false;
        if (loginId != null ? !loginId.equals(that.loginId) : that.loginId != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (balance != null ? !balance.equals(that.balance) : that.balance != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (lastLoginDate != null ? !lastLoginDate.equals(that.lastLoginDate) : that.lastLoginDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = loginId != null ? loginId.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + rolesId;
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (lastLoginDate != null ? lastLoginDate.hashCode() : 0);
        return result;
    }
}
