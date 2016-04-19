package augustyn.marcin.lifegamefinal;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

/**
 * Class representing one Cell(Field)
 * 
 * Point position - position of cell on X and Y axis
 * CellState cellAlive - ALIVE if cell is alive in current generation, DEAD if cell is dead
 * int aliveNeighboursCount - number of alive neighbouring cells, updated before statuses of new generation are calculated
 * List<Field> neighbours - list of neighbour cells
 * 
 * @author MARCIAUG
 *
 */
public class Field {
	private Point position;
	private boolean cellState;
	private int aliveNeighboursCount;
	private List<Field> neighbours = new LinkedList<>();
	
	/**
	 * Constructor
	 * @param position - position of cell on X and Y axis
	 */
	public Field(Point position) {
		this.position = position;
	}

	public boolean getCellState() {
		return cellState;
	}

	public void setCellState(boolean cellState) {
		this.cellState = cellState;
	}

	public int getAliveNeighboursCount() {
		return aliveNeighboursCount;
	}

	public void setAliveNeighboursCount(int aliveNeighboursCount) {
		this.aliveNeighboursCount = aliveNeighboursCount;
	}

	public Point getPosition() {
		return position;
	}

	public List<Field> getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(List<Field> neighbours) {
		this.neighbours = neighbours;
	}

	@Override
	public String toString() {
		return "Field [positionX=" + position.x + ", positionY=" + position.y + ", cellAlive=" + cellState
				+ ", neighboursCount=" + aliveNeighboursCount + "]";
	}
}
