package augustyn.marcin.stockmarket.bank.mapper;

import java.util.List;
import java.util.stream.Collectors;

import augustyn.marcin.stockmarket.bank.entity.PlayerFoundEntity;
import augustyn.marcin.stockmarket.bank.to.PlayerFoundTo;

public class PlayerFoundMapper {
	
	public static PlayerFoundTo map(PlayerFoundEntity playerFoundEntity) {
		if (playerFoundEntity != null) {
			return new PlayerFoundTo(playerFoundEntity.getId(), playerFoundEntity.getCurrency(), playerFoundEntity.getQuantity());
		}
		return null;
	}
	
	public static PlayerFoundEntity map(PlayerFoundTo playerFoundTo) {
		if (playerFoundTo != null) {
			return new PlayerFoundEntity(playerFoundTo.getId(), playerFoundTo.getCurrency(), playerFoundTo.getQuantity());
		}
		return null;
	}
	
	public static List<PlayerFoundTo> map2To(List<PlayerFoundEntity> playerFoundEntities) {
		return playerFoundEntities.stream().map(PlayerFoundMapper::map).collect(Collectors.toList());
	}

	public static List<PlayerFoundEntity> map2Entity(List<PlayerFoundTo> playerFoundTos) {
		return playerFoundTos.stream().map(PlayerFoundMapper::map).collect(Collectors.toList());
	}
}
