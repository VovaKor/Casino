package softgroup.ua.jpa.content;

import softgroup.ua.jpa.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Vlad on 05.03.2017.
 */
@Entity
@Table(name = "content")
public class Content implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "content_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long contentId;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "login_id", nullable = false)
    private User author;

    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Size(max = 255)
    @Column(name = "text")
    @NotNull
    private String text;

    @Size(max = 255)
    @Column(name = "description")
    @NotNull
    private String description;

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Content content = (Content) o;

        if (!contentId.equals(content.contentId)) return false;
        if (!author.equals(content.author)) return false;
        if (!date.equals(content.date)) return false;
        if (!text.equals(content.text)) return false;
        return description.equals(content.description);

    }

    @Override
    public int hashCode() {
        int result = author.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + text.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}
