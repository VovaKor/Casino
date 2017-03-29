package softgroup.ua.service.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softgroup.ua.jpa.AutomatEntity;
import softgroup.ua.repository.AutomatRepository;

/**
 * Created by Вова on 12.03.2017.
 */
@Service
public class BootstrapAutomat implements InitializingBean {
    private static final Logger logger =  LoggerFactory.getLogger(BootstrapAutomat.class);
    @Autowired
    private AutomatRepository automatRepository;
    @Override
    @Transactional
    public void afterPropertiesSet() throws Exception {
        createAutomat();
    }
    private void createAutomat() {

            automatRepository.save(new AutomatEntity(1,"Slot machine","A slot machine is a casino gambling machine with three reels which spin when a button is pushed. Standard bet is 1 EURO."));
            logger.debug("Created automat \"Slot machine\"");

    }
}
