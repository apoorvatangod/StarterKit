package augustyn.marcin.lifegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * Class for visualisation of game - shows grid of cells. Green cell is alive, gray is dead.
 * Left mouse button click skips to next generation. Hold left mouse button for auto simulation of next generations.
 * @author MARCIAUG
 *
 */
public class GameOfLife extends JPanel{
	private static final long serialVersionUID = 6463599313965871632L;
	private static final int SPEED_MIN = 0;
	private static final int SPEED_MAX = 100;
	private static final int SPEED_DEFAULT = 10;
	private static final int FRAME_WIDTH = 1200;
	private static final int FRAME_HEIGHT = 1000;
	private static final int SIDE_PANEL_WIDTH = 200;
	private static boolean autoUpdate = false;
	private static int animationSpeed = 100;
	private static Grid grid = new Grid(50,50);
		
	/*private static boolean[][] inputGrid = {
			{true, false, false, false, false},
			{true, false, true, true, false},
			{true, false, true, false, false},
			{false, true, false, false, false},
			{true, false, false, false, true}
		};*/
	
	/*{false, false, false, false, false},
			{false, false, false, true, false},
			{false, false, true, false, false},
			{false, true, false, false, false},
			{false, false, false, false, false}*/
	//private static Grid grid = new Grid(inputGrid);

	
	public GameOfLife() {

	}
	/**
	 * Prints grid with cells and cells' status
	 */
	@Override
	public void paint(Graphics g) {
		List<Field> fields = grid.getGrid();
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.GREEN);
		
		int frameWidth = getWidth() - SIDE_PANEL_WIDTH;
		int frameHeight = getHeight();
		int cellWidth = frameWidth / grid.getGridColumns();
		int cellHeight = frameHeight / grid.getGridRows();
		for(Field field : fields){
			int x = field.getPositionX() * cellWidth;
			int y = field.getPositionY() * cellHeight;
			if(field.isCellAlive()){
				g.setColor(Color.GREEN);
				g2d.fillRect(x, y, cellWidth, cellHeight);
			}
			else{
				g.setColor(Color.BLACK);
				g2d.drawRect(x, y, cellWidth, cellHeight);
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Game Of Life");
		JSlider slider = new JSlider(JSlider.VERTICAL, SPEED_MIN, SPEED_MAX, SPEED_DEFAULT);
		Hashtable<Integer, JLabel> sliderLabels = new Hashtable<>();
		JLabel sliderTitle = new JLabel("Animation speed");
		
		sliderTitle.setBounds(1035, 175, 100, 20);
		sliderTitle.setAlignmentX(CENTER_ALIGNMENT);
		
		sliderLabels.put( new Integer( 0 ), new JLabel("Fast") );
		sliderLabels.put( new Integer( SPEED_MAX/2 ), new JLabel("Medium") );
		sliderLabels.put( new Integer( SPEED_MAX ), new JLabel("Slow") );
		
		slider.setBounds(1050, 200, 100, 600);
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(1);
		slider.setPaintLabels(true);
		slider.setLabelTable(sliderLabels);
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int newSpeed = ((JSlider)e.getSource()).getValue();
				setAnimationSpeed(newSpeed * 10);
			}
        });
		
		JButton button = new JButton("Auto");
		button.setBounds(1025, 850, 100, 50);
		button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				GameOfLife.setAutoUpdate(!GameOfLife.isAutoUpdate());
				
			}
		});
		
		frame.add(slider);
		frame.add(sliderTitle);
		frame.add(button);
		frame.add(new GameOfLife());
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setVisible(true);
		frame.add(new MouseEvents(grid, frame));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while(true){
			if(autoUpdate){
				grid.nextGeneration();
				frame.repaint();
				Thread.sleep(animationSpeed);
			}
		}
	}

	public static boolean isAutoUpdate() {
		return autoUpdate;
	}

	public static void setAutoUpdate(boolean autoUpdate) {
		GameOfLife.autoUpdate = autoUpdate;
	}
	public static void setAnimationSpeed(int animationSpeed) {
		GameOfLife.animationSpeed = animationSpeed;
	}
}
/**
 * Class for handling mouse events.
 * @author MARCIAUG
 *
 */
class MouseEvents extends JPanel implements MouseListener {
	private static final long serialVersionUID = -736110162875010822L;
	private Grid grid;
	private JFrame panel;
	
	/**
	 * Constructor
	 * @param grid - grid of cells, which is to be updated on mouse click
	 * @param panel - which panel is to be repainted after grid update
	 */
	public MouseEvents(Grid grid, JFrame panel) {
		this.grid = grid;
		this.panel = panel;
		addMouseListener(this);
	}


	public void mouseClicked(MouseEvent me) {	
	}
	
	public void mouseEntered(MouseEvent me) {
	}
	
	public void mouseExited(MouseEvent me) {
	}

	public void mousePressed(MouseEvent me) {
		/*try {// When mouse button if pressed and hold for more than 150ms, auto animation starts.
			Thread.sleep(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		GameOfLife.setAutoUpdate(true);*/
	}
	
	/**
	 * Single click skips to next generation. Button release stops auto animation.
	 */
	public void mouseReleased(MouseEvent me) {
		GameOfLife.setAutoUpdate(false);
		grid.nextGeneration();
		panel.repaint();
	}
	
	public void mouseDragged(MouseEvent me) {
	}
	
	public void mouseMoved(MouseEvent me) {
	}

}