package augustyn.marcin.stockmarket.broker.mapper;

import java.util.List;
import java.util.stream.Collectors;

import augustyn.marcin.stockmarket.broker.entity.OfferEntity;
import augustyn.marcin.stockmarket.broker.to.OfferTo;

public class OfferMapper {
	
	public static OfferTo map(OfferEntity offerEntity) {
		if (offerEntity != null) {
			return new OfferTo(offerEntity.getId(), offerEntity.getActionType(), offerEntity.getShare(), offerEntity.getQuantity(),
					offerEntity.getPrice(), offerEntity.getDate(), offerEntity.getCurrency(), offerEntity.getComission(), offerEntity.getStatus());
		}
		return null;
	}
	
	public static OfferEntity map(OfferTo offerTo) {
		if (offerTo != null) {
			return new OfferEntity(offerTo.getId(), offerTo.getActionType(), offerTo.getShare(), offerTo.getQuantity(),
					offerTo.getPrice(), offerTo.getDate(), offerTo.getCurrency(), offerTo.getComission(), offerTo.getStatus());
		}
		return null;
	}
	
	public static List<OfferTo> map2To(List<OfferEntity> offerEntities) {
		return offerEntities.stream().map(OfferMapper::map).collect(Collectors.toList());
	}

	public static List<OfferEntity> map2Entity(List<OfferTo> offerTos) {
		return offerTos.stream().map(OfferMapper::map).collect(Collectors.toList());
	}
}
