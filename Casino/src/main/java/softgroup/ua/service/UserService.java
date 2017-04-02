package softgroup.ua.service;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import softgroup.ua.jpa.UserDataEntity;
import softgroup.ua.jpa.UserEntity;
import softgroup.ua.repository.UserDataRepository;
import softgroup.ua.repository.UserRepository;
import softgroup.ua.service.exception.AuthorizationException;

import java.security.NoSuchAlgorithmException;
import java.util.GregorianCalendar;
import java.util.List;

import java.security.MessageDigest;


/**
 * @author Rymar Stanislav
 */
@Service
@Transactional(timeout = 10)
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDataRepository userDataRepository;

    @Secured({})
    public void addUser(UserEntity user) {
        logger.debug("Adding user with login/id = %s", user.getLoginId());
        userRepository.save(user);
    }

    public void deleteUser(String loginId) {
        logger.debug("Deleting use with login/id = %s", loginId);
        userRepository.delete(loginId);
    }

    public void updateUser(UserEntity user) {
        logger.debug("Updating user with login/id = %s", user.getLoginId());
        userRepository.save(user);
    }
    @Transactional(readOnly = true)
    public UserEntity findUserById(String loginId) {
        logger.debug("Searching user with login/id = %s", loginId);
        UserEntity userEntity = userRepository.findOne(loginId);
        //This line is mandatory to solve “failed to lazily initialize a collection of role” exception
        Hibernate.initialize(userEntity.getRolesList().size());
        return userEntity;
    }

    public List<UserEntity> findUserByName(String name) {
        logger.debug("Searching users with name = %s", name);
        return userRepository.findUserByName(name);
    }

    public List<UserEntity> findUserBySurname(String surname) {
        logger.debug("Searching users with surname = %s", surname);
        return userRepository.findUserBySurname(surname);
    }

    public List<UserEntity> findUserByNameAndSurname(String name, String surname) {
        logger.debug("Searching users with name = %s and surname = %s", name, surname);
        return userRepository.findUserByNameAndSurname(name, surname);
    }

    public UserEntity findUserByPassport(String passport) {
        logger.debug("Searching user with passport = %s", passport);
        return userRepository.findUserByPassport(passport);
    }

    public UserEntity findUserByTelephone(String telephone) {
        logger.debug("Searching user with telephone = %s", telephone);
        return userRepository.findUserByTelephone(telephone);
    }

    // TODO: 16.03.17 add to this method unit test
    public UserDataEntity findUserDataById(String loginId) {
        logger.debug("Searching user data with login/id = %s ", loginId);
        return userDataRepository.findOne(loginId);
    }

    public List<UserEntity> getAllUser() {
        logger.debug("Searching all users");
        return userRepository.findAll();
    }

    public void deleteAllUser() {
        logger.debug("Deleting all users");
        userRepository.deleteAll();
    }

    @Transactional
    public UserEntity authenticateUser(String login, String password) throws AuthorizationException {
        UserEntity user = userRepository.findOne(login);
        if (user != null) {
            if( ! user.getPassword().equalsIgnoreCase(digest(password))){
                logger.debug("Invalid password");
                throw new AuthorizationException("Invalid password");
            }
            else {
                user.setLastLoginDate(new GregorianCalendar());
                userRepository.save(user);
                logger.debug("Login ok");
            }
        }else{
            logger.debug("User not found");
            throw new AuthorizationException("User not found");
        }
        return user;
    }

    public static String digest(String original) {
        String res = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(original.getBytes());
            byte[] digest = md.digest();
            res = new String(Hex.encode(digest));
        } catch (NoSuchAlgorithmException ex) {
            logger.error("Can not create SHA-256 digester");
        }
        return res;
    }

}
