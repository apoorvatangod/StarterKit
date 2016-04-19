package augustyn.marcin.pokerfinal;

public enum Figures {
	TWO(2,"2"),
	THREE(3,"3"),
	FOUR(4,"4"),
	FIVE(5,"5"),
	SIX(6,"6"),
	SEVEN(7,"7"),
	EIGHT(8,"8"),
	NINE(9,"9"),
	TEN(10,"T"),
	JACK(11, "J"),
	QUEEN(12, "Q"),
	KING(13, "K"),
	ACE(14, "A");
	
	private int figure;
	private String symbol;
	
	private Figures(int figure, String symbol) {
		this.figure = figure;
		this.symbol = symbol;
	}
	public static int getValueBySymbol(String symbol) {
		  for(Figures e: Figures.values()) {
		    if(e.symbol.equals(symbol)) {
		      return e.getFigure();
		    }
		  }
		  throw new IllegalArgumentException(symbol + " is not a valid card symbol.");
		} 
	public int getFigure() {
		return figure;
	}
}
