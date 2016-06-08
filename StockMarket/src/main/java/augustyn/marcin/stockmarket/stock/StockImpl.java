package augustyn.marcin.stockmarket.stock;

import java.util.List;

import org.springframework.stereotype.Service;

import augustyn.marcin.stockmarket.stock.to.ShareDataTo;

@Service
public class StockImpl implements Stock {

	@Override
	public ShareDataTo getCurrentDataForShare(String share) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShareDataTo> getHistoryDataForShare(String share, int historySizeInDays) {
		// TODO Auto-generated method stub
		return null;
	}

}
