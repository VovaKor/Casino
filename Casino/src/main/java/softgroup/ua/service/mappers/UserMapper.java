package softgroup.ua.service.mappers;

import org.springframework.stereotype.Component;
import softgroup.ua.api.User;
import softgroup.ua.api.UserData;
import softgroup.ua.jpa.UserDataEntity;
import softgroup.ua.jpa.UserEntity;
import softgroup.ua.jpa.enums.Gender;
import softgroup.ua.service.UserService;
import softgroup.ua.service.exception.ParsingException;
import softgroup.ua.utils.DateParser;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.util.GregorianCalendar;

/**
 * @author Stanislav Rymar
 */
@Component
public class UserMapper implements GenericMapper<UserEntity, User> {


    @Override
    public User fromInternal(UserEntity userEntity) throws ParsingException {
        if (userEntity != null) {
            User user = new User();
            DateParser parser = new DateParser();

            user.setLoginId(userEntity.getLoginId());
            user.setPassword(userEntity.getPassword());
            user.setEmail(userEntity.getEmail());
            user.setBalance(userEntity.getBalance());
            if (userEntity.getLastLoginDate() != null)
                user.setLastLoginDate(userEntity.getLastLoginDate());

            if (userEntity.getUserData() != null) {
                UserData userData = new UserData();
                UserDataEntity userDataEntity = userEntity.getUserData();
                userData.setName(userDataEntity.getName());
                userData.setSurname(userDataEntity.getSurname());
                if (userDataEntity.getPatronymic() != null)
                    userData.setPatronymic(userDataEntity.getPatronymic());
                userData.setPassport(userDataEntity.getPassport());
                userData.setGender(userDataEntity.getGender().name());
                userData.setTelephone(userDataEntity.getTelephone());
                userData.setBirthDay(parser.calendarToString(userDataEntity.getBirthDay()));
                userData.setCity(userDataEntity.getCity());
                userData.setCountry(userDataEntity.getCountry());
                if (userDataEntity.getAddress() != null)
                    userData.setAddress(userDataEntity.getAddress());

                user.setUserData(userData);
            }

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

            if (user.getUserData() != null) {
                UserDataEntity userDataEntity = new UserDataEntity(user.getLoginId());
                UserData userData = user.getUserData();
                DateParser parser = new DateParser();

                userDataEntity.setName(userData.getName() == null ? null : userData.getName());
                userDataEntity.setSurname(userData.getSurname() == null ? null : userData.getName());
                userDataEntity.setPatronymic(userData.getPatronymic() == null ? null : userData.getPatronymic());
                userDataEntity.setPassport(userData.getPassport() == null ? null : userData.getPassport());
                userDataEntity.setGender(userData.getGender() == null ? null : Gender.valueOf(userData.getGender()));

                try {
                    userDataEntity.setBirthDay(userData.getBirthDay() == null ? null : parser.stringToCalendar(userData.getBirthDay()));
                } catch (ParseException e) {
                    throw new ParsingException("Parse exception: invalid string-date format");
                }

                userDataEntity.setTelephone(userData.getTelephone() == null ? null : userData.getTelephone());
                userDataEntity.setCity(userData.getCity() == null ? null : userData.getCity());
                userDataEntity.setCountry(userData.getCountry() == null ? null : userData.getCountry());
                userDataEntity.setAddress(userData.getAddress() == null ? null : userData.getAddress());

                userEntity.setUserData(userDataEntity);
            }

            return userEntity;
        } else
            throw new ParsingException("Parse exception: User DTO = null");
    }
}
