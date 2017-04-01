package softgroup.ua.engines.games;

import softgroup.ua.api.Automat;

import java.util.Random;

/**
 * Created by Вова on 01.04.2017.
 */
public interface GameEngine {

    void play(Automat automat);

    void fillSlots(Automat automat);
}
