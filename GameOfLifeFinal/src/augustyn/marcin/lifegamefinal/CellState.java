package augustyn.marcin.lifegamefinal;

public enum CellState {
	DEAD(false),
	ALIVE(true);
	
	private boolean state;

	private CellState(boolean state) {
		this.state = state;
	}

	public boolean getValue() {
		return this.state;
	}
}
