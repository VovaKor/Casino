package softgroup.ua.engines.games;

import softgroup.ua.api.Automat;
import softgroup.ua.jpa.UserEntity;
import softgroup.ua.service.exception.GameException;

/**
 * Created by Вова on 01.04.2017.
 */
public interface GameEngine {

    void play(Automat automat, UserEntity userEntity) throws GameException;

    void fillSlots(Automat automat);

}
