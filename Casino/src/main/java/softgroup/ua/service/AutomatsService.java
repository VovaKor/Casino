package softgroup.ua.service;

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
public class AutomatsService extends GeneralServiceImp<AutomatEntity, Integer>{

    @Autowired
    AutomatRepository automatRepository;

    public List <AutomatEntity> getAllAutomats() {
        return automatRepository.findAll();
    }

    @Override
    public JpaRepository<AutomatEntity, Integer> getRepository() {
        return automatRepository;
    }
}
