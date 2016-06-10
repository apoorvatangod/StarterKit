package augustyn.marcin.stockmarket.stock;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import augustyn.marcin.stockmarket.calendar.MyCalendar;
import augustyn.marcin.stockmarket.readsharedata.ReadShareDataService;
import augustyn.marcin.stockmarket.stock.mapper.ShareDataMapper;
import augustyn.marcin.stockmarket.stock.repository.ShareDataRepository;
import augustyn.marcin.stockmarket.stock.to.ShareDataTo;

@Service
public class StockImpl implements Stock {
	
	@Autowired
	private ShareDataRepository shareDataRepository;
	
	@Autowired
	private ReadShareDataService readShareDataService;
	
	@Autowired
	private MyCalendar calendar;

	@Override
	public ShareDataTo getCurrentDataForShare(String share) {
		return ShareDataMapper.map(shareDataRepository.findCurrentByName(share, calendar.getCurrentDate().toDate()));
	}

	@Override
	public List<ShareDataTo> getHistoryDataForShare(String share, int historySizeInDays) {
		return ShareDataMapper.map2To(shareDataRepository.findByNameAndDate(share, calendar.getCurrentDate().minusDays(historySizeInDays).toDate(), 
				calendar.getCurrentDate().toDate()));
	}
	
	@PostConstruct
	@Override
	public void initShareDataDb() {
		shareDataRepository.save(ShareDataMapper.map2Entity(readShareDataService.readShareData()));
	}

}
