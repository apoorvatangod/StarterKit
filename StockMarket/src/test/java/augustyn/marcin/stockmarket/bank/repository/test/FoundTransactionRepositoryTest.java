package augustyn.marcin.stockmarket.bank.repository.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import augustyn.marcin.stockmarket.bank.entity.FoundTransactionEntity;
import augustyn.marcin.stockmarket.bank.repository.FoundTransactionRepository;
import augustyn.marcin.stockmarket.enumation.Currency;
import augustyn.marcin.stockmarket.enumation.TransactionType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class FoundTransactionRepositoryTest {

	@Autowired
	private FoundTransactionRepository transactionRepository;
	
	@Test
	public void testShouldFindAllTransactions() {
		// given
		
		// when
		List<FoundTransactionEntity> transactions = transactionRepository.findAll();
		int recordsFound = transactions.size();
		// then
		assertTrue(recordsFound > 7);
	}
	
	@Test
	public void testShouldFindById() {
		// given
		
		// when
		FoundTransactionEntity transaction = transactionRepository.findTransactionById(1L);

		// then
		assertEquals(TransactionType.DEPOSIT, transaction.getType());
		assertEquals(Currency.PLN, transaction.getCurrency());
		assertEquals(1000, transaction.getQuantity());
	}
	
	
	@Test
	public void testShouldNotFindById() {
		// given
		
		// when
		FoundTransactionEntity transaction = transactionRepository.findTransactionById(null);

		// then
		assertNull(transaction);
	}
	
	@Test
	public void testShouldSaveNewEntity() {
		// given
		FoundTransactionEntity newEntity = new FoundTransactionEntity(null, TransactionType.WITHDRAW, Currency.EUR, 777, new Date());
		
		// when
		FoundTransactionEntity transaction = transactionRepository.save(newEntity);
		
		// then

		assertEquals(TransactionType.WITHDRAW, transaction.getType());
		assertEquals(Currency.EUR, transaction.getCurrency());
		assertEquals(777, transaction.getQuantity());
		
		//after
		transactionRepository.delete(transaction);
	}
}
