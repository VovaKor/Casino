package softgroup.ua.repository;

import org.springframework.data.repository.CrudRepository;
import softgroup.ua.jpa.AutomatEntity;

import java.util.List;

/**
 * Created by Вова on 04.03.2017.
 */
public interface AutomatRepository extends CrudRepository <AutomatEntity, Long> {
    public List <AutomatEntity> findByAutomatId (int automatId);
    public List <AutomatEntity> findByAutomatName (String automatName);
    public List <AutomatEntity> findAll();
}
