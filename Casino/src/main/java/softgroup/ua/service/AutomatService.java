package softgroup.ua.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softgroup.ua.jpa.AutomatEntity;
import softgroup.ua.repository.AutomatRepository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Вова on 08.03.2017.
 */
@Service
public class AutomatService extends GeneralServiceImp<AutomatEntity, Integer>{
    private static final Logger logger =  LoggerFactory.getLogger(AutomatService.class);
    @Autowired
    AutomatRepository automatRepository;

    public List <AutomatEntity> getAllAutomats() {
        return automatRepository.findAll();
    }

    @Override
    public JpaRepository<AutomatEntity, Integer> getRepository() {
        return automatRepository;
    }

    public AutomatEntity getAutomatById(Integer automatId) {
        return automatRepository.findByAutomatId(automatId);
    }

    public AutomatEntity addAutomat(AutomatEntity automatEntity) {
        logger.debug("Adding automat \"%s\" with id \"%s\"", automatEntity.getAutomatName(), automatEntity.getAutomatId());
        automatEntity = automatRepository.save(automatEntity);
        return automatEntity;
    }

    public AutomatEntity updateAutomat(AutomatEntity automatEntity) {
        logger.debug("Updating automat \"%s\" with id \"%s\"", automatEntity.getAutomatName(), automatEntity.getAutomatId());
        automatEntity = automatRepository.save(automatEntity);
        return automatEntity;
    }

    public void deleteAutomat(Integer automatId) {
        AutomatEntity automatEntity = automatRepository.findOne(automatId);
        if (automatEntity != null) {
            logger.debug("Deleting automat \"%s\" with id \"%s\"", automatEntity.getAutomatName(), automatEntity.getAutomatId());
            automatRepository.delete(automatEntity);
        }
    }
}
