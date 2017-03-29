package softgroup.ua.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import softgroup.ua.api.FeedbackAPI;
import softgroup.ua.jpa.Feedback;
import softgroup.ua.repository.FeedbackRepository;

//import softgroup.ua.api.FeedbackAPI;

@Component
public class FeedbackMapper {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public FeedbackAPI fromInternal(Feedback feedback) {
        FeedbackAPI feedbackAPI = null;
        if (feedback != null) {
            feedbackAPI = new FeedbackAPI();
            feedbackAPI.feedbackId = feedback.getFeedbackId().toString();
            feedbackAPI.message = feedback.getMessage();
            feedbackAPI.email = feedback.getEmail();
            feedbackAPI.messageTime = feedback.getMessageTime();
        }
        return feedbackAPI;
    }

    public Feedback toInternal(FeedbackAPI feedbackAPI) {
        Feedback feedback = null;
        if (feedbackAPI.feedbackId != null) {
            feedback = feedbackRepository.findOne(Long.parseLong(feedbackAPI.feedbackId));
        }
        if (feedback == null) {
            feedback = new softgroup.ua.jpa.Feedback();
        }
        feedback.setFeedbackId(Long.parseLong(feedbackAPI.feedbackId));
        feedback.setMessage(feedbackAPI.message);
        feedback.setEmail(feedbackAPI.email);
        feedback.setMessageTime(feedbackAPI.messageTime);
        return feedback;
    }
}
