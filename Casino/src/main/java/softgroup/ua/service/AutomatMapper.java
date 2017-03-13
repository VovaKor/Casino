package softgroup.ua.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import softgroup.ua.api.Automat;
import softgroup.ua.jpa.AutomatEntity;
import softgroup.ua.repository.AutomatRepository;

/**
 * Created by Вова on 08.03.2017.
 */
@Component
public class AutomatMapper {
    private static final Logger logger =  LoggerFactory.getLogger(AutomatMapper.class);
    @Autowired
    AutomatRepository automatRepository;
    /**
     * Maps internal JPA model to external REST model
     * @param automatEntity internal automat model
     * @return external REST automat model
     */
    public Automat fromInternal(AutomatEntity automatEntity) {
        Automat automat = null;
        if (automatEntity != null) {
            automat = new Automat();
            automat.automatId = automatEntity.getAutomatId();
            automat.automatName = automatEntity.getAutomatName();
            automat.description = automatEntity.getDescription();
        }
        return automat;
    }
    /**
     * Maps external REST model to internal automat entity;
     * If automat does not exists in DB then creates new. If automat already exists
     * then fetches automat from DB and sets all fields from external REST model
     * @param automat REST model
     * @return internal AutomatEntity with all required fields set
     */
    public AutomatEntity toInternal(Automat automat) {
        AutomatEntity automatEntity = null;
        if (automat.automatId != null) {
            automatEntity = automatRepository.findOne(automat.automatId);
        }
        if (automatEntity == null) {
            logger.debug("Creating new automat");
            automatEntity = new AutomatEntity();
            automatEntity.setAutomatId(automat.automatId);
        }
        logger.debug("Updating existing automat");
        automatEntity.setAutomatName(automat.automatName);
        automatEntity.setDescription(automat.description);
        return automatEntity;
    }
}
