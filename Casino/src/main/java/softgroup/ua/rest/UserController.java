package softgroup.ua.rest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softgroup.ua.jpa.User;
import softgroup.ua.service.UserService;
import softgroup.ua.service.bootstrap.BootstrapUserService;

/**
 * @author Stanislav Rymar
 */
@RestController
public class UserController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BootstrapUserService.class);

    @Autowired
    private UserService userService;


    @GetMapping(value = "/user/{loginId}")
    public ResponseEntity<?> findUserById(@PathVariable String loginId) {
        logger.debug("Searching User by login/id where login/id is %s ", loginId);
        User user = userService.findUserById(loginId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/user")
    public ResponseEntity<Void> addUser(@RequestBody User user) {

        if (user != null) {
            userService.addUser(user);
            logger.debug("User successful added");
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            logger.debug("No content for adding");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }


}
