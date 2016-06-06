package augustyn.marcin.stockmarket.broker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import augustyn.marcin.stockmarket.broker.entity.OfferEntity;

@Repository
@Transactional(readOnly = true)
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {

	
	@Query("SELECT o FROM OfferEntity o WHERE UPPER(o.status) like UPPER(:status)")
	List<OfferEntity> findOfferByStatus( @Param("status") String status);

	@Query("SELECT o FROM OfferEntity o WHERE o.id = :id")
	List<OfferEntity> findOfferById(@Param("id") Long id);
	
	
}
