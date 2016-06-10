package augustyn.marcin.stockmarket.readsharedata.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import augustyn.marcin.stockmarket.readsharedata.ReadShareDataService;
import augustyn.marcin.stockmarket.stock.to.ShareDataTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ReadShareDataImplTest {

	@Autowired
	private ReadShareDataService readShareDataService;

	@Test
	public void testReadShareData() {
		// given

		// when
		List<ShareDataTo> shareData = readShareDataService.readShareData();
		
		// then
		assertEquals(shareData.get(0).getName(), "PKOBP");
		assertEquals(shareData.get(0).getPrice(), 3735);
		assertEquals(shareData.get(0).getDate(), new DateTime(2013, 1, 2, 0, 0, 0).toDate());
		assertEquals(shareData.get(1).getName(), "KGHM");
		assertEquals(shareData.get(1).getPrice(), 19310);
		assertEquals(shareData.get(1).getDate(), new DateTime(2013, 1, 2, 0, 0, 0).toDate());
		assertEquals(shareData.get(2).getName(), "PGNIG");
		assertEquals(shareData.get(2).getPrice(), 526);
		assertEquals(shareData.get(2).getDate(), new DateTime(2013, 1, 2, 0, 0, 0).toDate());
	}
}
