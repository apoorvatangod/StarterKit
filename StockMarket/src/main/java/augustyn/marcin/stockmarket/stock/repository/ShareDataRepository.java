package augustyn.marcin.stockmarket.stock.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import augustyn.marcin.stockmarket.stock.entity.ShareDataEntity;

@Repository
@Transactional(readOnly = true)
public interface ShareDataRepository extends JpaRepository<ShareDataEntity, Long> {

	
	@Query("SELECT sd FROM ShareDataEntity sd WHERE UPPER(sd.name) like UPPER(:name) AND (sd.date BETWEEN :startDate AND :endDate)")
	List<ShareDataEntity> findByNameAndDate( @Param("name") String name, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query("SELECT sd FROM ShareDataEntity sd WHERE sd.id = :id")
	List<ShareDataEntity> findById( @Param("id") Long id);
	
}
