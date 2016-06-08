package augustyn.marcin.stockmarket.broker.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;

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

import augustyn.marcin.stockmarket.bank.Bank;
import augustyn.marcin.stockmarket.bank.to.FoundTransactionTo;
import augustyn.marcin.stockmarket.broker.BrokerImpl;
import augustyn.marcin.stockmarket.broker.entity.OfferEntity;
import augustyn.marcin.stockmarket.broker.entity.PlayerShareEntity;
import augustyn.marcin.stockmarket.broker.mapper.PlayerShareMapper;
import augustyn.marcin.stockmarket.broker.repository.OfferRepository;
import augustyn.marcin.stockmarket.broker.repository.PlayerShareRepository;
import augustyn.marcin.stockmarket.broker.to.OfferTo;
import augustyn.marcin.stockmarket.broker.to.PlayerShareTo;
import augustyn.marcin.stockmarket.calendar.Calendar;
import augustyn.marcin.stockmarket.enumation.ActionType;
import augustyn.marcin.stockmarket.enumation.Currency;
import augustyn.marcin.stockmarket.enumation.OfferStatus;
import augustyn.marcin.stockmarket.stock.Stock;
import augustyn.marcin.stockmarket.stock.to.ShareDataTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class BrokerImplTest {

	@Mock 
	private Stock stockMock;
	
	@Mock 
	private Calendar calendarMock;
	
	@Mock 
	private Bank bankMock;
	
	@Mock
	private OfferRepository offerRepositoryMock;
	
	@Mock
	private PlayerShareRepository playerShareRepositoryMock;
	
	@InjectMocks
    private BrokerImpl brokerImpl = new BrokerImpl();
	
	@Captor ArgumentCaptor<Integer> integerCaptor;
	
	@Captor ArgumentCaptor<PlayerShareEntity> playerShareEntityCaptor;
	
	@Before 
	public void prepareMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetSellOffer() {
		// given
		ShareDataTo shareDataTo = new ShareDataTo(1L, "PKO", 1000, new Date());
		
		// when
		when(stockMock.getCurrentDataForShare(anyString())).thenReturn(shareDataTo);
		when(calendarMock.getCurrentDate()).thenReturn(new Date()); 
		when(offerRepositoryMock.save(any(OfferEntity.class))).thenReturn(null); 
		OfferTo offer = brokerImpl.getSellOffer("PKO", 100);

		// then
		assertTrue(offer.getPrice() <= 1000);
		assertTrue(offer.getPrice() >= 980);
		assertTrue(offer.getQuantity() >= 80);
		assertTrue(offer.getQuantity() <= 100);
		assertTrue(offer.getComission() >= 500);
		assertEquals(offer.getActionType(), ActionType.SELL);
		assertEquals(offer.getShare(), "PKO");
		assertEquals(offer.getStatus(), OfferStatus.ACTIVE);
	}
	
	@Test
	public void testShouldReturnNullWhenNullInShareNameINGetSellOffer() {
		// given
		ShareDataTo shareDataTo = new ShareDataTo(1L, "PKO", 1000, new Date());
		
		// when
		when(stockMock.getCurrentDataForShare(anyString())).thenReturn(shareDataTo);
		when(calendarMock.getCurrentDate()).thenReturn(new Date()); 
		when(offerRepositoryMock.save(any(OfferEntity.class))).thenReturn(null); 
		OfferTo offer = brokerImpl.getSellOffer(null, 100);

		// then
		assertEquals(null, offer);
	}
	
	@Test
	public void testShouldReturnNullWhenNegativeShareQuantityInShareNameINGetSellOffer() {
		// given
		ShareDataTo shareDataTo = new ShareDataTo(1L, "PKO", 1000, new Date());
		
		// when
		when(stockMock.getCurrentDataForShare(anyString())).thenReturn(shareDataTo);
		when(calendarMock.getCurrentDate()).thenReturn(new Date()); 
		when(offerRepositoryMock.save(any(OfferEntity.class))).thenReturn(null); 
		OfferTo offer = brokerImpl.getSellOffer("PKO", -1);

		// then
		assertEquals(null, offer);
	}
	
	@Test
	public void testGetBuyOffer() {
		// given
		ShareDataTo shareDataTo = new ShareDataTo(1L, "PKO", 1000, new Date());
		
		// when
		when(stockMock.getCurrentDataForShare(anyString())).thenReturn(shareDataTo);
		when(calendarMock.getCurrentDate()).thenReturn(new Date()); 
		when(offerRepositoryMock.save(any(OfferEntity.class))).thenReturn(null); 
		OfferTo offer = brokerImpl.getBuyOffer("PKO", 100);

		// then
		assertTrue(offer.getPrice() <= 1020);
		assertTrue(offer.getPrice() >= 1000);
		assertTrue(offer.getQuantity() >= 80);
		assertTrue(offer.getQuantity() <= 100);
		assertTrue(offer.getComission() >= 500);
		assertEquals(offer.getActionType(), ActionType.BUY);
		assertEquals(offer.getShare(), "PKO");
		assertEquals(offer.getStatus(), OfferStatus.ACTIVE);
	}
	
	@Test
	public void testExecuteSellOffer() {
		// given
		OfferTo offer = new OfferTo(1L, ActionType.SELL, "PKO", 100, 1000, new Date(), Currency.PLN, 500, OfferStatus.ACTIVE);
		PlayerShareTo playerShareTo = new PlayerShareTo(1L, "PKO", 100);
		
		// when
		when(playerShareRepositoryMock.findPlayerShareByName(any())).thenReturn(PlayerShareMapper.map2Entity(Arrays.asList(playerShareTo))); 
		brokerImpl.executeSellOffer(offer);

		// then
		verify(bankMock, times(1)).executeTransaction(any(), any(), anyInt());
		verify(bankMock).executeTransaction(any(), any(), integerCaptor.capture());
		assertTrue(integerCaptor.getValue() == 99500);
	}
	
	@Test
	public void testShouldReturnNullWhenNotEnoughSharesInExecuteSellOffer() {
		// given
		OfferTo offer = new OfferTo(1L, ActionType.SELL, "PKO", 100, 1000, new Date(), Currency.PLN, 500, OfferStatus.ACTIVE);
		PlayerShareTo playerShareTo = new PlayerShareTo(1L, "PKO", 99);
		
		// when	
		when(playerShareRepositoryMock.findPlayerShareByName(any())).thenReturn(PlayerShareMapper.map2Entity(Arrays.asList(playerShareTo))); 
		FoundTransactionTo transactionTo = brokerImpl.executeSellOffer(offer);

		// then
		assertEquals(transactionTo, null);
	}
	
	@Test
	public void testExecuteBuyOffer() {
		// given
		OfferTo offer = new OfferTo(1L, ActionType.BUY, "PKO", 100, 1000, new Date(), Currency.PLN, 500, OfferStatus.ACTIVE);
		PlayerShareTo playerShareTo = new PlayerShareTo(1L, "PKO", 1);
		// when
		when(playerShareRepositoryMock.findPlayerShareByName(any())).thenReturn(PlayerShareMapper.map2Entity(Arrays.asList(playerShareTo)));
		when(bankMock.confirmTransaction(any())).thenReturn(true); 
		brokerImpl.executeBuyOffer(offer, new FoundTransactionTo());

		// then
		verify(playerShareRepositoryMock, times(1)).save(any(PlayerShareEntity.class));
		verify(playerShareRepositoryMock).save(playerShareEntityCaptor.capture());
		assertTrue(playerShareEntityCaptor.getValue().getQuantity() == 101);
	}
	
	@Test
	public void testShouldNotExecuteSaveWhenNegativeConfirmationFromBank() {
		// given
		OfferTo offer = new OfferTo(1L, ActionType.BUY, "PKO", 100, 1000, new Date(), Currency.PLN, 500, OfferStatus.ACTIVE);
		PlayerShareTo playerShareTo = new PlayerShareTo(1L, "PKO", 1);
		// when
		when(playerShareRepositoryMock.findPlayerShareByName(any())).thenReturn(PlayerShareMapper.map2Entity(Arrays.asList(playerShareTo)));
		when(bankMock.confirmTransaction(any())).thenReturn(false); 
		brokerImpl.executeBuyOffer(offer, new FoundTransactionTo());

		// then
		verify(playerShareRepositoryMock, never()).save(any(PlayerShareEntity.class));
		
	}
	
	@Test
	public void testShouldNotExecuteSaveWhenOfferNotActive() {
		// given
		OfferTo offer = new OfferTo(1L, ActionType.BUY, "PKO", 100, 1000, new Date(), Currency.PLN, 500, OfferStatus.OUTDATED);
		PlayerShareTo playerShareTo = new PlayerShareTo(1L, "PKO", 1);
		// when
		when(playerShareRepositoryMock.findPlayerShareByName(any())).thenReturn(PlayerShareMapper.map2Entity(Arrays.asList(playerShareTo)));
		when(bankMock.confirmTransaction(any())).thenReturn(false); 
		brokerImpl.executeBuyOffer(offer, new FoundTransactionTo());

		// then
		verify(playerShareRepositoryMock, never()).save(any(PlayerShareEntity.class));
		
	}
}
