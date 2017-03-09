/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softgroup.ua.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "games")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GamesEntity.findAll", query = "SELECT g FROM GamesEntity g"),
    @NamedQuery(name = "GamesEntity.findByGameId", query = "SELECT g FROM GamesEntity g WHERE g.gameId = :gameId"),
    @NamedQuery(name = "GamesEntity.findByAmount", query = "SELECT g FROM GamesEntity g WHERE g.amount = :amount"),
    @NamedQuery(name = "GamesEntity.findByDateTime", query = "SELECT g FROM GamesEntity g WHERE g.dateTime = :dateTime")})
public class GamesEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "game_id")
    private Long gameId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private BigDecimal amount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
    
    @JoinColumn(name = "login_id", referencedColumnName = "login_id")
    @ManyToOne(optional = false)
    private User user;
    
    @JoinColumn(name = "automat_id", referencedColumnName = "automat_id")
    @ManyToOne(optional = false)
    private AutomatEntity automatId;

    public GamesEntity() {
    }

    public GamesEntity(Long gameId) {
        this.gameId = gameId;
    }

    public GamesEntity(Long gameId, BigDecimal amount, Date dateTime) {
        this.gameId = gameId;
        this.amount = amount;
        this.dateTime = dateTime;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AutomatEntity getAutomatId() {
        return automatId;
    }

    public void setAutomatId(AutomatEntity automatId) {
        this.automatId = automatId;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gameId != null ? gameId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GamesEntity)) {
            return false;
        }
        GamesEntity other = (GamesEntity) object;
        if ((this.gameId == null && other.gameId != null) || (this.gameId != null && !this.gameId.equals(other.gameId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "softgroup.ua.jpa.GamesEntity[ gameId=" + gameId + " ]";
    }
    
}
