package augustyn.marcin.stockmarket.bank.exception;

public class NegativeQuantityValue extends Exception {
	private static final long serialVersionUID = 5041201599078896701L;
	
	public NegativeQuantityValue() {
    }

    public NegativeQuantityValue(String message) {
        super(message);
    }

}
