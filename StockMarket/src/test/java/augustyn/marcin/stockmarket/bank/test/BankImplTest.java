package augustyn.marcin.stockmarket.bank.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import augustyn.marcin.stockmarket.bank.BankImpl;
import augustyn.marcin.stockmarket.bank.entity.PlayerFoundEntity;
import augustyn.marcin.stockmarket.bank.repository.FoundTransactionRepository;
import augustyn.marcin.stockmarket.bank.repository.PlayerFoundRepository;
import augustyn.marcin.stockmarket.bank.to.FoundTransactionTo;
import augustyn.marcin.stockmarket.calendar.MyCalendar;
import augustyn.marcin.stockmarket.enumation.Currency;
import augustyn.marcin.stockmarket.enumation.TransactionType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class BankImplTest {

	@Mock 
	private MyCalendar calendarMock;
	
	@Mock
	private PlayerFoundRepository playerFoundRepositoryMock;
	
	@Mock
	private FoundTransactionRepository foundTransactionRepositoryMock;
	
	@InjectMocks
    private BankImpl bankImpl = new BankImpl();
	
	@Captor ArgumentCaptor<PlayerFoundEntity> playerFoundEntityCaptor;
	
	@Before 
	public void prepareMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testWithdrawTransaction() {
		// given
		PlayerFoundEntity playerFoundEntity = new PlayerFoundEntity(1L, Currency.PLN, 1000);
		
		// when
		when(playerFoundRepositoryMock.findPlayerFoundByCurrency(anyString())).thenReturn(playerFoundEntity);
		when(calendarMock.getCurrentDate()).thenReturn(new DateTime()); 
		
		FoundTransactionTo foundTransaction= bankImpl.executeTransaction(TransactionType.WITHDRAW, Currency.PLN, 100);

		// then
		assertEquals(foundTransaction.getCurrency(), Currency.PLN);
		assertTrue(foundTransaction.getQuantity() == 100);
		assertEquals(foundTransaction.getType(), TransactionType.WITHDRAW);
		verify(playerFoundRepositoryMock, times(1)).save(any(PlayerFoundEntity.class));
		verify(playerFoundRepositoryMock).save(playerFoundEntityCaptor.capture());
		assertTrue(playerFoundEntityCaptor.getValue().getQuantity() == 900);
	}
	
	@Test
	public void testSouldReturnNullWhenNotEnoughFoundsInWithdrawTransaction() {
		// given
		PlayerFoundEntity playerFoundEntity = new PlayerFoundEntity(1L, Currency.PLN, 1000);
		
		// when
		when(playerFoundRepositoryMock.findPlayerFoundByCurrency(anyString())).thenReturn(playerFoundEntity);
		when(calendarMock.getCurrentDate()).thenReturn(new DateTime()); 
		
		FoundTransactionTo foundTransaction= bankImpl.executeTransaction(TransactionType.WITHDRAW, Currency.PLN, 1001);

		// then
		assertEquals(foundTransaction, null);
		verify(playerFoundRepositoryMock, never()).save(any(PlayerFoundEntity.class));
	}
	
	@Test
	public void testDepositTransaction() {
		// given
		PlayerFoundEntity playerFoundEntity = new PlayerFoundEntity(1L, Currency.PLN, 1000);
		
		// when
		when(playerFoundRepositoryMock.findPlayerFoundByCurrency(anyString())).thenReturn(playerFoundEntity);
		when(calendarMock.getCurrentDate()).thenReturn(new DateTime()); 
		
		FoundTransactionTo foundTransaction= bankImpl.executeTransaction(TransactionType.DEPOSIT, Currency.PLN, 100);

		// then
		assertEquals(foundTransaction.getCurrency(), Currency.PLN);
		assertTrue(foundTransaction.getQuantity() == 100);
		assertEquals(foundTransaction.getType(), TransactionType.DEPOSIT);
		verify(playerFoundRepositoryMock, times(1)).save(any(PlayerFoundEntity.class));
		verify(playerFoundRepositoryMock).save(playerFoundEntityCaptor.capture());
		assertTrue(playerFoundEntityCaptor.getValue().getQuantity() == 1100);
	}
	
	@Test
	public void testCheckExchangeRatePlnToEur() {
		// given

		// when
		Float exchangeRate= bankImpl.checkExchangeRate(Currency.PLN, Currency.EUR, 100);

		// then
		assertTrue(exchangeRate >= 0.23f);
		assertTrue(exchangeRate <= 0.27f);
	}
	
	@Test
	public void testCheckExchangeRateEurToPln() {
		// given

		// when
		Float exchangeRate= bankImpl.checkExchangeRate(Currency.EUR, Currency.PLN, 100);

		// then
		assertTrue(exchangeRate >= 3.9f);
		assertTrue(exchangeRate <= 4.1f);
	}
	
	@Test
	public void testShouldReturnNullWhenWrongCurrencyInCheckExchangeRate() {
		// given

		// when
		Float exchangeRate= bankImpl.checkExchangeRate(Currency.USD, Currency.USD, 100);

		// then
		assertEquals(exchangeRate, null);

	}
	
	@Test
	public void testShouldReturnNullWhenNegativeQuantityInCheckExchangeRate() {
		// given

		// when
		Float exchangeRate= bankImpl.checkExchangeRate(Currency.EUR, Currency.PLN, -100);

		// then
		assertEquals(exchangeRate, null);

	}
}
