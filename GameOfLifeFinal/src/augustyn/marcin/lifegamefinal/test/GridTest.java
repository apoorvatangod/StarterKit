package augustyn.marcin.lifegamefinal.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import augustyn.marcin.lifegamefinal.CellState;
import augustyn.marcin.lifegamefinal.Field;
import augustyn.marcin.lifegamefinal.Grid;

public class GridTest {
	Grid grid;
	
	@Test
	public void shouldKillCellWhenLessThanTwoNeighbours(){
		//given
		boolean[][] inputGrid = {
				{false, false, false},
				{false, true, true},
				{false, false, false}
			};

		grid = new Grid(inputGrid);
		//when
		grid.nextGeneration();
		List<Field> fields = grid.getGrid();
		//then 
			assertEquals(CellState.DEAD.getValue(), fields.get(4).getCellState());
	}
	@Test
	public void shouldCellBeAliveWhenTwoNeighbours(){
		//given
		boolean[][] inputGrid = {
				{false, false, false},
				{false, true, true},
				{false, true, false}
			};

		grid = new Grid(inputGrid);
		//when
		grid.nextGeneration();
		List<Field> fields = grid.getGrid();
		//then 
		assertEquals(CellState.ALIVE.getValue(), fields.get(4).getCellState());
	}	
	@Test
	public void shouldCellBeAliveWhenThreeNeighbours(){
		//given
		boolean[][] inputGrid = {
				{false, true, false},
				{false, true, true},
				{false, true, false}
			};

		grid = new Grid(inputGrid);
		//when
		grid.nextGeneration();
		List<Field> fields = grid.getGrid();
		//then 
		assertEquals(CellState.ALIVE.getValue(), fields.get(4).getCellState());
	}
	@Test
	public void shouldKillCellWhenMoreThanThreeNeighbours(){
		//given
		boolean[][] inputGrid = {
				{true, true, false},
				{false, true, true},
				{false, true, false}
			};

		grid = new Grid(inputGrid);
		//when
		grid.nextGeneration();
		List<Field> fields = grid.getGrid();
		//then 
			assertEquals(CellState.DEAD.getValue(), fields.get(4).getCellState());
	}
	@Test
	public void shouldResurectCellWhenThreeNeighbours(){
		//given
		boolean[][] inputGrid = {
				{false, true, false},
				{false, false, true},
				{false, true, false}
			};

		grid = new Grid(inputGrid);
		//when
		grid.nextGeneration();
		List<Field> fields = grid.getGrid();
		//then 
			assertEquals(CellState.ALIVE.getValue(), fields.get(4).getCellState());
	}
	@Test
	public void shouldCellStayDeadWhenLessThanTwoNeighbours(){
		//given
		boolean[][] inputGrid = {
				{false, false, false},
				{false, false, true},
				{false, true, false}
			};

		grid = new Grid(inputGrid);
		//when
		grid.nextGeneration();
		List<Field> fields = grid.getGrid();
		//then 
			assertEquals(CellState.DEAD.getValue(), fields.get(4).getCellState());
	}
	@Test
	public void shouldCellStayDeadWhenMareThanThreeNeighbours(){
		//given
		boolean[][] inputGrid = {
				{true, true, false},
				{false, false, true},
				{false, true, false}
			};

		grid = new Grid(inputGrid);
		//when
		grid.nextGeneration();
		List<Field> fields = grid.getGrid();
		//then 
			assertEquals(CellState.DEAD.getValue(), fields.get(4).getCellState());
	}
	@Test
	public void shouldKillBothCellsCuzLessThenTwoNeighbours(){
		//given
		boolean[][] inputGrid = {
				{false, false, false},
				{false, true, true},
				{false, false, false}
			};
		boolean[][] outputGrid = {
				{false, false, false},
				{false, false, false},
				{false, false, false}
			};

		grid = new Grid(inputGrid);
		//when
		grid.nextGeneration();
		List<Field> fields = grid.getGrid();
		//then 
		for(Field field : fields){
			assertEquals(outputGrid[field.getPosition().y][field.getPosition().x], field.getCellState());
		}
	}
	
	@Test
	public void shouldNotChangeCuzTwoNeighboursPlusResurectOneCell(){
		//given
		boolean[][] inputGrid = {
				{false, false, false},
				{false, true, true},
				{false, false, true}
			};
		boolean[][] outputGrid = {
				{false, false, false},
				{false, true, true},
				{false, true, true}
			};

		grid = new Grid(inputGrid);
		//when
		grid.nextGeneration();
		List<Field> fields = grid.getGrid();
		//then 
		for(Field field : fields){
			assertEquals(outputGrid[field.getPosition().y][field.getPosition().x], field.getCellState());
		}
	}
	
	@Test
	public void shouldKillTwoCellCuzMoreThenThreeNeighbours(){
		//given
		boolean[][] inputGrid = {
				{false, false, true},
				{false, true, true},
				{false, true, true}
			};
		boolean[][] outputGrid = {
				{false, true, true},
				{false, false, false},
				{false, true, true}
			};

		grid = new Grid(inputGrid);
		//when
		grid.nextGeneration();
		List<Field> fields = grid.getGrid();
		//then 
		for(Field field : fields){
			assertEquals(outputGrid[field.getPosition().y][field.getPosition().x], field.getCellState());
			//System.out.println(field.getPositionX() + " " + field.getPositionY());
		}
	}
	@Test
	public void shouldReturnIsAliveTrueOnlyForMiddleCell(){
		//given
		boolean[][] inputGrid = {
				{false, false, false, false, false},
				{false, false, false, true, false},
				{false, false, true, false, false},
				{false, true, false, false, false},
				{false, false, false, false, false}
			};
		boolean[][] outputGrid = {
				{false, false, false, false, false},
				{false, false, false, false, false},
				{false, false, true, false, false},
				{false, false, false, false, false},
				{false, false, false, false, false}
			};

		grid = new Grid(inputGrid);
		//when
		grid.nextGeneration();
		List<Field> fields = grid.getGrid();
		//then 
		for(Field field : fields){
			assertEquals(outputGrid[field.getPosition().y][field.getPosition().x], field.getCellState());
		}
	}
}
