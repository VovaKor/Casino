package softgroup.ua.jpa;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;


@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @NotNull
    @Column(name = "feedback_id")
    private Long feedBackId;

    @NotNull
    @Column(name = "message")
    private String message;

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "message_time")
    private Date messageTime;

    public Feedback(){

    }

    public Feedback(Long feedBackId) {
        this.feedBackId = feedBackId;
    }

    public Feedback(String message, String email, Date messageTime) {
        this.message = message;
        this.email = email;
        this.messageTime = messageTime;
    }

    public Feedback(Long feedBackId, String message, String email, Date messageTime) {
        this.feedBackId = feedBackId;
        this.message = message;
        this.email = email;
        this.messageTime = messageTime;
    }

    public Long getFeedBackId() {
        return feedBackId;
    }

    public void setFeedBackId(Long feedBackId) {
        this.feedBackId = feedBackId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Date messageTime) {
        this.messageTime = messageTime;
    }

}
