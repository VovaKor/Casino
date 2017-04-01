package softgroup.ua.rest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softgroup.ua.api.AddUserRequest;
import softgroup.ua.api.User;
import softgroup.ua.api.UserListReply;
import softgroup.ua.jpa.UserEntity;
import softgroup.ua.service.UserService;
import softgroup.ua.service.bootstrap.BootstrapUserService;
import softgroup.ua.service.exception.ParsingException;
import softgroup.ua.service.mappers.UserMapper;

/**
 * @author Stanislav Rymar
 */
@RestController
public class UserController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BootstrapUserService.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping(value = "/user/{loginId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> findUserById(@PathVariable String loginId) {
        logger.debug("Searching UserEntity by login/id where login/id is %s ", loginId);
        User user = null;
        UserListReply reply = new UserListReply();
        UserEntity userEntity = userService.findUserById(loginId);


        if (userEntity != null) {
            try {
                user = userMapper.fromInternal(userEntity);
                reply.getList().add(user);
                return new ResponseEntity<>(reply, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/user")
    public ResponseEntity<Void> addUser(@RequestBody AddUserRequest addUserRequest) {

        if (addUserRequest != null) {
            UserEntity userEntity;
            try {
                User user = addUserRequest.getUser();
                userEntity = userMapper.toInternal(user);
                userService.addUser(userEntity);

                logger.debug("UserEntity successful added");
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (ParsingException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            logger.debug("No content for adding");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }


}
