package augustyn.marcin.stockmarket.stock;

import java.util.List;

import augustyn.marcin.stockmarket.stock.to.ShareDataTo;

public interface Stock {
	
	public ShareDataTo getCurrentDataForShare(String share);
	
	public List<ShareDataTo> getHistoryDataForShare(String share, int historySizeInDays);
	
	public void initShareDataDb();
}
