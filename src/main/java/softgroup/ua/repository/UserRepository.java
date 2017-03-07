package softgroup.ua.repository;

import org.springframework.data.repository.CrudRepository;
import softgroup.ua.jpa.UserEntity;

/**
 * Created by Vlad on 05.03.2017.
 */
public interface UserRepository extends CrudRepository<UserEntity, String> {
}
