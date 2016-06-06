package augustyn.marcin.stockmarket.broker.repository.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import augustyn.marcin.stockmarket.broker.mapper.PlayerShareMapper;
import augustyn.marcin.stockmarket.broker.repository.PlayerShareRepository;
import augustyn.marcin.stockmarket.broker.to.PlayerShareTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class RepoTest {

	@Autowired
	private PlayerShareRepository repo;

	@Before
	public void init(){

	}
	
	@Test
	public void testShouldFindAllShares() {
		// given
		
		// when
		List<PlayerShareTo> shares = PlayerShareMapper.map2To(repo.findAll());
		int recordsFound = shares.size();
		// then
		assertEquals(1, recordsFound);
		assertEquals("PKO", shares.get(0).getName());
	}
}
