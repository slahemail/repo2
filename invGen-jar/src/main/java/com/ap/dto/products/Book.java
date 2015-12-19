package com.ap.dto.products;

import java.math.BigDecimal;

/**
 * Book - Product with type Book. 
 * 
 * @version 1.0
 * @author A
 * @date   13/12/2015
 * 
 */
public class Book extends Product{
	
	/**
	 * @param description 
	 * @param priceExculTax
	 * @param isImported
	 * @return 
	 */
	public Book(String description, BigDecimal priceExculTax, Boolean isImported) {
		super(description, priceExculTax, isImported);
		this.type = "Book";
	}
}
