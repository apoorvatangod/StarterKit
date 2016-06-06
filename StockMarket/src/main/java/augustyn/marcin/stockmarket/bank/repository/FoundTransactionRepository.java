package augustyn.marcin.stockmarket.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import augustyn.marcin.stockmarket.bank.entity.FoundTransactionEntity;

@Repository
@Transactional(readOnly = true)
public interface FoundTransactionRepository extends JpaRepository<FoundTransactionEntity, Long> {

	@Query("SELECT ft FROM FoundTransactionEntity ft WHERE ft.id = :id")
	List<FoundTransactionEntity> findTransactionById(@Param("id") Long id);
	
	
}
