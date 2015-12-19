package com.ap.exceptions;

/**
 * RoundAmountException - Exception for round amounts. 
 * 
 * @version 1.0
 * @author A
 * @date   13/12/2015
 * 
 */
public class RoundAmountException extends InvGenerationException {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param 	 
	 * @return 
	 */
	public RoundAmountException() {		
	}
	
	/**
	 * @param message	 
	 * @return 
	 */
	public RoundAmountException(String message) {
		super(message);
	}

	/**
	 * @param 	 
	 * @return message
	 */
	public String getMessage() {
		return "Can not round the amount !";
	}
}
