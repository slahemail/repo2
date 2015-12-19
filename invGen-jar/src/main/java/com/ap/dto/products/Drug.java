package com.ap.dto.products;

import java.math.BigDecimal;

/**
 * Drug - Product with type Drug. 
 * 
 * @version 1.0
 * @author A
 * @date   13/12/2015
 * 
 */
public class Drug extends Product{
	
	/**
	 * @param description 
	 * @param priceExculTax
	 * @param isImported
	 * @return 
	 */
	public Drug(String description, BigDecimal priceExculTax, Boolean isImported) {
		super(description, priceExculTax, isImported);
		this.type = "Drug";
	}
}
