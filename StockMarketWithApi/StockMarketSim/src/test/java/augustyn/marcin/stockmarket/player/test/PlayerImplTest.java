package augustyn.marcin.stockmarket.player.test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import augustyn.marcin.stockmarket.broker.Broker;
import augustyn.marcin.stockmarket.broker.to.OfferTo;
import augustyn.marcin.stockmarket.enumation.ActionType;
import augustyn.marcin.stockmarket.enumation.Currency;
import augustyn.marcin.stockmarket.enumation.OfferStatus;
import augustyn.marcin.stockmarket.player.PlayerImpl;
import augustyn.marcin.stockmarket.strategy.Strategy;
import augustyn.marcin.stockmarket.strategy.model.StockActionToPerform;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PlayerImplTest {

	@Mock 
	private Bank bankMock;
	
	@Mock
	private Broker brokerMock;
	
	@Mock
	private Strategy strategyMock;
	
	@InjectMocks
    private PlayerImpl playerImpl = new PlayerImpl();
	
	@Captor ArgumentCaptor<Integer> integerCaptor;
	
	@Before 
	public void prepareMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testPerformBuyAction() {
		// given
		OfferTo offer = new OfferTo(1L, ActionType.BUY, "PKO", 10, 1000, new Date(), Currency.PLN, 500, OfferStatus.ACTIVE);
		StockActionToPerform action = new StockActionToPerform(ActionType.BUY, "PKO", 10, null, 900, Currency.PLN, new Date());
		List<StockActionToPerform> actions = new ArrayList<>();
		actions.add(action);
		// when
		when(strategyMock.getActions(any(), any())).thenReturn(actions);
		when(brokerMock.getBuyOffer(any(), anyInt())).thenReturn(offer);
		when(strategyMock.analyzeBuyOffer(any(), any())).thenReturn(true);
		when(bankMock.checkBalance()).thenReturn(null);
		when(brokerMock.checkShareBalance()).thenReturn(null);
		
		playerImpl.performActions();
		// then
		verify(bankMock).executeTransaction(any(), any(), integerCaptor.capture());
		verify(brokerMock, times(1)).executeBuyOffer(any(), any());
		assertTrue(integerCaptor.getValue() == 10500);
	}
	
	@Test
	public void testPerformSellAction() {
		// given
		OfferTo offer = new OfferTo(2L, ActionType.SELL, "PKO", 10, 1000, new Date(), Currency.PLN, 500, OfferStatus.ACTIVE);
		StockActionToPerform action = new StockActionToPerform(ActionType.SELL, "PKO", 10, 900, null, Currency.PLN, new Date());
		List<StockActionToPerform> actions = new ArrayList<>();
		actions.add(action);
		// when
		when(strategyMock.getActions(any(), any())).thenReturn(actions);
		when(brokerMock.getSellOffer(any(), anyInt())).thenReturn(offer);
		when(strategyMock.analyzeSellOffer(any(), any())).thenReturn(true);
		when(bankMock.checkBalance()).thenReturn(null);
		when(brokerMock.checkShareBalance()).thenReturn(null);
		
		playerImpl.performActions();
		// then
		verify(brokerMock, times(1)).executeSellOffer(any());
	}
}
