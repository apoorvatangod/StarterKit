package augustyn.marcin.stockmarket.readsharedata;

import java.util.List;

import augustyn.marcin.stockmarket.stock.to.ShareDataTo;

public interface ReadShareDataService {

	public List<ShareDataTo> readShareData();
}
