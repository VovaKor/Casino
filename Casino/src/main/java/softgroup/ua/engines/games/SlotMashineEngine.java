package softgroup.ua.engines.games;

import org.springframework.stereotype.Component;
import softgroup.ua.api.Automat;
import softgroup.ua.jpa.UserEntity;
import softgroup.ua.service.exception.GameException;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Created by Вова on 29.03.2017.
 */
@Component
public class SlotMashineEngine implements GameEngine {

    private final int SLOT1 = 3;
    private final int SLOT2 = 9;
    private final int SLOT3 = 11;
    private final int SEED = 12;

    private BigDecimal balance;

    @Override
    public void play(Automat automat, UserEntity userEntity) throws GameException {
        balance = userEntity.getBalance();
        if (balance.intValue() >= 1) {
            Random random = new Random();

            int slot1 = random.nextInt(SEED);
            int slot2 = random.nextInt(SEED);
            int slot3 = random.nextInt(SEED);

            automat.slots.add(slot1);
            automat.slots.add(slot2);
            automat.slots.add(slot3);

            automat.isWon = slot1 == slot2 && slot1 == slot3;
            if (automat.isWon){
                userEntity.setBalance(balance.add(BigDecimal.valueOf(1)));
            }else {
                userEntity.setBalance(balance.subtract(BigDecimal.valueOf(1)));
            }

        } else {
            throw new GameException("Not enough balance to play");
        }

    }
    @Override
    public void fillSlots(Automat automat) {
        automat.slots.add(SLOT1);
        automat.slots.add(SLOT2);
        automat.slots.add(SLOT3);
    }

}
