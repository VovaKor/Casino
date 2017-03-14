package softgroup.ua.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        logger.debug("Adding user with login/id = %s", user.getLoginId());
        userRepository.save(user);
    }

    public void deleteUser(String loginId) {
        logger.debug("Deleting use with login/id = %s", loginId);
        userRepository.delete(loginId);
    }

    public void updateUser(User user) {
        logger.debug("Updating user with login/id = %s", user.getLoginId());
        userRepository.save(user);
    }

    public User findUserById(String loginId) {
        logger.debug("Searching user with login/id = %s", loginId);
        return userRepository.findOne(loginId);
    }

    public List<User> findUserByName(String name) {
        logger.debug("Searching users with name = %s", name);
        return userRepository.findUserByName(name);
    }

    public List<User> findUserBySurname(String surname) {
        logger.debug("Searching users with surname = %s", surname);
        return userRepository.findUserBySurname(surname);
    }

    public List<User> findUserByNameAndSurname(String name, String surname) {
        logger.debug("Searching users with name = %s and surname = %s", name, surname);
        return userRepository.findUserByNameAndSurname(name, surname);
    }

    public User findUserByPassport(String passport) {
        logger.debug("Searching user with passport = %s", passport);
        return userRepository.findUserByPassport(passport);
    }

    public User findUserByTelephone(String telephone) {
        logger.debug("Searching user with telephone = %s", telephone);
        return userRepository.findUserByTelephone(telephone);
    }

    public List<User> getAllUser() {
        logger.debug("Searching all users");
        return userRepository.findAll();
    }

    public void deleteAllUser() {
        logger.debug("Deleting all users");
        userRepository.deleteAll();
    }

}
