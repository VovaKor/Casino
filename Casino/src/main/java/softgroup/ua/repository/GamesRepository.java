package softgroup.ua.repository;

import java.math.BigDecimal;
import java.util.Date;
import softgroup.ua.jpa.GamesEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Anjey-13 on 09.03.2017.
 */
public interface GamesRepository extends JpaRepository<GamesEntity, Long> {
    public List <GamesEntity> findByLoginId (String loginId);
    
    public List <GamesEntity> findGamesByGamesId (Long gameId);
    
    public List<GamesEntity> findByDateTimeBefore(Date dateTime);
    
    public List<GamesEntity> findByDateTimeAfter(Date dateTime);
    
    public List<GamesEntity> findByDateTimeBetween(Date startDateTime, Date endDateTime);
    
    public List<GamesEntity> findByAmountGreaterThan(BigDecimal amount);
    
    public List<GamesEntity> findByAmountLessThan(BigDecimal amount);

    public List<GamesEntity> findByDateTime(Date dateTime);
}
