/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.luciow.warehouse;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import pl.luciow.warehouse.impl.*;
import pl.luciow.warehouse.matchers.*;
import pl.luciow.warehouse.model.*;

/**
 *
 * @author Mariusz
 */
public class OrderServiceTest {
	
	@Mock 
	private Warehouse warehouseMock;
	@Mock
	private OrderService orderService;
	@Mock
	private Order orderMock;
	@Mock
	private PaymentService paymentServiceMock;
	@Mock
	private MailService mailServiceMock;
	
	@Before 
	public void prepareMocks() {
		MockitoAnnotations.initMocks(this);
	}
	//checkes if no exception was thrown for fillOrder()
    @SuppressWarnings("unchecked")
	@Test
    public void fillOrderSuccesTest() throws NotEnoughItemsException {
    	//given
    	warehouseMock = mock(Warehouse.class);
    	orderService = new OrderServiceImpl(null, null, warehouseMock);
    	//when
    	when(warehouseMock.removeItems(any(List.class))).thenReturn(null); 
    	
    	//then
    	try {
			orderService.fillOrder(new Order());
		} catch (OrderProcessException e) {
			assertTrue(false);
		}
    	assertTrue(true);
    }
    //checkes if exception was thrown for fillOrder()
    @SuppressWarnings("unchecked")
	@Test(expected=OrderProcessException.class)
    public void fillOrderThrowTest() throws NotEnoughItemsException, OrderProcessException {
    	//given
    	warehouseMock = mock(Warehouse.class);
    	orderService = new OrderServiceImpl(null, null, warehouseMock);
    	//when
    	when(warehouseMock.removeItems(any(List.class))).thenThrow(new NotEnoughItemsException());
    	orderService.fillOrder(new Order());
    	//then
    }
    //
    @SuppressWarnings("unchecked")
	@Test
    public void cancelOrderTest() {
    	//given
    	warehouseMock = mock(WarehouseImpl.class);
    	orderService = new OrderServiceImpl(null, null, warehouseMock);
    	orderMock = mock(Order.class);
    	//when
    	doCallRealMethod().when(warehouseMock).addItems(Mockito.any(List.class));
    	//then
		try {
			orderService.cancelOrder(orderMock);
		} catch (OrderProcessException e) {
			assertTrue(false);
		}
    	assertTrue(true);
    }

    @Test
    public void processPaymentThrowTest() throws Exception  {
    	//given
    	paymentServiceMock = mock(PaymentService.class);
    	mailServiceMock = mock(MailService.class);
    	orderService = new OrderServiceImpl(mailServiceMock, paymentServiceMock, null);
    	//when
    	when(paymentServiceMock.processPayment(any(Payment.class))).thenThrow(new Exception());
    	orderService.processPayment(new Order(), new Payment());
    	//then
    	verify(mailServiceMock).sendMail((Mail)argThat(new MailErrorMatcher()));
    }

    @Test
    public void processPaymentSuccessTest() throws Exception {
    	//given
    	paymentServiceMock = mock(PaymentService.class);
    	mailServiceMock = mock(MailService.class);
    	orderService = new OrderServiceImpl(mailServiceMock, paymentServiceMock, null);
    	//when
    	when(paymentServiceMock.processPayment(any(Payment.class))).thenReturn(Long.valueOf(777));
    	orderService.processPayment(new Order(), new Payment());
    	//then
    	verify(mailServiceMock).sendMail((Mail)argThat(new MailSuccessMatcher()));
    }
}
