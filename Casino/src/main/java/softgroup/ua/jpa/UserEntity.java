package softgroup.ua.jpa;

import org.hibernate.validator.constraints.Email;
import softgroup.ua.jpa.content.Content;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


@Entity
@Table(name = "user", schema = "casino")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String loginId;
    private String password;
    private int rolesId;
    private BigDecimal balance;
    private String email;
    private Calendar lastLoginDate;
    private UserDataEntity userData;
    private List<TransactionEntity> transactionList;
    private List<Content> contentList;
    private List<GamesEntity> gamesList;

    public UserEntity(){

    }

    public UserEntity(String loginId, String password, int rolesId, BigDecimal balance, String email) {
        this.loginId = loginId;
        this.password = password;
        this.rolesId = rolesId;
        this.balance = balance;
        this.email = email;
    }

    @Id
    @NotNull
    @Column(name = "login_id")
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    @Basic
    @NotNull
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @NotNull
    @Column(name = "roles_id")
    public int getRolesId() {
        return rolesId;
    }

    public void setRolesId(int rolesId) {
        this.rolesId = rolesId;
    }

    @Basic
    @NotNull
    @Column(name = "balance")
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Basic
    @NotNull
    @Email
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_login_date")
    public Calendar getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Calendar lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public UserDataEntity getUserData() {
        return userData;
    }

    public void setUserData(UserDataEntity userData) {
        this.userData = userData;
    }
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "loginId")
    public List<GamesEntity> getGamesList() {
        return gamesList;
    }
 
    public void setGamesList(List<GamesEntity> gamesList) {
        this.gamesList = gamesList;
    }
     
    @OneToMany(mappedBy = "user", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    public List<TransactionEntity> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<TransactionEntity> transactionList) {
        this.transactionList = transactionList;
    }

    @OneToMany(mappedBy = "author", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    public List<Content> getContentList() {
        return contentList;
    }

    public void setContentList(List<Content> contentList) {
        this.contentList = contentList;
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

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy HH:mm:ss");

        return "UserEntity{" +
                "loginId='" + loginId + '\'' +
                ", password='" + password + '\'' +
                ", rolesId=" + rolesId +
                ", balance=" + balance +
                ", email='" + email + '\'' +
                ", lastLoginDate=" + sdf.format(lastLoginDate.getTime()) +
                ", transactionList=" + transactionList +
                ", userData=" + userData +
                '}';
    }
}
