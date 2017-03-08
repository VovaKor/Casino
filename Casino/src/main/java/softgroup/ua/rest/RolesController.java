package softgroup.ua.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import softgroup.ua.api.Role;
import softgroup.ua.api.RolesListReply;
import softgroup.ua.jpa.RolesEntity;
import softgroup.ua.service.RolesMapper;
import softgroup.ua.service.RolesService;

/**
 * Created by Вова on 08.03.2017.
 */
@RestController
public class RolesController {
    @Autowired
    RolesService rolesService;
    @Autowired
    RolesMapper rolesMapper;

    @RequestMapping(path="/roles/all",  method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RolesListReply getAllUsers(){
        RolesListReply reply = new RolesListReply();
        for(RolesEntity rolesEntity: rolesService.getAllRoles()){
            reply.roles.add(rolesMapper.fromInternal(rolesEntity));
        }
        return reply;
    }
}
