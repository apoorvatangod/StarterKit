package augustyn.marcin.stockmarket.broker.exception;

public class InsufficientShareBalance extends Exception {
	private static final long serialVersionUID = 5041205599078856701L;
	
	public InsufficientShareBalance() {
    }

    public InsufficientShareBalance(String message) {
        super(message);
    }

}
