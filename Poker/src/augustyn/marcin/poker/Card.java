package augustyn.marcin.poker;
/**
 * Class that holds information about a single card.
 * @author MARCIAUG
 *
 */
public class Card {
	private int figure;
	private int color;
	/**
	 * public constructor
	 * @param figure - figure of the card - starts with 2,3,... and ends with 12(represents Queen),13(King),14(Ace)
	 * @param color - color of the card - 1=Spades, 2=Hearts, 3=Diamonds, 4=Clubs
	 */
	public Card(int figure, int color) {
		this.figure = figure;
		this.color = color;
	}

	public int getFigure() {
		return figure;
	}

	public int getColor() {
		return color;
	}

	@Override
	public String toString() {
		String colour;
		switch(color){
		case 1://spades
			colour = "S";
			break;
		case 2://hearts
			colour = "H";
			break;
		case 3://diamonds
			colour = "D";
			break;
		case 4://clubs
			colour = "C";
			break;
		default:
			colour ="NN";
		}
		return figure + colour + " ";
	}
	public String getFigureAsString(){
		if(figure < 11){
			return String.valueOf(figure);
		}
		if (figure == 11){
			return "J";
		}
		if (figure == 12){
			return "Q";
		}
		if (figure == 13){
			return "K";
		}
		return "A";
	}
}
