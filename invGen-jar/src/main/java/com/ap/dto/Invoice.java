package com.ap.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.ap.dto.products.Product;
import com.ap.exceptions.InvGenerationException;

/**
 * Invoice - Invoice which can holds many purchases. 
 * 
 * @version 1.0
 * @author A
 * @date   13/12/2015
 * 
 */
public class Invoice  {    
	private List<Purchase> basket;  		// holds only one Product with Quantity and its tax list	
	private BigDecimal invoiceTaxAmount;    // The invoice tax amount 
	private BigDecimal invoiceTotalAmount;  // the invoice total amount
	
	/**
	 * Invoice - Invoice constructor 
	 * @param 
	 * @return 	
	 */
	public Invoice() {		
		this.basket 			= new ArrayList<Purchase>();
		this.invoiceTaxAmount 	= new BigDecimal("0");
		this.invoiceTotalAmount = new BigDecimal("0");
	}
		
		
	/**
	 * @param  
	 * @return basket
	 */
	public List<Purchase> getBasket() {
		return basket;
	}

	/**
	 * @param  basket
	 * @return 	
	 */	
	public void setBasket(List<Purchase> basket) {
		this.basket = basket;
	}

	/**
	 * @param  
	 * @return invoiceTaxAmount	
	 */	
	public BigDecimal getInvoiceTaxAmount() {
		return invoiceTaxAmount;
	}

	/**
	 * @param  invoiceTaxAmount
	 * @return 	
	 */	
	public void setInvoiceTaxAmount(BigDecimal invoiceTaxAmount) {
		this.invoiceTaxAmount = invoiceTaxAmount;
	}

	/**
	 * @param  
	 * @return invoiceTotalAmount	
	 */	
	public BigDecimal getInvoiceTotalAmount() {
		return invoiceTotalAmount;
	}

	/**
	 * @param  invoiceTaxAmount
	 * @return 	
	 */	
	public void setInvoiceTotalAmount(BigDecimal invoiceTotalAmount) {
		this.invoiceTotalAmount = invoiceTotalAmount;
	}
}
