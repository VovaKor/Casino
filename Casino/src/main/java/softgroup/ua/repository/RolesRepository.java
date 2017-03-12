package softgroup.ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import softgroup.ua.jpa.RolesEntity;

import java.util.List;

/**
 * Created by Вова on 04.03.2017.
 */
public interface RolesRepository extends JpaRepository<RolesEntity, Long> {
    public List <RolesEntity> findByRoleName (String roleName);
    public List <RolesEntity> findByRolesId (int rolesId);
    //public List <RolesEntity> findAll();
}
