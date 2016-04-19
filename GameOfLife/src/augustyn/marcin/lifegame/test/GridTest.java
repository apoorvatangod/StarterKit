package augustyn.marcin.lifegame.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import augustyn.marcin.lifegame.Field;
import augustyn.marcin.lifegame.Grid;

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
			assertEquals(false, fields.get(4).isCellAlive());
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
			assertEquals(true, fields.get(4).isCellAlive());
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
			assertEquals(true, fields.get(4).isCellAlive());
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
			assertEquals(false, fields.get(4).isCellAlive());
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
			assertEquals(true, fields.get(4).isCellAlive());
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
			assertEquals(false, fields.get(4).isCellAlive());
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
			assertEquals(false, fields.get(4).isCellAlive());
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
			assertEquals(outputGrid[field.getPositionY()][field.getPositionX()], field.isCellAlive());
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
			assertEquals(outputGrid[field.getPositionY()][field.getPositionX()], field.isCellAlive());
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
			assertEquals(outputGrid[field.getPositionY()][field.getPositionX()], field.isCellAlive());
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
			assertEquals(outputGrid[field.getPositionY()][field.getPositionX()], field.isCellAlive());
		}
	}
}
