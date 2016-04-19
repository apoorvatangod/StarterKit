package augustyn.marcin.pokerfinal;

/**
 * Class that holds information about a single card.
 * @author MARCIAUG
 *
 */
public class Card{
	private int figure = 0;
	private Shapes shape;
	/**
	 * public constructor
	 * @param figure - figure of the card - starts with 2,3,... and ends with 12(represents Queen),13(King),14(Ace)
	 * @param shape - shape of the card - S=Spades, H=Hearts, D=Diamonds, C=Clubs
	 */
	public Card(int figure, Shapes shape) {
		this.figure = figure;
		this.shape = shape;
	}

	public int getFigure() {
		return figure;
	}

	public Shapes getShape() {
		return shape;
	}

	@Override
	public String toString() {
		return "Card [figure=" + figure + ", shape=" + shape + "]";
	}	
}
