package augustyn.marcin.poker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main class, contains compare function for two card sets ("hands").
 * @author MARCIAUG
 *
 */
public class Main extends JPanel{
	private static final long serialVersionUID = -196365701940460357L;
	private static final Logger logger = LogManager.getLogger(Main.class);
	private static int handLevel1;
	private static long setPower1;
	private static int handLevel2;
	private static long setPower2;
	private static HandCalculator hand1 = new HandCalculator(1);
	private static HandCalculator hand2 = new HandCalculator(2);
	private BufferedImage imageC;
	private BufferedImage imageS;
	private BufferedImage imageD;
	private BufferedImage imageH;
	private Graphics2D g2d;
	
	/**
	 * Draws cards of both players.
	 */
	@Override
	public void paint(Graphics g) {
		int x,y;
		g2d = (Graphics2D) g;
		Font font = new Font("New font", Font.BOLD, 30);
		g.setFont(font);
		g.setColor(Color.BLACK);
		for(int i = 0; i < 10; i++){
			y = 50 + 200 * (int) Math.round(i/5);
			if (i < 5){
				x = 50 + i * 150;
				g2d.drawString(hand1.getHand().get(i).getFigureAsString(), x + 20, y + 25);
				g2d.drawImage(getImageByCard(hand2.getHand().get(i)), x + 25, y + 40, 50, 50, null);
			}
			else{
				x = 50 + (i - 5) * 150;
				g2d.drawString(hand2.getHand().get(i - 5).getFigureAsString(), x + 20, y + 25);
				g2d.drawImage(getImageByCard(hand2.getHand().get(i - 5)), x + 25, y + 40, 50, 50, null);
			}
			g2d.drawRect(x, y, 100, 100);
		}
	}
	/**
	 * Gets image of card shape to be assigned to a card.
	 * @param card input card to which assign shape image
	 * @return BufferedImage of card shape
	 */
	private BufferedImage getImageByCard(Card card){
		if (card.getColor() == 1){
			return imageS;
		}
		if (card.getColor() == 2){
			return imageH;
		}
		if (card.getColor() == 3){
			return imageD;
		}
		return imageC;	
	}
	/**
	 * Default constructor. Loads images of card shapes (eg. club, heart,...)
	 * @throws FileNotFoundException when at least one of input images of card shapes cannot be read.
	 */
	public Main() throws FileNotFoundException{
		try {                
	          imageC = ImageIO.read(new File("src/images/C.png"));
	          imageD = ImageIO.read(new File("src/images/D.png"));
	          imageH = ImageIO.read(new File("src/images/H.png"));
	          imageS = ImageIO.read(new File("src/images/S.png"));
	       } catch (IOException ex) {
	            throw new FileNotFoundException("Image files for card colors not found.");
	       }
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		generateRandomHands();
		
		hand1.calculateSetLevel();
		handLevel1 = hand1.getHandLevel();
		//logger.info(handLevel1);
		hand1.calculateSetPower();
		setPower1 = hand1.getHandPower();
		//logger.info(setPower1);
		
		hand2.calculateSetLevel();
		handLevel2 = hand2.getHandLevel();
		//logger.info(handLevel2);
		hand2.calculateSetPower();
		setPower2 = hand2.getHandPower();
		//logger.info(setPower2);
		
		int winner = compareHands(handLevel1, setPower1, handLevel2, setPower2);
		String winnerText = new String();
		if (winner == 0){
			winnerText = "Draw!";
		}
		if (winner == 1){
			winnerText = "Player 1 wins.";
		}
		if (winner == 2){
			winnerText = "Player 2 wins.";
		}
		JFrame frame = new JFrame("Poker");
		JLabel winnerLabel = new JLabel(winnerText);
		Font font = new Font("Winner font", Font.BOLD, 50);
		JButton button = new JButton("New hands");
		
		winnerLabel.setBounds(200, 400, 600, 60);
		winnerLabel.setFont(font);
		
		button.setBounds(350, 175, 100, 40);
		button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				hand1.getHand().clear();
				hand2.getHand().clear();
				generateRandomHands();
				frame.repaint();
			}
		});
		
		frame.add(winnerLabel);
		frame.add(button);
		frame.add(new Main());
		frame.setSize(900, 550);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	/**
	 * Method compares two card sets(hands, players).
	 * @param handLevel1 - level (hand rank) of first set
	 * @param setPower1 - power of first set (used to settle draws when both players have same hand rank)
	 * @param handLevel2- level (hand rank) of second set
	 * @param setPower2 -  power of second set (used to settle draws when both players have same hand rank)
	 * @return int 0-draw, 1-first hand wins, 2-second hand wins
	 */
	public static int compareHands(int handLevel1, long setPower1, int handLevel2, long setPower2){
		if(handLevel1 < 0 || handLevel2 < 0 || setPower1 < 0L || setPower2 < 0L){
			throw new UnsupportedOperationException("Players must have 5 cards to compare.");
		}
		if(handLevel1 > handLevel2){
			logger.info("Hand 1 wins.");
			return 1;
		}
		else if(handLevel1 < handLevel2){
			logger.info("Hand 2 wins.");
			return 2;
		}
		else{
			if(setPower1 > setPower2){
				logger.info("Hand 1 wins.");
				return 1;
			}
			else if(setPower1 < setPower2){
				logger.info("Hand 2 wins.");
				return 2;
			}
			else{
				logger.info("Draw!");
				return 0;
			}
		}
	}
	/**
	 * Generates random set of cards for both players.
	 */
	public static void generateRandomHands(){
		for (int i = 0; i < 10; i++){
			Card card;
			int color = 0;
			int figure = 0;
			Random random = new Random();
			do{
				color = random.nextInt(4) + 1;
				figure = random.nextInt(13) + 2;
				card = new Card(figure, color);
			}while(!checkIfUniqueCard(card));
			
			if(i < 5){
				hand1.pickCard(figure, String.valueOf(color));
			}
			else{
				hand2.pickCard(figure, String.valueOf(color));
			}
		}
	}
	/**
	 * Check if card has been already picked by either of players
	 * @param card input card to be checked if is unique
	 * @return true if card is unique, false if card has already been picked
	 */
	public static boolean checkIfUniqueCard(Card card){
		for(int i = 0; i < hand1.getHand().size(); i++){
			if(hand1.getHand().get(i).getColor() == card.getColor() && 
					hand1.getHand().get(i).getFigure() == card.getFigure()){
				logger.info("LOL1");
				return false;
			}
		}
		for(int i = 0; i < hand2.getHand().size(); i++){
			if(hand2.getHand().get(i).getColor() == card.getColor() && 
					hand2.getHand().get(i).getFigure() == card.getFigure()){
				logger.info("LOL2");
				return false;
			}
		}
		return true;
	}
}
