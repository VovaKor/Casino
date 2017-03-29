package softgroup.ua.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softgroup.ua.jpa.Feedback;
import softgroup.ua.repository.FeedbackRepository;

import java.util.Date;
import java.util.List;

/**
 * Service for entity Feedback
 */
@Service
public class FeedbackService {

    private static final Logger logger = LoggerFactory.getLogger(Feedback.class);

    /**
     * Feedback repository
     */
    @Autowired
    private FeedbackRepository feedbackRepository;

    /**
     * Method for adding Feedback in DB
     *
     * @param feedback feedback
     */
    public Feedback addFeedback(Feedback feedback) {
        logger.debug("Adding feedback {} at {}", feedback.getEmail(), feedback.getMessageTime());
        feedbackRepository.saveAndFlush(feedback);
        logger.debug("Added successfully");
        return feedback;
    }

    /**
     * Method for getting all feedbacks from DB
     *
     * @return list of feedback from DB
     */
    public List<Feedback> getAllFeedback() {
        logger.debug("Finding all feedbacks");
        return feedbackRepository.findAll();
    }

    /**
     * Method for finding feedback by feedbackId from DB
     *
     * @param id feedbackId
     * @return feedback with this id form DB
     */
    public Feedback findFeedbackById(Long id) {
        logger.debug("Finding feedback by ID - {}", id);
        return feedbackRepository.findOne(id);
    }

    /**
     * Method for finding feedbacks which contain this email from DB
     *
     * @param email email
     * @return list of feedbacks which contain this email from DB
     */
    public List<Feedback> findFeedbackByEmail(String email) {
        logger.debug("Finding feedback by EMAIL - {}", email);
        return feedbackRepository.findByEmailContaining(email);
    }

    /**
     * Method for finding feedbacks which contain this message from DB
     *
     * @param message message
     * @return list of feedbacks which contain this message from DB
     */
    public List<Feedback> findFeedbackByMessageContaining(String message) {
        logger.debug("Finding feedbacks by MESSAGE - {}", message);
        return feedbackRepository.findByMessageContaining(message);
    }

    /**
     * Method for finding feedbacks which had been written after time from DB
     *
     * @param time time
     * @return list of feedbacks which had been written after time from DB
     */
    public List<Feedback> findFeedbackByMessageTimeAfter(Date time) {
        logger.info("Finding feedbacks by TIME AFTER - {}", time);
        return feedbackRepository.findByMessageTimeAfter(time);
    }

    /**
     * Method for finding feedbacks which had been written before time from DB
     *
     * @param time time
     * @return list of feedbacks which had been written before time from DB
     */
    public List<Feedback> findFeedbackByMessageTimeBefore(Date time) {
        logger.debug("Finding feedbacks by TIME BEFORE - {}", time);
        return feedbackRepository.findByMessageTimeBefore(time);
    }

    /**
     * Method for finding feedbacks which had been written between startTime and endTime from DB
     *
     * @param startTime startTime
     * @param endTime   endTime
     * @return list of feedbacks which had been written between startTime and endTime from DB
     */
    public List<Feedback> findFeedbackByMessageTimeBetween(Date startTime, Date endTime) {
        logger.debug("Finding feedbacks by TIME BETWEEN {} and {}", startTime, endTime);
        return feedbackRepository.findByMessageTimeBetween(startTime, endTime);
    }

    /**
     * Method for removing feedback from DB by id
     *
     * @param id feedback id
     */
    public void deleteFeedback(Long id) {
        logger.debug("Deleting feedback by ID - {}", id);
        feedbackRepository.delete(id);
        logger.debug("Deleted successfully");
    }

    /**
     * Method for removing all feedbacks in DB
     */
    public void deleteAllFeedback() {
        logger.debug("Deleting all feedbacks");
        feedbackRepository.deleteAll();
        logger.debug("Deleted successfully");
    }
}
