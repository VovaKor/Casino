package softgroup.ua.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import softgroup.ua.api.LoginReply;
import softgroup.ua.api.LoginRequest;
import softgroup.ua.authorization.AuthenticatedUser;
import softgroup.ua.authorization.TokenProvider;
import softgroup.ua.jpa.UserEntity;
import softgroup.ua.service.UserService;
import softgroup.ua.service.exception.AuthorizationException;
import softgroup.ua.service.exception.ParsingException;
import softgroup.ua.service.mappers.UserMapper;

/**
 * Created by alexander on 29.03.17.
 */
@RestController
public class AuthorizationController {
    private static final Logger logger =  LoggerFactory.getLogger(AuthorizationController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TokenProvider tokenProvider;

    @RequestMapping(path = "/auth", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<LoginReply> authenticateUser(@RequestBody LoginRequest request) {
        LoginReply reply = new LoginReply();
        UserEntity user = null;
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            user = userService.authenticateUser(request.loginId, request.password);
            String token = tokenProvider.newToken();
            tokenProvider.put(token, new AuthenticatedUser(user));
            reply.token = token;
            reply.user = userMapper.fromInternal(user);
        } catch (Exception e) {
            reply.retcode = -1;
            reply.error_message = e.getMessage();
            if (e instanceof AuthorizationException) {
                logger.error("Error login in user. User: " + request.loginId);
            }
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(reply, httpStatus);
    }
}
