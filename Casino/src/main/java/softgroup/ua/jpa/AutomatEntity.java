package softgroup.ua.jpa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вова on 04.03.2017.
 */
@Entity
@Table(name = "automat", schema = "casino")
@NamedQueries({
        @NamedQuery(name = "AutomatEntity.findAll", query = "SELECT a FROM AutomatEntity a")})
public class AutomatEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "automat_id", nullable = false)
    private int automatId;
    @Basic
    @Column(name = "automat_name", nullable = false, length = 20)
    private String automatName;
    @Basic
    @Column(name = "description", nullable = false, length = 200)
    private String description;
    
    @OneToMany(cascade = CascadeType.DETACH, mappedBy = "automat")
    private List<GamesEntity> gamesList = new ArrayList<>();
    
    //private List<CommentsEntity> commentsEntityList;

    public int getAutomatId() {
        return automatId;
    }

    public void setAutomatId(int automatId) {
        this.automatId = automatId;
    }

    public String getAutomatName() {
        return automatName;
    }

    public void setAutomatName(String automatName) {
        this.automatName = automatName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public List<GamesEntity> getGamesList() {
        return gamesList;
    }

    public void setGamesList(List<GamesEntity> gamesList) {
        this.gamesList = gamesList;
    }

//    @OneToMany(cascade = CascadeType.DETACH, mappedBy = "automatEntity")
//    public List<CommentsEntity> getCommentsEntityList() {
//        return commentsEntityList;
//    }
//
//    public void setCommentsEntityList(List<CommentsEntity> commentsEntityList) {
//        this.commentsEntityList = commentsEntityList;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AutomatEntity that = (AutomatEntity) o;

        if (automatId != that.automatId) return false;
        if (automatName != null ? !automatName.equals(that.automatName) : that.automatName != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = automatId;
        result = 31 * result + (automatName != null ? automatName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
