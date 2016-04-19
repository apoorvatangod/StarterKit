package augustyn.marcin.pokerfinal;

public enum Shapes {
	HEART("H"),
	SPADE("S"),
	DIAMOND("D"),
	CLUB("C");
	
	private String symbol;
	
	private Shapes(String symbol) {
		this.symbol = symbol;
	}
	/**
	 * Gives Shape enum by it's String value.
	 * @param symbol - String for which enum is to be found.
	 * @return enum for symbol
	 * @throws IllegalArgumentException if String argument is not a valid shape
	 */
	public static Shapes getValueBySymbol(String symbol) {
		  for(Shapes e: Shapes.values()) {
		    if(e.symbol.equals(symbol)) {
		      return e;
		    }
		  }
		  throw new IllegalArgumentException(symbol + " is not a valid card shape.");
		} 
}
