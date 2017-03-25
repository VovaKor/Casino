package softgroup.ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import softgroup.ua.jpa.UserEntity;

import java.util.List;


/**
 * @author Stanislav Rymar
 */
public interface UserRepository extends JpaRepository<UserEntity, String> {

    @Query("SELECT u FROM UserEntity u WHERE userData.name = :name")
    List<UserEntity> findUserByName(@Param("name") String name);

    @Query("SELECT u FROM UserEntity u WHERE userData.surname = :surname")
    List<UserEntity> findUserBySurname(@Param("surname") String surname);

    @Query("SELECT u FROM UserEntity u WHERE userData.name = :name AND userData.surname =:surname")
    List<UserEntity> findUserByNameAndSurname(@Param("name") String name, @Param("surname") String surname);

    @Query("SELECT u FROM UserEntity u WHERE userData.passport = :passport")
    UserEntity findUserByPassport(@Param("passport") String passport);

    @Query("SELECT u FROM UserEntity u WHERE userData.telephone = :telephone")
    UserEntity findUserByTelephone(@Param("telephone") String telephone);
}
