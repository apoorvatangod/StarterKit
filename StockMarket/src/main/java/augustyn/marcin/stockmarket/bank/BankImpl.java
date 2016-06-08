package augustyn.marcin.stockmarket.bank;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import augustyn.marcin.stockmarket.bank.entity.PlayerFoundEntity;
import augustyn.marcin.stockmarket.bank.exception.InsufficientFoundBalance;
import augustyn.marcin.stockmarket.bank.exception.NegativeQuantityValue;
import augustyn.marcin.stockmarket.bank.mapper.FoundTransactionMapper;
import augustyn.marcin.stockmarket.bank.mapper.PlayerFoundMapper;
import augustyn.marcin.stockmarket.bank.repository.FoundTransactionRepository;
import augustyn.marcin.stockmarket.bank.repository.PlayerFoundRepository;
import augustyn.marcin.stockmarket.bank.to.FoundTransactionTo;
import augustyn.marcin.stockmarket.bank.to.PlayerFoundTo;
import augustyn.marcin.stockmarket.calendar.Calendar;
import augustyn.marcin.stockmarket.enumation.Currency;
import augustyn.marcin.stockmarket.enumation.TransactionType;
import augustyn.marcin.stockmarket.main.Randomizer;

@Service
public class BankImpl implements Bank {
	private static final Logger logger = LogManager.getLogger(BankImpl.class);
	private static final float MINIMAL_EUR_TO_PLN_EXCHANGE_RATE = 3.9f;
	private static final float MAXIMAL_EUR_TO_PLN_EXCHANGE_RATE = 4.1f;
	private static final float MINIMAL_PLN_TO_EUR_EXCHANGE_RATE = 0.23f;
	private static final float MAXIMAL_PLN_TO_EUR_EXCHANGE_RATE = 0.27f;
	@SuppressWarnings("unused")
	private static final float EXCHANGE_COMISSION_PERCENTAGE = 0.02f;
	
	
	@Autowired
	private PlayerFoundRepository playerFoundRepository;
	
	@Autowired
	private FoundTransactionRepository foundTransactionRepository;
	
	@Autowired
	private Calendar calendar;

	@Override
	public List<PlayerFoundTo> checkBalance() {
		return PlayerFoundMapper.map2To(playerFoundRepository.findAll());
	}

	@Override
	public FoundTransactionTo executeTransaction(TransactionType type, Currency currency, Integer quantity) {
		if(type == TransactionType.WITHDRAW){
			try {
				checkIfEnoughFoundsForWithdraw(currency, quantity);
			} catch (InsufficientFoundBalance e) {
				logger.info(e.getMessage());
				return null;
			}
		}
		PlayerFoundEntity playerFoundEntity = playerFoundRepository.findPlayerFoundByCurrency(currency.toString()).get(0);
		
		if(type == TransactionType.WITHDRAW){
			playerFoundEntity.setQuantity(playerFoundEntity.getQuantity() - quantity);
		}
		if(type == TransactionType.DEPOSIT){
			playerFoundEntity.setQuantity(playerFoundEntity.getQuantity() + quantity);
		}
		playerFoundRepository.save(playerFoundEntity);
		FoundTransactionTo transactionTo = new FoundTransactionTo(null, type, currency, quantity, calendar.getCurrentDate());
		
		foundTransactionRepository.save(FoundTransactionMapper.map(transactionTo));
		
		return transactionTo;
	}

	@Override
	public boolean confirmTransaction(FoundTransactionTo transaction) {
		return transaction == null ? false : FoundTransactionMapper.map(transaction).equals(foundTransactionRepository.findOne(transaction.getId()));
	}

	@Override
	public Float checkExchangeRate(Currency inputCurrency, Currency outputCurrency, Integer quantity) {
		try {
			validateQuantityValue(quantity);
		} catch (NegativeQuantityValue e) {
			logger.info(e.getMessage());
			return null;
		}
		if(inputCurrency == Currency.EUR && outputCurrency == Currency.PLN){
			return Randomizer.randomFloat(MINIMAL_EUR_TO_PLN_EXCHANGE_RATE, MAXIMAL_EUR_TO_PLN_EXCHANGE_RATE);
		}
		if(inputCurrency == Currency.PLN && outputCurrency == Currency.EUR){
			return Randomizer.randomFloat(MINIMAL_PLN_TO_EUR_EXCHANGE_RATE, MAXIMAL_PLN_TO_EUR_EXCHANGE_RATE);
		}
		return null;
	}

	@Override
	public boolean executeExchange(Currency inputCurrency, Currency outputCurrency, Integer quantity) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private void checkIfEnoughFoundsForWithdraw(Currency currency, int quantity) throws InsufficientFoundBalance{
		int foundsQuantityOnAccount = playerFoundRepository.findPlayerFoundByCurrency(currency.toString()).get(0).getQuantity();
		
		if(foundsQuantityOnAccount < quantity){
			throw new InsufficientFoundBalance("Insufficient founds on player account to perform withdraw.");
		}
	}
	private void validateQuantityValue(Integer quantity) throws NegativeQuantityValue{
		if(quantity <= 0){
			throw new NegativeQuantityValue("Quantity must be greater than 0.");
		}
	}
}