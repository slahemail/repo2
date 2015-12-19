package com.ap.dto;

import com.ap.exceptions.InvGenerationException;

/**
 * Tax - tax details . 
 * 
 * @version 1.0
 * @author A
 * @date   13/12/2015
 * 
 */
public class Tax {
	private Float			taxRate;			   	// Tax rate not yet divide by 100 - example : 10 for 10% 
	private String			taxDesc	= "";			// Tax Desc
	//private List<String> 	taxExcluedProducts ;   	//TODO : Taxes can get a list of excluded Product
			
	/**
	 * @param taxDesc
	 * @param taxValue
	 * @return 
	 */
	public Tax(String taxDesc, Float taxRate) {	  	
		this.taxDesc 			= taxDesc;  
		this.taxRate			= taxRate;
		//this.taxExcluedProducts	= new ArrayList<>();
	}
	
	/**
	 * @param 
	 * @return taxRate
	 */
	public Float getTaxRate() {
		return taxRate;
	}
	
	/**
	 * @param taxRate
	 * @return 
	 * @throws InvGenerationException
	 */
	public void setTaxValue(Float taxRate) throws InvGenerationException {
		if (taxRate<0 || taxRate>100)
			throw new InvGenerationException("Tax Rate should be between 0 and 100");  
		this.taxRate = taxRate; 
	}
	
	/**
	 * @param 
	 * @return taxDesc
	 */
	public String getTaxDesc() {
		return taxDesc;
	}
	
	/**
	 * @param taxDesc 
	 * @return 
	 */
	public void setTaxDesc(String taxDesc) {
		this.taxDesc = taxDesc;
	}	 
}
