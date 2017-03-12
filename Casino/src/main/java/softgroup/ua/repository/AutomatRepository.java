package softgroup.ua.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import softgroup.ua.jpa.AutomatEntity;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Вова on 04.03.2017.
 */
@Repository
public interface AutomatRepository extends JpaRepository <AutomatEntity, Long> {
    public List <AutomatEntity> findByAutomatId (int automatId);
    public List <AutomatEntity> findByAutomatName (String automatName);
   // public List <AutomatEntity> findAll();
}
