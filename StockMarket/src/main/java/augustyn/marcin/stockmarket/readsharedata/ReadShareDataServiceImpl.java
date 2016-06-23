package augustyn.marcin.stockmarket.readsharedata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import augustyn.marcin.stockmarket.stock.to.ShareDataTo;

@Service
public class ReadShareDataServiceImpl implements ReadShareDataService {
	
	@Autowired
	private CsvReader reader;
	
	@Override
	public List<ShareDataTo> readShareData() {
		return reader.readRecords();
	}

}
