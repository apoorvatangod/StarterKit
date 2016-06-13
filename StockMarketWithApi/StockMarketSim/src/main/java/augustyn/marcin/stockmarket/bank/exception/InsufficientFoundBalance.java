package augustyn.marcin.stockmarket.bank.exception;

public class InsufficientFoundBalance extends Exception {
	private static final long serialVersionUID = 5041205599078896701L;
	
	public InsufficientFoundBalance() {
    }

    public InsufficientFoundBalance(String message) {
        super(message);
    }

}
