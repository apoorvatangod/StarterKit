package augustyn.marcin.stockmarket.bank.repository.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import augustyn.marcin.stockmarket.bank.entity.PlayerFoundEntity;
import augustyn.marcin.stockmarket.bank.repository.PlayerFoundRepository;
import augustyn.marcin.stockmarket.enumation.Currency;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PlayerFoundRepositoryTest {

	@Autowired
	private PlayerFoundRepository playerFoundRepository;
	
	@Test
	public void testShouldFindAllOffers() {
		// given
		
		// when
		List<PlayerFoundEntity> playerFounds = playerFoundRepository.findAll();
		int recordsFound = playerFounds.size();
		// then
		assertEquals(3, recordsFound);
	}
	
	@Test
	public void testShouldFindById() {
		// given
		
		// when
		List<PlayerFoundEntity> playerFounds = playerFoundRepository.findPlayerFoundById(1L);
		PlayerFoundEntity playerFound = playerFounds.get(0);

		// then
		assertEquals(1, playerFounds.size());
		assertEquals(Currency.PLN, playerFound.getCurrency());
		assertEquals(1000000, playerFound.getQuantity());

	}
	
	@Test
	public void testShouldFindByCurrency() {
		// given
		
		// when
		List<PlayerFoundEntity> playerFounds = playerFoundRepository.findPlayerFoundByCurrency(Currency.EUR.toString());
		int recordsFound = playerFounds.size();
		PlayerFoundEntity playerFound = playerFounds.get(0);

		// then
		assertEquals(1, recordsFound);
		assertEquals(Currency.EUR, playerFound.getCurrency());
		assertEquals(1, playerFound.getQuantity());
	}
	
	@Test
	public void testShouldNotFindByCurrency() {
		// given
		
		// when
		List<PlayerFoundEntity> playerFounds = playerFoundRepository.findPlayerFoundByCurrency("Not a currency");

		// then
		assertTrue(playerFounds.isEmpty());
	}
	
	@Test
	public void testShouldNotFindById() {
		// given
		
		// when
		List<PlayerFoundEntity> playerFounds = playerFoundRepository.findPlayerFoundById(null);

		// then
		assertTrue(playerFounds.isEmpty());
	}
	
	@Test
	public void testShouldSaveNewEntity() {
		// given
		PlayerFoundEntity newEntity = new PlayerFoundEntity(null, Currency.GBP, 7);
		
		// when
		playerFoundRepository.save(newEntity);
		
		List<PlayerFoundEntity> playerFounds = playerFoundRepository.findPlayerFoundByCurrency(Currency.GBP.toString());
		PlayerFoundEntity playerFound = playerFounds.get(0);
		int recordsFound = playerFounds.size();
		// then
		
		assertEquals(1, recordsFound);
		assertEquals(Currency.GBP, playerFound.getCurrency());
		assertEquals(7, playerFound.getQuantity());
		
		//after
		playerFoundRepository.delete(playerFounds.get(0));
	}
}
