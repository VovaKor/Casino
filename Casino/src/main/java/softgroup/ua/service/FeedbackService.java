package softgroup.ua.service;

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
    public void addFeedback(Feedback feedback) {
        feedbackRepository.saveAndFlush(feedback);
    }

    /**
     * Method for getting all feedbacks from DB
     *
     * @return list of feedback from DB
     */
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    /**
     * Method for finding feedback by feedbackId from DB
     *
     * @param id feedbackId
     * @return feedback with this id form DB
     */
    public Feedback findFeedbackById(Long id) {
        return feedbackRepository.findOne(id);
    }

    /**
     * Method for finding feedbacks which contain this email from DB
     *
     * @param email email
     * @return list of feedbacks which contain this email from DB
     */
    public List<Feedback> findFeedbackByEmail(String email) {
        return feedbackRepository.findByEmailContaining(email);
    }

    /**
     * Method for finding feedbacks which contain this message from DB
     *
     * @param message message
     * @return list of feedbacks which contain this message from DB
     */
    public List<Feedback> findFeedbackByMessageContaining(String message) {
        return feedbackRepository.findByMessageContaining(message);
    }

    /**
     * Method for finding feedbacks by mesageTime from DB
     *
     * @param messageTime messageTime
     * @return list of feedbacks with this messageTime from DB
     */
    public List<Feedback> findFeedbackByMessageTime(Date messageTime) {
        return feedbackRepository.findByMessageTime(messageTime);
    }

    /**
     * Method for finding feedbacks which had been written after time from DB
     *
     * @param time time
     * @return list of feedbacks which had been written after time from DB
     */
    public List<Feedback> findFeedbackByMessageTimeAfter(Date time) {
        return feedbackRepository.findByMessageTimeAfter(time);
    }

    /**
     * Method for finding feedbacks which had been written before time from DB
     *
     * @param time time
     * @return list of feedbacks which had been written before time from DB
     */
    public List<Feedback> findFeedbackByMessageTimeBefore(Date time) {
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
        return feedbackRepository.findByMessageTimeBetween(startTime, endTime);
    }

    /**
     * Method for removing feedback from DB by id
     *
     * @param id feedback id
     */
    public void deleteFeedback(Long id) {
        feedbackRepository.delete(id);
    }

    /**
     * Method for removing all feedbacks in DB
     */
    public void deleteAllFeedback() {
        feedbackRepository.deleteAll();
    }
}
