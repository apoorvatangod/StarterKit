package augustyn.marcin.bowlingfinal;

public enum Bonus {
	SPARE(1),
	STRIKE(2);
	
	private int bonus;

	private Bonus(int bonus) {
		this.bonus = bonus;
	}
	public int getValue(){
		return this.bonus;
	}
}
