package augustyn.marcin.pokerfinal;

public enum Sets {
	HIGH_CARD(0), PAIR(1), TWO_PAIRS(2), THREE_OF_KIND(3), STRAIGHT(4), FLUSH(5), FULL_HOUSE(6),
	FOUR_OF_KIND(7);
	
	private int value;

	private Sets(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
}
