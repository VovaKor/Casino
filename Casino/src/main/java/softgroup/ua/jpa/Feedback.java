package softgroup.ua.jpa;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Entity Feedback
 */
@Entity
@Table(name = "feedback")
public class Feedback implements Serializable {

    /**
     * Field feedbackId (not null, primary key)
     */
    @Id
    @NotNull
    @Column(name = "feedback_id")
    private Long feedbackId;

    /**
     * Field message (not null)
     */
    @NotNull
    @Column(name = "message")
    private String message;


    /**
     * Field email (not null)
     */
    @NotNull
    @Column(name = "email")
    private String email;

    /**
     * Field messageTime (not null)
     */
    @NotNull
    @Column(name = "message_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date messageTime;

    /**
     * Constructor without parameters
     */
    public Feedback() {

    }

    /**
     * Constructor with feedbackId parameter
     *
     * @param feedbackId field feedbackId
     */
    public Feedback(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    /**
     * Constrictor with all parameters
     *
     * @param feedbackId  field feedbackId
     * @param message     field message
     * @param email       field email
     * @param messageTime field messageTime
     */
    public Feedback(Long feedbackId, String message, String email, Date messageTime) {
        this.feedbackId = feedbackId;
        this.message = message;
        this.email = email;
        this.messageTime = messageTime;
    }

    /**
     * Getter for feedbackId
     *
     * @return field feedbackId
     */
    public Long getFeedbackId() {
        return feedbackId;
    }

    /**
     * Setter for feedbackId
     *
     * @param feedbackId value for field feedbackId
     */
    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    /**
     * Getter for message
     *
     * @return field message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter for message
     *
     * @param message value for field message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Getter for email
     *
     * @return field email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for email
     *
     * @param email value for field email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for messageTime
     *
     * @return field messageTime
     */
    public Date getMessageTime() {
        return messageTime;
    }

    /**
     * Setter for messageTime
     *
     * @param messageTime value for field messageTime
     */
    public void setMessageTime(Date messageTime) {
        this.messageTime = messageTime;
    }

    /**
     * Override method toString()
     *
     * @return String of Feedback
     */
    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackId=" + feedbackId +
                ", message='" + message + '\'' +
                ", email='" + email + '\'' +
                ", messageTime=" + messageTime +
                '}';
    }
}
