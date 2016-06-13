package augustyn.marcin.stockmarket.broker.mapper;

import java.util.List;
import java.util.stream.Collectors;

import augustyn.marcin.stockmarket.broker.entity.PlayerShareEntity;
import augustyn.marcin.stockmarket.broker.to.PlayerShareTo;

public class PlayerShareMapper {
	
	public static PlayerShareTo map(PlayerShareEntity playerShareEntity) {
		if (playerShareEntity != null) {
			return new PlayerShareTo(playerShareEntity.getId(), playerShareEntity.getName(), playerShareEntity.getQuantity());
		}
		return null;
	}
	
	public static PlayerShareEntity map(PlayerShareTo playerShareTo) {
		if (playerShareTo != null) {
			return new PlayerShareEntity(playerShareTo.getId(), playerShareTo.getName(), playerShareTo.getQuantity());
		}
		return null;
	}
	
	public static List<PlayerShareTo> map2To(List<PlayerShareEntity> playerShareEntities) {
		return playerShareEntities.stream().map(PlayerShareMapper::map).collect(Collectors.toList());
	}

	public static List<PlayerShareEntity> map2Entity(List<PlayerShareTo> playerShareTos) {
		return playerShareTos.stream().map(PlayerShareMapper::map).collect(Collectors.toList());
	}
}
