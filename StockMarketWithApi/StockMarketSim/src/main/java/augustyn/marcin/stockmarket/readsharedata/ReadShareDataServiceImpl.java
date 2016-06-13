package augustyn.marcin.stockmarket.readsharedata;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import augustyn.marcin.stockmarket.stock.to.ShareDataTo;

@Service
public class ReadShareDataServiceImpl implements ReadShareDataService {
	private static final Logger logger = LogManager.getLogger(ReadShareDataServiceImpl.class);
	
	@Autowired
	private CsvReader reader;
	
	@Override
	public List<ShareDataTo> readShareData() {
		for(ShareDataTo entity : reader.readRecords()){
			logger.info("Share: " + entity.getName() + ", price: " + entity.getPrice() + ", date: " + entity.getDate());
		}
		return reader.readRecords();
	}

}
