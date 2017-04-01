package softgroup.ua.service.mappers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import softgroup.ua.api.Automat;
import softgroup.ua.jpa.AutomatEntity;
import softgroup.ua.repository.AutomatRepository;
import softgroup.ua.service.exception.ParsingException;

/**
 * Created by Вова on 08.03.2017.
 */
@Component
public class AutomatMapper implements GenericMapper<AutomatEntity, Automat>{
    private static final Logger logger =  LoggerFactory.getLogger(AutomatMapper.class);
    @Autowired
    AutomatRepository automatRepository;
    /**
     * Maps internal JPA model to external REST model
     * @param automatEntity internal automat model
     * @return external REST automat model
     */
    @Override
    public Automat fromInternal(AutomatEntity automatEntity) throws ParsingException {
        Automat automat = null;
        if (automatEntity != null) {
            automat = new Automat();
            automat.automatId = automatEntity.getAutomatId();
            automat.automatName = automatEntity.getAutomatName();
            automat.description = automatEntity.getDescription();
        }else
            throw new ParsingException("Parse exception: automatEntity = null");
        return automat;
    }
    /**
     * Maps external REST model to internal automat entity;
     * If automat does not exists in DB then creates new. If automat already exists
     * then fetches automat from DB and sets all fields from external REST model
     * @param automat REST model
     * @return internal AutomatEntity with all required fields set
     */
    @Override
    public AutomatEntity toInternal(Automat automat) throws ParsingException {
        AutomatEntity automatEntity;
        if (automat != null) {
            automatEntity = null;
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
        } else
            throw new ParsingException("Parse exception: Automat = null");
        return automatEntity;
    }
}
