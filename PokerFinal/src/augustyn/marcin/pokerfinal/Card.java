package augustyn.marcin.pokerfinal;

/**
 * Class that holds information about a single card.
 * @author MARCIAUG
 *
 */
public class Card{
	private Figures figure;
	private Shapes shape;
	/**
	 * public constructor
	 * @param figure - figure of the card
	 * @param shape - shape of the card - S=Spades, H=Hearts, D=Diamonds, C=Clubs
	 */
	public Card(Figures figure, Shapes shape) {
		this.figure = figure;
		this.shape = shape;
	}

	public Figures getFigure() {
		return figure;
	}

	public Shapes getShape() {
		return shape;
	}	
}
