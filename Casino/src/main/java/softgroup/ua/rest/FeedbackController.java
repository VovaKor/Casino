package softgroup.ua.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import softgroup.ua.api.AddFeedbackRequest;
import softgroup.ua.api.FeedbackListReply;
import softgroup.ua.jpa.Feedback;
import softgroup.ua.service.FeedbackService;
import softgroup.ua.service.mappers.FeedbackMapper;


@RestController
public class FeedbackController {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackController.class);

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private FeedbackMapper feedbackMapper;

    @RequestMapping(path = "/feedback/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public FeedbackListReply getAllFeedbacks() {
        FeedbackListReply reply = new FeedbackListReply();
        for (Feedback f : feedbackService.getAllFeedback()) {
            reply.feedbacks.add(feedbackMapper.fromInternal(f));
        }
        return reply;
    }

    @RequestMapping(path = "/feedback/add",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public FeedbackListReply addFeedback(@RequestBody AddFeedbackRequest addFeedbackRequest) {
        FeedbackListReply reply = new FeedbackListReply();
        try {
            Feedback feedback = feedbackService.addFeedback(feedbackMapper.toInternal(addFeedbackRequest.feedback));
            reply.feedbacks.add(feedbackMapper.fromInternal(feedback));
        } catch (Exception e) {
            reply.retcode = -1;
            reply.error_message = e.getMessage();
            logger.error("Error adding feedback. Exception: " + e.getMessage(), e);
        }
        return reply;
    }

    @RequestMapping(path = "/feedback/byId/{feedbackId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public FeedbackListReply getFeedbackById(@PathVariable Long feedbackId) {
        FeedbackListReply reply = new FeedbackListReply();
        reply.feedbacks.add(feedbackMapper.fromInternal(feedbackService.findFeedbackById(feedbackId)));
        return reply;
    }

    @RequestMapping(path = "/feedback/delete/{feedbackId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public FeedbackListReply deleteFeedbackById(@PathVariable Long feedbackId) {
        FeedbackListReply reply = new FeedbackListReply();
        try {
            feedbackService.deleteFeedback(feedbackId);
        } catch (Exception e) {
            reply.retcode = -1;
            reply.error_message = e.getMessage();
            logger.error("Error delete feedback. Exception: " + e.getMessage(), e);
        }
        return reply;


    }

}
