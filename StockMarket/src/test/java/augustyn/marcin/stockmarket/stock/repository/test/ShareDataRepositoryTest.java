package augustyn.marcin.stockmarket.stock.repository.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import augustyn.marcin.stockmarket.stock.entity.ShareDataEntity;
import augustyn.marcin.stockmarket.stock.repository.ShareDataRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ShareDataRepositoryTest {

	@Autowired
	private ShareDataRepository shareDataRepository;
	
	@Test
	public void testShouldFindAllShareData() {
		// given
		
		// when
		List<ShareDataEntity> sharesData = shareDataRepository.findAll();
		int recordsFound = sharesData.size();
		// then
		assertEquals(9, recordsFound);
	}
	
	@Test
	public void testShouldFindById() {
		// given
		
		// when
		List<ShareDataEntity> sharesData = shareDataRepository.findById(1L);
		ShareDataEntity shareData = sharesData.get(0);

		// then
		assertEquals(1, sharesData.size());
		assertEquals("PKO", shareData.getName());
		assertEquals(1000, shareData.getPrice());
	}
	
	@Test
	public void testShouldFindByDate() {
		// given
		Date startDate = new Date(990403200000L);
		Date endDate = new Date(990619200000L);
		// when
		
		List<ShareDataEntity> sharesData = shareDataRepository.findByNameAndDate("PKO", startDate, endDate);
		int recordsFound = sharesData.size();

		// then
		assertEquals(2, recordsFound);
	}
	
	@Test
	public void testShouldNotFindByDate() {
		// given
		Date startDate = new Date(1022025600000L);
		Date endDate = new Date(1022198400000L);
		// when
		List<ShareDataEntity> sharesData = shareDataRepository.findByNameAndDate("PKO", startDate, endDate);

		// then
		assertTrue(sharesData.isEmpty());
	}
	
	@Test
	public void testShouldNotFindById() {
		// given
		
		// when
		List<ShareDataEntity> sharesData = shareDataRepository.findById(null);

		// then
		assertTrue(sharesData.isEmpty());
	}
	
	@Test
	public void testShouldSaveNewEntity() {
		// given
		
		Date startDate = new Date(1104537600000L);
		Date date = new Date(1104624000000L);
		Date endDate = new Date(1104710400000L);

		ShareDataEntity newEntity = new ShareDataEntity(null, "PKO", 111, date);
		
		// when
		shareDataRepository.save(newEntity);
		
		List<ShareDataEntity> sharesData = shareDataRepository.findByNameAndDate("PKO", startDate, endDate);
		ShareDataEntity shareData = sharesData.get(0);
		// then
		
		assertEquals(1, sharesData.size());
		assertEquals("PKO", shareData.getName());
		assertEquals(111, shareData.getPrice());
		
		//after
		shareDataRepository.delete(sharesData.get(0));
	}
}
