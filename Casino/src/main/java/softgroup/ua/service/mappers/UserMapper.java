package softgroup.ua.service.mappers;

import org.springframework.stereotype.Component;
import softgroup.ua.api.User;
import softgroup.ua.jpa.UserEntity;
import softgroup.ua.service.UserService;
import softgroup.ua.service.exception.ParsingException;

/**
 * @author Stanislav Rymar
 */
@Component
public class UserMapper implements GenericMapper<UserEntity, User> {


    @Override
    public User fromInternal(UserEntity userEntity) throws ParsingException {
        if (userEntity != null) {
            User user = new User();
            user.setLoginId(userEntity.getLoginId());
            user.setPassword(userEntity.getPassword());
            user.setEmail(userEntity.getEmail());
            user.setBalance(userEntity.getBalance());
            return user;
        } else
            throw new ParsingException("Parse exception: userEntity = null");
    }

    @Override
    public UserEntity toInternal(User user) throws ParsingException {
        if (user != null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setLoginId(user.getLoginId());
            userEntity.setPassword(UserService.digest(user.getPassword()));
            userEntity.setBalance(user.getBalance());
            userEntity.setEmail(user.getEmail());
            return userEntity;
        } else
            throw new ParsingException("Parse exception: User DTO = null");
    }
}
