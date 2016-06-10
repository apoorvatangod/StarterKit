package augustyn.marcin.stockmarket.main.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import augustyn.marcin.stockmarket.bank.entity.PlayerFoundEntity;
import augustyn.marcin.stockmarket.bank.repository.PlayerFoundRepository;
import augustyn.marcin.stockmarket.enumation.Currency;
import augustyn.marcin.stockmarket.main.Main;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class MainTest {

	@Autowired
	private Main main;
	
	@Autowired
	private PlayerFoundRepository playerFoundRepository;
	
	@Test
	public void testShouldFindAllOffers() {
		//before
		PlayerFoundEntity found = playerFoundRepository.findPlayerFoundByCurrency(Currency.PLN.toString());
		found.setQuantity(1000000);
		playerFoundRepository.save(found);
		
		// given
		
		// when
		main.executeSim();
		
		// then

	}
}
