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
		List<PlayerShareEntity> shares = playerShareRepository.findPlayerShareById(1L);
		int recordsFound = shares.size();
		String shareName = shares.get(0).getName();
		// then
		assertEquals(1, recordsFound);
		assertEquals("PKO", shareName);
	}
	
	@Test
	public void testShouldFindByName() {
		// given
		
		// when
		List<PlayerShareEntity> shares = playerShareRepository.findPlayerShareByName("KGHM");
		int recordsFound = shares.size();
		String shareName = shares.get(0).getName();
		// then
		assertEquals(1, recordsFound);
		assertEquals("KGHM", shareName);
	}
	
	@Test
	public void testShouldNotFindByName() {
		// given
		
		// when
		List<PlayerShareEntity> shares = playerShareRepository.findPlayerShareByName("Not a share");

		// then
		assertTrue(shares.isEmpty());
	}
	
	@Test
	public void testShouldNotFindById() {
		// given
		
		// when
		List<PlayerShareEntity> shares = playerShareRepository.findPlayerShareById(null);

		// then
		assertTrue(shares.isEmpty());
	}
	
	@Test
	public void testShouldSaveNewEntity() {
		// given
		PlayerShareEntity newEntity = new PlayerShareEntity(null, "CORP", 100);
		
		// when
		playerShareRepository.save(newEntity);
		
		List<PlayerShareEntity> shares = playerShareRepository.findPlayerShareByName("CORP");
		// then
		
		assertEquals(shares.size(), 1);
		assertEquals(shares.get(0).getName(), "CORP");
		assertEquals(shares.get(0).getQuantity(), 100);
		
		//after
		playerShareRepository.delete(shares.get(0));
	}
}
