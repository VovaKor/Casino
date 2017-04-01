package softgroup.ua.authorization;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by alexander on 29.03.17.
 */
@Service
public class TokenProvider {

    @Autowired
    private HazelcastInstance hazelcastInstance;

    public void put(String token, AuthenticatedUser user) {
        hazelcastInstance.getMap("token-cache").put(token, user);
    }

    public AuthenticatedUser get(String token) {
        AuthenticatedUser user = null;
        if (token != null) {
            user = (AuthenticatedUser) hazelcastInstance.getMap("token-cache").get(token);
        }
        return user;
    }

    public String newToken(){
        return UUID.randomUUID().toString();
    }
}
