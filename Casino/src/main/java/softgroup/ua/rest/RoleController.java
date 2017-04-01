package softgroup.ua.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import softgroup.ua.api.AddRoleRequest;
import softgroup.ua.api.GenericReply;
import softgroup.ua.api.RolesListReply;
import softgroup.ua.jpa.RoleEntity;
import softgroup.ua.service.mappers.RoleMapper;
import softgroup.ua.service.RoleService;

/**
 * Created by Вова on 08.03.2017.
 */
@RestController
public class RoleController {
    private static final Logger logger =  LoggerFactory.getLogger(RoleController.class);
    @Autowired
    RoleService roleService;
    @Autowired
    RoleMapper roleMapper;

    @RequestMapping(path="/roles/all",  method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RolesListReply getAllRoles(){
        RolesListReply reply = new RolesListReply();
        for(RoleEntity roleEntity : roleService.getAllRoles()){
            reply.roles.add(roleMapper.fromInternal(roleEntity));
        }
        return reply;
    }

    @RequestMapping(path="/roles/byId/{roleId}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RolesListReply getRoleById(@PathVariable Integer roleId){
        RolesListReply reply = new RolesListReply();
        reply.roles.add(roleMapper.fromInternal(roleService.getRoleById(roleId)));
        return reply;
    }

    @RequestMapping(path="/roles/add",  method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RolesListReply addRole (@RequestBody AddRoleRequest addRoleRequest){
        RolesListReply reply = new RolesListReply();
        try {
            RoleEntity roleEntity = roleService.addRole(roleMapper.toInternal(addRoleRequest.role));
            reply.roles.add(roleMapper.fromInternal(roleEntity));
        }catch (Exception e){
            reply.retcode = -1;
            reply.error_message = e.getMessage();
            logger.error("Error adding role. Exception: "+e.getMessage(),e);
        }
        return reply;
    }
    @RequestMapping(path="/roles/update",  method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RolesListReply updateRole(@RequestBody AddRoleRequest addRoleRequest){
        RolesListReply reply = new RolesListReply();
        try {
            RoleEntity roleEntity = roleService.updateRole(roleMapper.toInternal(addRoleRequest.role));
            reply.roles.add(roleMapper.fromInternal(roleEntity));
        }catch (Exception e){
            reply.retcode = -1;
            reply.error_message = e.getMessage();
            logger.error("Error updating role. Exception: "+e.getMessage(),e);
        }
        return reply;
    }
    @RequestMapping(path="/roles/delete/{roleId}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericReply deleteRole(@PathVariable Integer roleId){
        GenericReply reply = new GenericReply();
        try {
            roleService.deleteRole(roleId);
        }catch (Exception e){
            reply.retcode = -1;
            reply.error_message = e.getMessage();
            logger.error("Error deleting role. Exception: "+e.getMessage(),e);
        }
        return reply;
    }

}
