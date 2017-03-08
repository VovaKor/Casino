package softgroup.ua.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import softgroup.ua.api.AutomatsListReply;
import softgroup.ua.jpa.AutomatEntity;
import softgroup.ua.service.AutomatsMapper;
import softgroup.ua.service.AutomatsService;

/**
 * Created by Вова on 08.03.2017.
 */
@RestController
public class AutomatsController {
    @Autowired
    AutomatsService automatsService;
    @Autowired
    AutomatsMapper automatsMapper;
    @RequestMapping(path="/automats/all",  method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AutomatsListReply getAllAutomats(){
        AutomatsListReply reply = new AutomatsListReply();
        for (AutomatEntity automat:automatsService.getAllAutomats()) {
            reply.automats.add(automatsMapper.fromInternal(automat));
        }
        return reply;
    }

}
