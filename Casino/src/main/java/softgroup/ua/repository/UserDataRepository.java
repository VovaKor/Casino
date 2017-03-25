package softgroup.ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softgroup.ua.jpa.UserDataEntity;

/**
 * @author Stanislav Rymar
 */
public interface UserDataRepository extends JpaRepository<UserDataEntity, String> {

}
