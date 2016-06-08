package augustyn.marcin.stockmarket.broker.repository.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import augustyn.marcin.stockmarket.broker.entity.OfferEntity;
import augustyn.marcin.stockmarket.broker.repository.OfferRepository;
import augustyn.marcin.stockmarket.enumation.ActionType;
import augustyn.marcin.stockmarket.enumation.Currency;
import augustyn.marcin.stockmarket.enumation.OfferStatus;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class OfferRepositoryTest {

	@Autowired
	private OfferRepository offerRepository;
	
	@Test
	public void testShouldFindAllOffers() {
		// given
		
		// when
		List<OfferEntity> offers = offerRepository.findAll();
		int recordsFound = offers.size();
		// then
		assertEquals(5, recordsFound);
	}
	
	@Test
	public void testShouldFindById() {
		// given
		
		// when
		OfferEntity offer = offerRepository.findOfferById(1L);

		// then
		assertEquals(ActionType.BUY, offer.getActionType());
		assertEquals("PKO", offer.getShare());
		assertEquals(5, offer.getQuantity());
		assertEquals(1000, offer.getPrice());
		assertEquals(Currency.PLN, offer.getCurrency());
		assertEquals(500, offer.getComission());
		assertEquals(OfferStatus.ACTIVE, offer.getStatus());
	}
	
	@Test
	public void testShouldFindByStatus() {
		// given
		
		// when
		List<OfferEntity> offers = offerRepository.findOffersByStatus(OfferStatus.ACTIVE.toString());
		int recordsFound = offers.size();

		// then
		assertEquals(2, recordsFound);
	}
	
	@Test
	public void testShouldNotFindByStatus() {
		// given
		
		// when
		List<OfferEntity> offers = offerRepository.findOffersByStatus("Not a status");

		// then
		assertTrue(offers.isEmpty());
	}
	
	@Test
	public void testShouldNotFindById() {
		// given
		
		// when
		OfferEntity offer = offerRepository.findOfferById(null);

		// then
		assertNull(offer);
	}
	
	@Test
	public void testShouldSaveNewEntity() {
		// given
		OfferEntity newEntity = new OfferEntity(null, ActionType.BUY, "CORP", 100, 700, new Date(), Currency.PLN, 500, OfferStatus.COMPLETED);
		
		// when
		offerRepository.save(newEntity);
		
		List<OfferEntity> offers = offerRepository.findOffersByStatus(OfferStatus.COMPLETED.toString());
		OfferEntity offer = offers.get(0);
		// then
		
		assertEquals(1, offers.size());
		assertEquals(ActionType.BUY, offer.getActionType());
		assertEquals("CORP", offer.getShare());
		assertEquals(100, offer.getQuantity());
		assertEquals(700, offer.getPrice());
		assertEquals(Currency.PLN, offer.getCurrency());
		assertEquals(500, offer.getComission());
		assertEquals(OfferStatus.COMPLETED, offer.getStatus());
		
		//after
		offerRepository.delete(offers.get(0));
	}
}
