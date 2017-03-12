package softgroup.ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softgroup.ua.jpa.RoleEntity;

import java.util.List;

/**
 * Created by Вова on 04.03.2017.
 */
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    public List <RoleEntity> findByRoleName (String roleName);
    public RoleEntity findByRoleId(int roleId);
    //public List <RoleEntity> findAll();
}
