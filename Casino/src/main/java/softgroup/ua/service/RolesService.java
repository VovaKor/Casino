package softgroup.ua.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softgroup.ua.jpa.RolesEntity;
import softgroup.ua.repository.RolesRepository;

import java.util.List;

/**
 * Created by Вова on 08.03.2017.
 */
@Service
public class RolesService {
    //private static final Logger logger =  LoggerFactory.getLogger(RolesService.class);
    @Autowired
    RolesRepository rolesRepository;
    public List<RolesEntity> getAllRoles(){
        return  rolesRepository.findAll();
    }
}
