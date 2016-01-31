package com.ad.exceptions;

/**
 * ListToolsException - Generic project Exception. 
 * 
 * @version 1.0
 * @author MGA
 * @date   07/02/2016
 * 
 */
public class ListToolsException extends Exception {
 
	private static final long serialVersionUID = 1L;
    private String message = "";
	    
	/**
	 * @param 	 
	 * @return 
	 */	
	public ListToolsException() {		
	}

	/**
	 * @param message	 
	 * @return 
	 */
	public ListToolsException(String message) {
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
