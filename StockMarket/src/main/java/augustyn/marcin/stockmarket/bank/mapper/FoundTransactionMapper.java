package augustyn.marcin.stockmarket.bank.mapper;

import java.util.List;
import java.util.stream.Collectors;

import augustyn.marcin.stockmarket.bank.entity.FoundTransactionEntity;
import augustyn.marcin.stockmarket.bank.to.FoundTransactionTo;

public class FoundTransactionMapper {
	
	public static FoundTransactionTo map(FoundTransactionEntity foundTransactionEntity) {
		if (foundTransactionEntity != null) {
			return new FoundTransactionTo(foundTransactionEntity.getId(),foundTransactionEntity.getType(), foundTransactionEntity.getCurrency(),
					foundTransactionEntity.getQuantity(), foundTransactionEntity.getDate());
		}
		return null;
	}
	
	public static FoundTransactionEntity map(FoundTransactionTo foundTransactionTo) {
		if (foundTransactionTo != null) {
			return new FoundTransactionEntity(foundTransactionTo.getId(),foundTransactionTo.getType(), foundTransactionTo.getCurrency(),
					foundTransactionTo.getQuantity(), foundTransactionTo.getDate());
		}
		return null;
	}
	
	public static List<FoundTransactionTo> map2To(List<FoundTransactionEntity> foundTransactionEntities) {
		return foundTransactionEntities.stream().map(FoundTransactionMapper::map).collect(Collectors.toList());
	}

	public static List<FoundTransactionEntity> map2Entity(List<FoundTransactionTo> foundTransactionTos) {
		return foundTransactionTos.stream().map(FoundTransactionMapper::map).collect(Collectors.toList());
	}
}
