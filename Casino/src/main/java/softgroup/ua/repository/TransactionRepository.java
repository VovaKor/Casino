package softgroup.ua.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softgroup.ua.jpa.TransactionEntity;

/**
 *
 * @author alexander
 */
@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    
    public List<TransactionEntity> findByDateTime(Date dateTime);
    
    /**
     *
     * @param dateTime dateTime of transaction
     * @return List of transactions from database
     */
    public List<TransactionEntity> findByDateTimeBefore(Date dateTime);
    
    public List<TransactionEntity> findByDateTimeAfter(Date dateTime);
    
    public List<TransactionEntity> findByDateTimeBetween(Date startDateTime, Date endDateTime);
    
    public List<TransactionEntity> findByAmount(BigDecimal amount);
    
    public List<TransactionEntity> findByAmountGreaterThan(BigDecimal amount);
    
    public List<TransactionEntity> findByAmountLessThan(BigDecimal amount);
        
    public List<TransactionEntity> findByInfoContaining(String info);
    
}
