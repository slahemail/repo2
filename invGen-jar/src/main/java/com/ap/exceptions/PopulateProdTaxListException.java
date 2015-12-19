package com.ap.exceptions;

/**
 * PopulateProdTaxListException - Exception for populating tax list. 
 * 
 * @version 1.0
 * @author A
 * @date   13/12/2015
 * 
 */
public class PopulateProdTaxListException extends InvGenerationException {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param 	 
	 * @return 
	 */
	public PopulateProdTaxListException() {		
	}
	
	/**
	 * @param message	 
	 * @return 
	 */
	public PopulateProdTaxListException(String message) {
		super(message);
	}

	/**
	 * @param 	 
	 * @return message
	 */
	public String getMessage() {
		return "Can not get the Product tax list !";
	}
}
