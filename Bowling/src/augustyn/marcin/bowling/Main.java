package augustyn.marcin.bowling;

public class Main {

	public static void main(String[] args) {
		ScoreCalculator player1 = new ScoreCalculator(1);
		player1.roll(10);
		System.out.println(player1.score());
		player1.roll(9);
		player1.roll(1);
		System.out.println(player1.score());
		player1.roll(5);
		player1.roll(5);
		System.out.println(player1.score());
		player1.roll(7);
		player1.roll(2);
		System.out.println(player1.score());
		player1.roll(10);
		System.out.println(player1.score());
		player1.roll(10);;
		System.out.println(player1.score());
		player1.roll(10);
		System.out.println(player1.score());
		player1.roll(9);
		player1.roll(0);
		System.out.println(player1.score());
		player1.roll(8);
		player1.roll(2);
		System.out.println(player1.score());
		player1.roll(9);
		player1.roll(1);
		System.out.println(player1.score());
		player1.roll(10);
		System.out.println(player1.score());
	}

}
