package softgroup.ua.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softgroup.ua.jpa.Feedback;
import softgroup.ua.repository.FeedbackRepository;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public void addFeedback(Feedback feedback){
        feedbackRepository.saveAndFlush(feedback);
    }

    public List<Feedback> getAll(){
        return feedbackRepository.findAll();
    }
}
