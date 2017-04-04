package softgroup.ua.rest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import softgroup.ua.api.AddUserRequest;
import softgroup.ua.api.User;
import softgroup.ua.api.UserListReply;
import softgroup.ua.jpa.UserEntity;
import softgroup.ua.repository.RoleRepository;
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
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping(value = "/users/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserListReply findAllUsers() {
        UserListReply reply = new UserListReply();
        logger.debug("Searching all user!");
        userService.getAllUser().forEach((UserEntity u) -> {
            try {
                reply.getList().add(userMapper.fromInternal(u));
            } catch (ParsingException e) {
                reply.retcode = -2;
                reply.error_message = e.getMessage();
                logger.error(e.getMessage());
            }
        });
        return reply;
    }

    @GetMapping(value = "/users/{loginId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserListReply findUserById(@PathVariable String loginId) {
        UserListReply reply = new UserListReply();
        logger.debug("Searching UserEntity by login/id where login/id is %s ", loginId);
        User user = null;
        UserEntity userEntity = userService.findUserById(loginId);

        if (userEntity != null) {
            try {
                user = userMapper.fromInternal(userEntity);
                reply.getList().add(user);
                return reply;
            } catch (Exception e) {
                reply.retcode = -3;
                reply.error_message = e.getMessage();
                return reply;
            }
        }
        return reply;
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserListReply addUser(@RequestBody AddUserRequest addUserRequest) {
        UserListReply reply = new UserListReply();
        if (addUserRequest != null) {
            UserEntity userEntity;
            try {
                User user = addUserRequest.getUser();
                userEntity = userMapper.toInternal(user);
                userEntity.getRolesList().add(roleRepository.findByRoleId(3));
                userService.addUser(userEntity);
                logger.debug("UserEntity successful added");
                reply.getList().add(user);
            } catch (ParsingException e) {
                reply.retcode = -1;
                reply.error_message = e.getMessage();
                return reply;
            }
        } else {
            reply.retcode = -2;
            reply.error_message = "Empty addUserRequest";
        }
        return reply;
    }

}
