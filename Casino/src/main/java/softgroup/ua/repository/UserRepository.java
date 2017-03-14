package softgroup.ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import softgroup.ua.jpa.User;

import java.util.List;


/**
 * @author Stanislav Rymar
 */
public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT u FROM User u WHERE userData.name = :name")
    List<User> findUserByName(@Param("name") String name);

    @Query("SELECT u FROM User u WHERE userData.surname = :surname")
    List<User> findUserBySurname(@Param("surname") String surname);

    @Query("SELECT u FROM User u WHERE userData.name = :name AND userData.surname =:surname")
    List<User> findUserByNameAndSurname(@Param("name") String name, @Param("surname") String surname);

    @Query("SELECT u FROM User u WHERE userData.passport = :passport")
    User findUserByPassport(@Param("passport") String passport);

    @Query("SELECT u FROM User u WHERE userData.telephone = :telephone")
    User findUserByTelephone(@Param("telephone") String telephone);
}
