package augustyn.marcin.stockmarket.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import augustyn.marcin.stockmarket.bank.entity.PlayerFoundEntity;

@Repository
@Transactional(readOnly = true)
public interface PlayerFoundRepository extends JpaRepository<PlayerFoundEntity, Long> {

	@Query("SELECT pf FROM PlayerFoundEntity pf WHERE UPPER(pf.currency) like UPPER(:currency)")
	PlayerFoundEntity findPlayerFoundByCurrency( @Param("currency") String currency);

	@Query("SELECT pf FROM PlayerFoundEntity pf WHERE pf.id = :id")
	PlayerFoundEntity findPlayerFoundById(@Param("id") Long id);
}
