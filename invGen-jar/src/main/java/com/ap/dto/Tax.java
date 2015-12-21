package com.ap.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ap.exceptions.InvGenerationException;

/**
 * Tax - tax details . 
 * 
 * @version 1.0
 * @author A
 * @date   13/12/2015
 *  
 * 
 */
@Entity
@Table
public class Tax {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer 		taxId;
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
	 * @return taxId
	 */
	public Integer getTaxId() {
		return taxId;
	}

	/**
	 * @param taxId
	 * @return 
	 */
	public void setTaxId(Integer taxId) {
		this.taxId = taxId;
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
	public void setTaxRate(Float taxRate) throws InvGenerationException {
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
	
	/* How to create in DB */	
	/*
	    CREATE TABLE public."Tax"
		(
		  "taxDesc" "char"[],
		  "taxId" BIGSERIAL NOT NULL,
		  "taxRate" double precision,
		  CONSTRAINT "TaxPk" PRIMARY KEY ("taxId")		  
		)
		WITH (
		  OIDS=FALSE
		);
	 */
}
