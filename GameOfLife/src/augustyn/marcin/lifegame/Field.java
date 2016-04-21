package augustyn.marcin.lifegame;

/**
 * Class representing one Cell(Field)
 * 
 * int positionX - position of cell on X axis
 * int positionY - position of cell on Y axis
 * boolean cellAlive - 1 if cell is alive in current generation, 0 if cell is dead
 * int neighboursCount - number of alive neighbouring cells, updated before statuses of new generation are calculated
 * 
 * @author MARCIAUG
 *
 */
public class Field {
	private int positionX;
	private int positionY;
	private boolean cellAlive;
	private int neighboursCount;
	
	/**
	 * Constructor
	 * @param positionX - position of cell on X axis
	 * @param positionY - position of cell on Y axis
	 */
	public Field(int positionX, int positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}

	public boolean isCellAlive() {
		return cellAlive;
	}

	public void setCellAlive(boolean cellAlive) {
		this.cellAlive = cellAlive;
	}

	public int getNeighboursCount() {
		return neighboursCount;
	}

	public void setNeighboursCount(int neighboursCount) {
		this.neighboursCount = neighboursCount;
	}

	public int getPositionX() {
		return positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	@Override
	public String toString() {
		return "Field [positionX=" + positionX + ", positionY=" + positionY + ", cellAlive=" + cellAlive
				+ ", neighboursCount=" + neighboursCount + "]";
	}
}
