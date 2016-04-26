/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.luciow.warehouse.impl;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import pl.luciow.warehouse.PaymentProcessor;
import pl.luciow.warehouse.model.Payment;
import pl.luciow.warehouse.util.PaymentValidator;
/**
 *
 * @author Mariusz
 */
@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceImplTest {
	private static final Logger logger = LogManager.getLogger(PaymentServiceImplTest.class);

    @Mock
    private PaymentProcessor paymentProcessor;

    @Mock
    private PaymentValidator paymentValidator;
    
    @InjectMocks
    private PaymentServiceImpl paymentServiceImpl = new PaymentServiceImpl();
  

    @Test
    public void processPaymentTest() {
    	//moze byc ale nie musi
    	//paymentProcessor = mock(PaymentProcessor.class);
    	//paymentValidator = mock(PaymentValidator.class);
    	
    	Mockito.doAnswer(new Answer<Void>() {
    	    public Void answer(InvocationOnMock invocation) {
    	    	@SuppressWarnings("unchecked")
				List<String> errorString = (List<String>)invocation.getArguments()[1];
    	    	errorString.add("Error during validate");
    	    	return null;
    	    }
    	}).when(paymentValidator).validate(Mockito.any(Payment.class),Mockito.anyListOf(String.class));
    	
    	

    	/* NIE DZIALA DLA VOID
    	 * when(paymentValidator).validate(Mockito.any(Payment.class), Mockito.anyListOf(String.class)).thenAnswer(new Answer<Void>() {
    		public Void answer(InvocationOnMock invocation) {
    	    	System.out.println("Lol");
    	    	PaymentValidator validator = (PaymentValidator)invocation.getMock();
    	    	List<String> errorString = (List<String>)invocation.getArguments()[1];
    	    	errorString.add("Error during validate");
    	        return null;
    	    }
    	});*/

    	try {
			paymentServiceImpl.processPayment(new Payment());
			logger.info("Error not occured");
			verify(paymentProcessor, Mockito.times(1)).processPayment(any(Payment.class));
		} catch (Exception e) {
			verify(paymentProcessor, never()).processPayment(any(Payment.class));
			logger.info("Error occured");
		}
    }
}
