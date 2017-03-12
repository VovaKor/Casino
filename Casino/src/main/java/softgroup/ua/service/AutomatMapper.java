package softgroup.ua.service;

import org.springframework.stereotype.Component;
import softgroup.ua.api.Automat;
import softgroup.ua.jpa.AutomatEntity;

/**
 * Created by Вова on 08.03.2017.
 */
@Component
public class AutomatMapper {
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
}
