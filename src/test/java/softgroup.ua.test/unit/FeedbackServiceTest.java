package softgroup.ua.test.unit;

import java.sql.Timestamp;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import softgroup.ua.jpa.Feedback;
import softgroup.ua.service.FeedbackService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FeedbackServiceTest{

    @Autowired
    private FeedbackService feedbackService;

    @Test
    public void addFeedback(){
        Long feedbackId = 55l;
        Feedback feedback = new Feedback(feedbackId);
        feedback.setMessage("Hello everyone!");
        feedback.setEmail("vasya228@gmail.com");
        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(stamp.getTime());
        feedback.setMessageTime(date);
        feedbackService.addFeedback(feedback);
    }




}