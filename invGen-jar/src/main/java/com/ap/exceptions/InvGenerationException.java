package com.ap.exceptions;

/**
 * InvGenerationException - Generic project Exception. 
 * 
 * @version 1.0
 * @author A
 * @date   13/12/2015
 * 
 */
public class InvGenerationException extends Exception {
 
	private static final long serialVersionUID = 1L;
    private String message = "";
	    
	/**
	 * @param 	 
	 * @return 
	 */	
	public InvGenerationException() {		
	}

	/**
	 * @param message	 
	 * @return 
	 */
	public InvGenerationException(String message) {
		this.message  = message;
	}
	
	/**
	 * @param 	 
	 * @return message
	 */
	public String getMessage() {
		return this.message;
	}
}
