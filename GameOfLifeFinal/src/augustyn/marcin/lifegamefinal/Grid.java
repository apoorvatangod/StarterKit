package augustyn.marcin.lifegamefinal;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * int generation - generation number, starts from 0
 * List<Field> grid - represents grid of cells
 * @author MARCIAUG
 *
 */
public class Grid {
	@SuppressWarnings("unused")
	private static final Logger logger = LogManager.getLogger(Grid.class);
	private int generation;
	private List<Field> grid = new ArrayList<>();
	/**
	 * Constructor for given grid size, populates grid with cells of random (dead or alive) state
	 * @param gridSize - number of rows and columns in grid 
	 */
	public Grid(Point gridSize) {
		generation = 0;
		generateGrid(gridSize.x, gridSize.y);
		populateRandom();
		getAllNeighbours();
	}
	/**
	 * Constructor for given (dead or alive) state matrix
	 * @param inputGrid - matrix of cells states
	 */
	public Grid(boolean[][] inputGrid) {
		generation = 0;
		
		for (int row = 0; row < inputGrid.length; row++){
			for (int col = 0; col < inputGrid[row].length; col++){
				Field field = new Field(new Point(col, row));
				field.setCellState(inputGrid[row][col]);
				grid.add(field);
			}
		}
		getAllNeighbours();
	}
	private void generateGrid(int gridRows, int gridColumns){
		for(int i = 0; i < gridRows; i++){
			for(int j = 0; j < gridColumns; j++){
				Field field = new Field(new Point(j, i));
				grid.add(field);
			}
		}
	}
	private void populateRandom(){
		Random random = new Random();
		for(Field field : grid){
			field.setCellState(random.nextBoolean());
		}
	}
	private void getNeighboursForCell(Field field){
		Point pos = field.getPosition();
		
		for (Field neighbour : grid){
			if(!field.equals(neighbour) && Math.abs(neighbour.getPosition().x - pos.x) <= 1 && 
					Math.abs(neighbour.getPosition().y - pos.y) <= 1){
				Field newNeighbourField = neighbour;
				field.getNeighbours().add(newNeighbourField);
				//logger.info("Cell " + x + ", " + y + " neighbour: " + neighbour.getPosition().x + " " + neighbour.getPosition().y);
			}
		}	
	}
	private void getAllNeighbours(){
		for (Field neighbour : grid){
			getNeighboursForCell(neighbour);
		}
	}
	private void updateNeighboursForCell(Field field){
		int aliveNeighboursCount = 0;
		for(Field neighbour : field.getNeighbours()){
			aliveNeighboursCount = CellState.ALIVE.getValue() == neighbour.getCellState() ? 
					aliveNeighboursCount + 1 : aliveNeighboursCount;
		}
		field.setAliveNeighboursCount(aliveNeighboursCount);
	}
	
	private void updateAllNeighbours(){
		for (Field neighbour : grid){
			updateNeighboursForCell(neighbour);
		}
	}
	private void updateStateForCell(Field field){
		int aliveNeighboursCount = field.getAliveNeighboursCount();

		if(aliveNeighboursCount < 2 || aliveNeighboursCount > 3){
			field.setCellState(CellState.DEAD.getValue());
		}
		if(aliveNeighboursCount == 3){
			field.setCellState(CellState.ALIVE.getValue());
		}
		//logger.info("Cell " + field.getPosition().x + ", " + field.getPosition().y + " has " + aliveNeighboursCount + " alive neighbours and is alive=" + field.getCellState());
	}
	
	private void updateAllStates(){
		for (Field cell : grid){
			updateStateForCell(cell);
		}
	}
	/**
	 * Calculates and updates statuses for cell in new generation.
	 */
	public void nextGeneration(){
		updateAllNeighbours();
		updateAllStates();
		generation++;
	}
	public int getGeneration() {
		return generation;
	}
	public List<Field> getGrid() {
		return grid;
	}	
}
