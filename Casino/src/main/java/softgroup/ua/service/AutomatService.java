package softgroup.ua.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import softgroup.ua.jpa.AutomatEntity;
import softgroup.ua.jpa.GamesEntity;
import softgroup.ua.repository.AutomatRepository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Вова on 08.03.2017.
 */
@Service
@Transactional(timeout = 10)
public class AutomatService {
    private static final Logger logger =  LoggerFactory.getLogger(AutomatService.class);
    @Autowired
    AutomatRepository automatRepository;

    @Secured({"ROLE_ROOT","ROLE_USER"})
    public List <AutomatEntity> getAllAutomats() {
        return automatRepository.findAll();
    }

    @Secured({"ROLE_USER","ROLE_ROOT"})
    @Transactional(readOnly = true)
    public AutomatEntity getAutomatById(Integer automatId) {
        return automatRepository.findByAutomatId(automatId);
    }

    @Secured({"ROLE_ROOT"})
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AutomatEntity addAutomat(AutomatEntity automatEntity) {
        logger.debug("Adding automat \"%s\" with id \"%s\"", automatEntity.getAutomatName(), automatEntity.getAutomatId());
        automatEntity = automatRepository.save(automatEntity);
        return automatEntity;
    }

    @Secured({"ROLE_ROOT", "ROLE_USER"})
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public AutomatEntity updateAutomat(AutomatEntity automatEntity) {
        logger.debug("Updating automat \"%s\" with id \"%s\"", automatEntity.getAutomatName(), automatEntity.getAutomatId());
        automatEntity = automatRepository.save(automatEntity);
        return automatEntity;
    }

    @Secured({"ROLE_ROOT"})
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteAutomat(Integer automatId) {
        AutomatEntity automatEntity = automatRepository.findOne(automatId);
        if (automatEntity != null) {
            logger.debug("Deleting automat \"%s\" with id \"%s\"", automatEntity.getAutomatName(), automatEntity.getAutomatId());
            List<GamesEntity> gamesEntityList = automatEntity.getGamesList();
            gamesEntityList.forEach(gamesEntity -> gamesEntity.setAutomat(null));
            automatRepository.delete(automatEntity);
        }

    }
}
