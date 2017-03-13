package softgroup.ua.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import softgroup.ua.api.AddAutomatRequest;
import softgroup.ua.api.AutomatsListReply;
import softgroup.ua.api.GenericReply;
import softgroup.ua.jpa.AutomatEntity;
import softgroup.ua.service.AutomatMapper;
import softgroup.ua.service.AutomatService;

/**
 * Created by Вова on 08.03.2017.
 */
@RestController
public class AutomatController {
    private static final Logger logger =  LoggerFactory.getLogger(AutomatController.class);
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
    @RequestMapping(path="/automats/byId/{automatId}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AutomatsListReply getAutomatById(@PathVariable Integer automatId){
        AutomatsListReply reply = new AutomatsListReply();
        reply.automats.add(automatMapper.fromInternal(automatService.getAutomatById(automatId)));
        return reply;
    }

    @RequestMapping(path="/automats/add",  method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AutomatsListReply addAutomat (@RequestBody AddAutomatRequest addAutomatRequest){
        AutomatsListReply reply = new AutomatsListReply();
        try {
            AutomatEntity automatEntity = automatService.addAutomat(automatMapper.toInternal(addAutomatRequest.automat));
            reply.automats.add(automatMapper.fromInternal(automatEntity));
        }catch (Exception e){
            reply.retcode = -1;
            reply.error_message = e.getMessage();
            logger.error("Error adding automat. Exception: "+e.getMessage(),e);
        }
        return reply;
    }
    @RequestMapping(path="/automats/update",  method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AutomatsListReply updateAutomat(@RequestBody AddAutomatRequest addAutomatRequest){
        AutomatsListReply reply = new AutomatsListReply();
        try {
            AutomatEntity automatEntity = automatService.updateAutomat(automatMapper.toInternal(addAutomatRequest.automat));
            reply.automats.add(automatMapper.fromInternal(automatEntity));
        }catch (Exception e){
            reply.retcode = -1;
            reply.error_message = e.getMessage();
            logger.error("Error updating automat. Exception: "+e.getMessage(),e);
        }
        return reply;
    }
    @RequestMapping(path="/automats/delete/{automatId}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericReply deleteAutomat(@PathVariable Integer automatId){
        GenericReply reply = new GenericReply();
        try {
            automatService.deleteAutomat(automatId);
        }catch (Exception e){
            reply.retcode = -1;
            reply.error_message = e.getMessage();
            logger.error("Error deleting automat. Exception: "+e.getMessage(),e);
        }
        return reply;
    }
}
