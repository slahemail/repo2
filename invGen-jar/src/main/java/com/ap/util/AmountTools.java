package com.ap.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ap.exceptions.RoundAmountException;

/**
 * AmountTools : Some tools use for manipulate Amounts
 * 		- Round Amounts - example : 0.99 to 0.10.
 * 		- format double to be display with 2 decimals 
 * 
 * @version 1.0
 * @author A
 * @date   13/12/2015
 * 
 */
public class AmountTools {
	private static Logger logger = LoggerFactory.getLogger( AmountTools.class );
	/**
	 * roundAmount - Populates taxes list for the product.
	 * 
	 * @return 	purchase of basket is populated with taxes list to be applied. 
	 * @param 	amount
	 * @exception so far the exception is generic	
	 * @throws RoundAmountException 
	 *  
	 */
	public static BigDecimal roundAmount(BigDecimal amount) throws RoundAmountException {
		try {
			//logger.debug("roundAmount : {}", amount);
			/* Specify the increment */ 
			BigDecimal increment  		= new BigDecimal("0.05");  
			/* Specify the rounding methode */
			RoundingMode roundingMode 	=  RoundingMode.UP; 
			/* divide and multipy the amount to get the rounded amount */
	        BigDecimal divided 	= amount.divide(increment, 0, roundingMode);
	        amount 	= divided.multiply(increment);
			return amount;			 	
		} catch (Exception e) {
			throw new RoundAmountException();
		}		
    }	
}
