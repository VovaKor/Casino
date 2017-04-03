package softgroup.ua.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import softgroup.ua.api.Automat;
import softgroup.ua.api.AutomatsListReply;
import softgroup.ua.authorization.AuthenticatedUser;
import softgroup.ua.engines.games.GameEngine;
import softgroup.ua.jpa.AutomatEntity;
import softgroup.ua.jpa.GamesEntity;
import softgroup.ua.jpa.UserEntity;
import softgroup.ua.service.AutomatService;
import softgroup.ua.service.GamesService;
import softgroup.ua.service.UserService;
import softgroup.ua.service.mappers.AutomatMapper;
import softgroup.ua.utils.EntityIdGenerator;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Вова on 01.04.2017.
 */
@RestController
public class GameController {
    private static final Logger logger =  LoggerFactory.getLogger(GameController.class);
    @Autowired
    AutomatService automatService;
    @Autowired
    private UserService userService;
    @Autowired
    private GamesService gamesService;
    @Autowired
    AutomatMapper automatMapper;
    @Autowired
    GameEngine gameEngine;

    @Secured({"ROLE_USER"})
    @RequestMapping(path="/automats/byId/{automatId}/play",  method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AutomatsListReply getGameResult(@PathVariable Integer automatId){
        AutomatsListReply reply = new AutomatsListReply();
        AuthenticatedUser authenticatedUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity userEntity = authenticatedUser.getUser();
        try {
            AutomatEntity automatEntity = automatService.getAutomatById(automatId);
            Automat automat = automatMapper.fromInternal(automatEntity);
            gameEngine.play(automat, userEntity);
            reply.automats.add(automat);

            GamesEntity gamesEntity = new GamesEntity(EntityIdGenerator.random(), userEntity.getBalance(), new GregorianCalendar());
            gamesEntity.setUser(userEntity);
            gamesEntity.setAutomat(automatEntity);
            userEntity.getGamesList().add(gamesEntity);
            automatEntity.getGamesList().add(gamesEntity);
            gamesService.save(gamesEntity);
            automatService.updateAutomat(automatEntity);
            userService.updateUser(userEntity);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString(),e);
            reply.retcode = -1;
            reply.error_message = e.getMessage();
        }
        return reply;
    }
}
