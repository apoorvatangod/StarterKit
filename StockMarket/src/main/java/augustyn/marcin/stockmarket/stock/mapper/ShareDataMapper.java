package augustyn.marcin.stockmarket.stock.mapper;

import java.util.List;
import java.util.stream.Collectors;

import augustyn.marcin.stockmarket.stock.entity.ShareDataEntity;
import augustyn.marcin.stockmarket.stock.to.ShareDataTo;

public class ShareDataMapper {
	
	public static ShareDataTo map(ShareDataEntity shareDataEntity) {
		if (shareDataEntity != null) {
			return new ShareDataTo(shareDataEntity.getId(), shareDataEntity.getName(), shareDataEntity.getPrice(), shareDataEntity.getDate());
		}
		return null;
	}
	
	public static ShareDataEntity map(ShareDataTo shareDataTo) {
		if (shareDataTo != null) {
			return new ShareDataEntity(shareDataTo.getId(), shareDataTo.getName(), shareDataTo.getPrice(), shareDataTo.getDate());
		}
		return null;
	}
	
	public static List<ShareDataTo> map2To(List<ShareDataEntity> offerEntities) {
		return offerEntities.stream().map(ShareDataMapper::map).collect(Collectors.toList());
	}

	public static List<ShareDataEntity> map2Entity(List<ShareDataTo> offerTos) {
		return offerTos.stream().map(ShareDataMapper::map).collect(Collectors.toList());
	}
}
