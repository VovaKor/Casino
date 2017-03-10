package softgroup.ua.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import softgroup.ua.jpa.GamesEntity;
import softgroup.ua.repository.GamesRepository;

/**
 *
 * @author alexander
 */
@Service
public class GamesService extends GeneralServiceImp<GamesEntity, Long>{
    
    @Autowired
    private GamesRepository gamesRepository;
    
    @Override
    public JpaRepository<GamesEntity, Long> getRepository() {
        return gamesRepository;
    }
    
    public void addGames(GamesEntity games) {
        gamesRepository.saveAndFlush(games);
    }
    
    public List<GamesEntity> getAllGames() {
        return gamesRepository.findAll();
    }
    
    public GamesEntity findGameById(Long gameId) {
        return gamesRepository.findOne(gameId);
    }
    
    public void deleteGame(Long gameId) {
        gamesRepository.delete(gameId);
    }

    public List<GamesEntity> findGamesByDateTime(Date dateTime) {
        return gamesRepository.findByDateTime(dateTime);
    }

    public List<GamesEntity> findGamesByDateTimeBefore(Date dateTime) {
        return gamesRepository.findByDateTimeBefore(dateTime);
    }
    
    public List<GamesEntity> findGamesByDateTimeAfter(Date dateTime) {
        return gamesRepository.findByDateTimeAfter(dateTime);
    }
    
    public List<GamesEntity> findGamesByDateTimeBetween(Date startDateTime, Date endDateTime) {
        return gamesRepository.findByDateTimeBetween(startDateTime, endDateTime);
    }
        
    public List<GamesEntity> findGamesByAmountGreaterThan(BigDecimal amount) {
        return gamesRepository.findByAmountGreaterThan(amount);
    }
    
    public List<GamesEntity> findGamesByAmountLessThan(BigDecimal amount) {
        return gamesRepository.findByAmountLessThan(amount);
    }
    
    
}
