package com.ap.dto.products;

import java.math.BigDecimal;

/**
 * Book - Product with type Food. 
 * 
 * @version 1.0
 * @author A
 * @date   13/12/2015
 * 
 */
public class Food extends Product{
	
	/**
	 * @param description 
	 * @param priceExculTax
	 * @param isImported
	 * @return 
	 */
	public Food(String description, BigDecimal priceExculTax, Boolean isImported) {
		super(description, priceExculTax, isImported);
		this.type = "Food";
	}
}
