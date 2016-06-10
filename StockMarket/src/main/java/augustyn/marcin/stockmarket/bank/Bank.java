package augustyn.marcin.stockmarket.bank;

import java.util.List;

import augustyn.marcin.stockmarket.bank.exception.InsufficientFoundBalance;
import augustyn.marcin.stockmarket.bank.to.FoundTransactionTo;
import augustyn.marcin.stockmarket.bank.to.PlayerFoundTo;
import augustyn.marcin.stockmarket.enumation.Currency;
import augustyn.marcin.stockmarket.enumation.TransactionType;

public interface Bank {
	
	public List<PlayerFoundTo> checkBalance();
	
	public FoundTransactionTo executeTransaction(TransactionType type, Currency currency, Integer quantity);
	
	public boolean confirmTransaction(FoundTransactionTo transaction);
	
	public Float checkExchangeRate(Currency inputCurrency, Currency outputCurrency, Integer quantity);
	
	public void executeExchange(Currency inputCurrency, Currency outputCurrency, Integer quantity) throws InsufficientFoundBalance;
}
