package com.ap.dto.products;

import java.math.BigDecimal;

/**
 * Product - Product Bean. 
 * 
 * @version 1.0
 * @author A
 * @date   13/12/2015
 * 
 */
public class Product  {

	protected String 			description;  			// Description of Product 
	protected BigDecimal 		priceTaxExclu;			// Price of Product tax Excluded
	protected Boolean 	  		isImported	= false;	// is the Product imported ?
	protected String 			type		= "";		// Type of product : so far we have Book , Drug , Food or Empty for other
  	

	/**
	 * @param description 
	 * @param priceExculTax
	 * @param isImported
	 * @return 
	 */
	public Product(String description, BigDecimal priceExculTax, Boolean isImported) {
		this.description 	= description;
		this.priceTaxExclu 	= priceExculTax;
		this.isImported		= isImported; 
	}
		
	/**
	 * @param
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param
	 * @return priceTaxExclu
	 */
	public BigDecimal getPriceTaxExclu() {
		return priceTaxExclu;
	}

	/**
	 * @param
	 * @return isImported
	 */
	public Boolean getIsImported() {
		return isImported;
	}

	/**
	 * @param
	 * @return type
	 */
	public String getType() {
		return type;
	}		
}
