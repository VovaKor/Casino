package softgroup.ua.jpa;

import org.hibernate.validator.constraints.Email;
import softgroup.ua.jpa.content.Content;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Stanislav Rymar
 */

@Entity
@Table(name = "user", schema = "casino")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String loginId;
    private String password;
    private BigDecimal balance;
    private String email;
    private Calendar lastLoginDate;
    private UserData userData;
    private List<GamesEntity> gamesList = new ArrayList<>();
    private List<RoleEntity> rolesList = new ArrayList<>();
    private List<TransactionEntity> transactionList = new ArrayList<>();
    private List<Content> contentList = new ArrayList<>();

    public User() {

    }

    public User(String loginId) {
        this.loginId = loginId;
    }


    public User(String loginId, String password, BigDecimal balance, String email) {
        this.loginId = loginId;
        this.password = password;
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

    @OneToOne(mappedBy = "user", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    public List<GamesEntity> getGamesList() {
        return gamesList;
    }

    public void setGamesList(List<GamesEntity> gamesList) {
        this.gamesList = gamesList;
    }

    @JoinTable(name = "user_roles", joinColumns = {
            @JoinColumn(name = "login_id", referencedColumnName = "login_id")}, inverseJoinColumns = {
            @JoinColumn(name = "roles_id", referencedColumnName = "roles_id")})
    @ManyToMany(cascade = CascadeType.DETACH)
    public List<RoleEntity> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<RoleEntity> rolesList) {
        this.rolesList = rolesList;
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

        User that = (User) o;

        if (rolesList != that.rolesList) return false;
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
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (lastLoginDate != null ? lastLoginDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy HH:mm:ss");

        return "User{" +
                "loginId='" + loginId + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", email='" + email + '\'' +
                ", lastLoginDate=" + sdf.format(lastLoginDate.getTime()) +
                ", transactionList=" + transactionList +
                ", userData=" + userData +
                '}';
    }
}
