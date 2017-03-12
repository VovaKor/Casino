package softgroup.ua.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softgroup.ua.jpa.RoleEntity;
import softgroup.ua.jpa.User;
import softgroup.ua.repository.UserDataRepository;
import softgroup.ua.repository.UserRepository;

import java.util.List;

/**
 * @author Rymar Stanislav
 */
@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDataRepository userDataRepository;

    public void addUser(User user) {
        logger.debug("Adding user with login_id = %s", user.getLoginId());
        userRepository.saveAndFlush(user);
    }

    public void deleteUser(String loginId) {
        User user = userRepository.getOne(loginId);
        if (user != null) {
            logger.debug("Deleting users %s with id %s", user.getLoginId());
            List<RoleEntity> rolesList = user.getRolesList();
            rolesList.clear();

            userDataRepository.delete(user.getUserData().getPassport());
            userRepository.delete(loginId);
        }
    }


    public void updateUser(User user) {
        logger.debug("User with login_id = %s was updated", user.getLoginId());
        userRepository.saveAndFlush(user);
    }


    public User getUserById(String loginId) {
        return userRepository.getOne(loginId);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }


}
