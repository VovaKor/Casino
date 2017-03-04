package softgroup.ua.test.unit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import softgroup.ua.jpa.Feedback;
import softgroup.ua.service.FeedbackService;
import softgroup.ua.utils.EntityIdGenerator;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Feedback service test
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FeedbackServiceTest {

    /**
     * Feedback service
     */
    @Autowired
    private FeedbackService feedbackService;

    /**
     * Method which execute before all test methods. This method delete all feedback in DB and insert new.
     */
    @Before
    public void deleteAllAndAddFeedbacks() {
        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(stamp.getTime());
        feedbackService.deleteAllFeedback();
        feedbackService.addFeedback(new Feedback(50l, "Hello everyone!1", "vasya228@gmail.com", date));
        feedbackService.addFeedback(new Feedback(51l, "Hello everyone!2", "vasya322@gmail.com", date));
        feedbackService.addFeedback(new Feedback(52l, "Hello everyone!3", "vasya1488@gmail.com", date));
        feedbackService.addFeedback(new Feedback(53l, "Hello everyone!4", "vasya666@gmail.com", date));
        feedbackService.addFeedback(new Feedback(54l, "Hello everyone!11", "vasya777@gmail.com", date));
    }

    /**
     * Method for testing
     * {@link softgroup.ua.service.FeedbackService#addFeedback(softgroup.ua.jpa.Feedback)} and
     * {@link softgroup.ua.service.FeedbackService#deleteFeedback(Long)}
     */
    @Test
    public void addAndDeleteFeedback() {
        Long feedbackId = EntityIdGenerator.random();
        Feedback feedback = new Feedback(feedbackId);
        feedback.setMessage("Hello everyone!");
        feedback.setEmail("vasya228@gmail.com");
        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(stamp.getTime());
        feedback.setMessageTime(date);
        feedbackService.addFeedback(feedback);
        feedbackService.deleteFeedback(feedbackId);
    }

    /**
     * Method for testing
     * {@link softgroup.ua.service.FeedbackService#getAllFeedback()}
     */
    @Test
    public void getAllFeedback() {
        List<Feedback> feedbacks = feedbackService.getAllFeedback();
        assertEquals(feedbacks.size(), 5);
    }

    /**
     * Method for testing
     * {@link softgroup.ua.service.FeedbackService#findFeedbackById(Long)}
     */
    @Test
    public void findFeedbackById() {
        Feedback feedback = feedbackService.findFeedbackById(51l);
        assertNotNull(feedback);
    }

    /**
     * Method for testing
     * {@link softgroup.ua.service.FeedbackService#findFeedbackByEmail(String)}
     */
    @Test
    public void findFeedbackByEmail() {
        List<Feedback> feedbacks = feedbackService.findFeedbackByEmail("2");
        assertEquals(feedbacks.size(), 2);
    }

    /**
     * Method for testing
     * {@link softgroup.ua.service.FeedbackService#findFeedbackByMessageContaining(String)}
     */
    @Test
    public void findFeedbackByMessage() {
        List<Feedback> feedbacks = feedbackService.findFeedbackByMessageContaining("1");
        assertEquals(feedbacks.size(), 2);
    }

    /**
     * Method for testing
     * {@link softgroup.ua.service.FeedbackService#findFeedbackByMessageTimeAfter(java.util.Date)}
     */
    @Test
    public void findFeedbackByMessageTimeAfter() {
        Timestamp time = new Timestamp(System.currentTimeMillis() - 100000000);
        Date date = new Date(time.getTime());
        List<Feedback> feedbacks = feedbackService.findFeedbackByMessageTimeAfter(date);
        assertEquals(feedbacks.size(), 5);
    }

    /**
     * Method for testing
     * {@link softgroup.ua.service.FeedbackService#findFeedbackByMessageTimeBefore(java.util.Date)}
     */
    @Test
    public void findFeedbackByMessageTimeBefore() {
        Timestamp time = new Timestamp(System.currentTimeMillis() + 100000000);
        Date date = new Date(time.getTime());
        List<Feedback> feedbacks = feedbackService.findFeedbackByMessageTimeBefore(date);
        assertEquals(feedbacks.size(), 5);
    }

    /**
     * Method for testing
     * {@link softgroup.ua.service.FeedbackService#findFeedbackByMessageTimeBetween(java.util.Date, java.util.Date)}
     */
    @Test
    public void findFeedbackByMessageTimeBetween() {
        Timestamp startTime = new Timestamp(System.currentTimeMillis() - 1000000);
        Timestamp endTime = new Timestamp(System.currentTimeMillis() + 1000000);
        Date startDate = new Date(startTime.getTime());
        Date endDate = new Date(endTime.getTime());
        List<Feedback> feedbacks = feedbackService.findFeedbackByMessageTimeBetween(startDate, endDate);
        assertEquals(feedbacks.size(), 5);
    }

    /**
     * Method which execute after all test methods. This method delete all feedback in DB and insert new.
     */
    @After
    public void deleteAll() {
        feedbackService.deleteAllFeedback();
    }


}