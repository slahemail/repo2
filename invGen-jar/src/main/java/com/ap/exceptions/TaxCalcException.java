package com.ap.exceptions;

/**
 * TaxCalcException - Exception for calculating taxes. 
 * 
 * @version 1.0
 * @author A
 * @date   13/12/2015
 * 
 */
public class TaxCalcException extends InvGenerationException {
	private static final long serialVersionUID = 1L;
	
	public TaxCalcException() {		
	}
	
	/**
	 * @param message	 
	 * @return 
	 */
	public TaxCalcException(String message) {
		super(message);		
	}

	/**
	 * @param 	 
	 * @return message
	 */
	public String getMessage() {
		return "Can not calculate taxes !";
	}
}
