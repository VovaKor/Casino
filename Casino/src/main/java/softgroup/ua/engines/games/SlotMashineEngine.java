package softgroup.ua.engines.games;

import org.springframework.stereotype.Component;
import softgroup.ua.api.Automat;

import java.util.Random;

/**
 * Created by Вова on 29.03.2017.
 */
@Component
public class SlotMashineEngine implements GameEngine {

    private final Integer SLOT1 = 3;
    private final Integer SLOT2 = 9;
    private final Integer SLOT3 = 12;

    public void play(Automat automat) {
        Random random = new Random();

        int slot1 = random.nextInt(SLOT3);
        int slot2 = random.nextInt(SLOT3);
        int slot3 = random.nextInt(SLOT3);

        automat.slots.add(slot1);
        automat.slots.add(slot2);
        automat.slots.add(slot3);

        automat.isWon = slot1 == slot2 && slot1 == slot3;

    }

    public void fillSlots(Automat automat) {
        automat.slots.add(SLOT1);
        automat.slots.add(SLOT2);
        automat.slots.add(SLOT3);
    }
    }
