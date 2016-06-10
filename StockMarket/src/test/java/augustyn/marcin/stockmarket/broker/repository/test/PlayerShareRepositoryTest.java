package augustyn.marcin.stockmarket.broker.repository.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import augustyn.marcin.stockmarket.broker.entity.PlayerShareEntity;
import augustyn.marcin.stockmarket.broker.repository.PlayerShareRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PlayerShareRepositoryTest {

	@Autowired
	private PlayerShareRepository playerShareRepository;
	
	@Test
	public void testShouldFindAllShares() {
		// given
		
		// when
		List<PlayerShareEntity> shares = playerShareRepository.findAll();
		int recordsFound = shares.size();
		// then
		assertEquals(3, recordsFound);
	}
	
	@Test
	public void testShouldFindById() {
		// given
		
		// when
		PlayerShareEntity share = playerShareRepository.findPlayerShareById(1L);
		String shareName = share.getName();
		// then
		assertEquals("PKO", shareName);
	}
	
	@Test
	public void testShouldFindByName() {
		// given
		
		// when
		PlayerShareEntity share = playerShareRepository.findPlayerShareByName("KGHM");

		String shareName = share.getName();
		// then
		assertEquals("KGHM", shareName);
	}
	
	@Test
	public void testShouldNotFindByName() {
		// given
		
		// when
		PlayerShareEntity share = playerShareRepository.findPlayerShareByName("Not a share");

		// then
		assertTrue(share == null);
	}
	
	@Test
	public void testShouldNotFindById() {
		// given
		
		// when
		PlayerShareEntity share = playerShareRepository.findPlayerShareById(null);

		// then
		assertTrue(share == null);
	}
	
	@Test
	public void testShouldSaveNewEntity() {
		// given
		PlayerShareEntity newEntity = new PlayerShareEntity(null, "CORP", 100);
		
		// when
		playerShareRepository.save(newEntity);
		
		PlayerShareEntity share = playerShareRepository.findPlayerShareByName("CORP");
		
		// then
		assertEquals(share.getName(), "CORP");
		assertEquals(share.getQuantity(), 100);
		
		//after
		playerShareRepository.delete(share);
	}
}
