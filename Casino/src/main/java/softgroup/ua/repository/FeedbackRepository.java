package softgroup.ua.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softgroup.ua.jpa.Feedback;

import java.util.Date;
import java.util.List;

/**
 * Repository for entity Feedback
 */
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    /**
     * Method for finding list of feedbacks by email
     *
     * @param email email of feedback
     * @return list of feedbacks which contain email
     */
    public List<Feedback> findByEmailContaining(String email);

    /**
     * Method for finding list of feedbacks by message
     *
     * @param message message of feedback
     * @return list of feedbacks which contain message
     */
    public List<Feedback> findByMessageContaining(String message);

    /**
     * Method for finding list of feedbacks by messageTime
     *
     * @param messageTime messageTime of feedback
     * @return list of feedbacks which have written by messageTime
     */
    public List<Feedback> findByMessageTime(Date messageTime);

    /**
     * Method for finding list of feedbacks by time
     *
     * @param time messageTime of feedback
     * @return list of feedbacks which have been written earlier than time
     */
    public List<Feedback> findByMessageTimeAfter(Date time);

    /**
     * Method for finding list of feedbacks by time
     *
     * @param time messageTime of feedback
     * @return list of feedbacks which have been written later than time
     */
    public List<Feedback> findByMessageTimeBefore(Date time);

    /**
     * Method for finding list of feedbacks between startTime and endTime
     *
     * @param startTime startTime
     * @param endTime   endTime
     * @return list of feedbacks, which have been written between startTime and endTime
     */
    public List<Feedback> findByMessageTimeBetween(Date startTime, Date endTime);

}
