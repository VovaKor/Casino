package softgroup.ua.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import softgroup.ua.api.AutomatsListReply;
import softgroup.ua.jpa.AutomatEntity;
import softgroup.ua.service.AutomatMapper;
import softgroup.ua.service.AutomatService;

/**
 * Created by Вова on 08.03.2017.
 */
@RestController
public class AutomatController {
    @Autowired
    AutomatService automatService;
    @Autowired
    AutomatMapper automatMapper;
    @RequestMapping(path="/automats/all",  method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AutomatsListReply getAllAutomats(){
        AutomatsListReply reply = new AutomatsListReply();
        for (AutomatEntity automat: automatService.getAllAutomats()) {
            reply.automats.add(automatMapper.fromInternal(automat));
        }
        return reply;
    }

}
