package softgroup.ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softgroup.ua.jpa.TransactionEntity;

/**
 *
 * @author alexander
 */
@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    
}
