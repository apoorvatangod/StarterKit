package augustyn.marcin.lifegame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * int gridRows - number of rows in grid 
 * int gridColumns - number of columns in grid
 * int generation - generation number, starts from 0
 * List<Field> grid - represents grid of cells
 * @author MARCIAUG
 *
 */
public class Grid {
	@SuppressWarnings("unused")
	private static final Logger logger = LogManager.getLogger(Grid.class);
	private int gridRows;
	private int gridColumns;
	private int generation;
	private List<Field> grid = new ArrayList<>();
	/**
	 * Constructor for given grid size, populates grid with cells of random (dead or alive) state
	 * @param gridRows - number of rows in grid 
	 * @param gridColumns - number of columns in grid
	 */
	public Grid(int gridRows, int gridColumns) {
		this.gridRows = gridRows;
		this.gridColumns = gridColumns;
		generation = 0;
		generateGrid();
		populateRandom();
	}
	/**
	 * Constructor for given (dead or alive) state matrix
	 * @param inputGrid - matrix of cells states
	 */
	public Grid(boolean[][] inputGrid) {
		this.gridRows = inputGrid.length;
		this.gridColumns = inputGrid[0].length;
		generation = 0;
		
		for (int row = 0; row < inputGrid.length; row++){
			for (int col = 0; col < inputGrid[row].length; col++){
				Field field = new Field(col, row);
				field.setCellAlive(inputGrid[row][col]);
				grid.add(field);
			}
		}
	}
	private void generateGrid(){
		for(int i = 0; i < gridRows; i++){
			for(int j = 0; j < gridColumns; j++){
				Field field = new Field(j, i);
				grid.add(field);
			}
		}
	}
	private void populateRandom(){
		Random random = new Random();
		for(Field field : grid){
			field.setCellAlive(random.nextBoolean());
		}
	}
	private void updateNeighbousForCell(Field field){//TODO tylko raz zrobic bo sie nie zmienia
		int x = field.getPositionX();
		int y = field.getPositionY();
		int neighboursCount = 0;
		for (Field neighbour : grid){
			if(!field.equals(neighbour)){
				if(Math.abs(neighbour.getPositionX() - x) <= 1 && Math.abs(neighbour.getPositionY() - y) <= 1){
					neighboursCount = neighbour.isCellAlive() ? neighboursCount + 1 : neighboursCount;
				}
			}
		}
		field.setNeighboursCount(neighboursCount);
		//logger.info("Cell " + x + ", " + y + " has " + neighboursCount + " neighbours.");
	}
	private void updateAllNeighbours(){
		for (Field neighbour : grid){
			updateNeighbousForCell(neighbour);
		}
	}
	private void updateStateForCell(Field field){
		int neighbours = field.getNeighboursCount();
		if(field.isCellAlive()){
			if(neighbours < 2){
				field.setCellAlive(false);
			}
			if(neighbours > 3){
				field.setCellAlive(false);
			}
		}
		else{
			if(neighbours == 3){
				field.setCellAlive(true);
			}
		}
		//logger.info("Cell " + field.getPositionX() + ", " + field.getPositionY() + " has " + field.getNeighboursCount() + " neighbours and is alive=" + field.isCellAlive());
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
	
	public int getGridRows() {
		return gridRows;
	}
	public int getGridColumns() {
		return gridColumns;
	}
	
}
