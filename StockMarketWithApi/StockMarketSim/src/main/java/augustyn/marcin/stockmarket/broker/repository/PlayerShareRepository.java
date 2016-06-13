package augustyn.marcin.stockmarket.broker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import augustyn.marcin.stockmarket.broker.entity.PlayerShareEntity;

@Repository
@Transactional(readOnly = true)
public interface PlayerShareRepository extends JpaRepository<PlayerShareEntity, Long> {

	
	@Query("SELECT ps FROM PlayerShareEntity ps WHERE UPPER(ps.name) like UPPER(:name)")
	PlayerShareEntity findPlayerShareByName( @Param("name") String name);

	@Query("SELECT ps FROM PlayerShareEntity ps WHERE ps.id = :id")
	PlayerShareEntity findPlayerShareById(@Param("id") Long id);
	
}
