package softgroup.ua.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softgroup.ua.jpa.User;
import softgroup.ua.repository.UserRepository;

import java.util.List;

/**
 * @author Rymar Stanislav
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public void addUser(User user) {

        userRepository.saveAndFlush(user);
    }

    public void deleteUser(String loginId) {
        userRepository.delete(loginId);
    }

    public void updateUser(User user) {

        userRepository.saveAndFlush(user);
    }

    public User getUserById(String loginId) {
        return userRepository.getOne(loginId);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }


}
