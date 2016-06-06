package augustyn.marcin.stockmarket.bank;

import java.util.List;

import augustyn.marcin.stockmarket.bank.to.FoundTransactionTo;
import augustyn.marcin.stockmarket.bank.to.PlayerFoundTo;
import augustyn.marcin.stockmarket.enumation.Currency;
import augustyn.marcin.stockmarket.enumation.TransactionType;

public interface Bank {
	
	public List<PlayerFoundTo> checkBalance();
	
	public FoundTransactionTo executeTransaction(TransactionType type, Currency currency, Long quantity);
	
	public Boolean confirmTransaction(FoundTransactionTo transaction);
	
	public Long checkExchangeRate(Currency inputCurrency, Currency outputCurrency, Integer quantity);
	
	public Boolean executeExchange(Currency inputCurrency, Currency outputCurrency, Integer quantity);
}
