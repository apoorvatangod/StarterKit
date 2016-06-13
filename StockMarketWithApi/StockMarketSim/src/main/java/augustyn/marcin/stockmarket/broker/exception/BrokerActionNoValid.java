package augustyn.marcin.stockmarket.broker.exception;

public class BrokerActionNoValid extends Exception {
	private static final long serialVersionUID = 5041205599078656701L;
	
	public BrokerActionNoValid() {
    }

    public BrokerActionNoValid(String message) {
        super(message);
    }

}
