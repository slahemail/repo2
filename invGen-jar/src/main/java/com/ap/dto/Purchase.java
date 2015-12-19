package com.ap.dto;

import java.math.BigDecimal;
import java.util.List;

import com.ap.dto.products.Product;
import com.ap.exceptions.PopulateProdTaxListException;
import com.ap.services.TaxServices;

/**
 * Purchase - holds only one product with its tax list and quantity . 
 * 
 * @version 1.0
 * @author A
 * @date   13/12/2015
 * 
 */
public class Purchase {
	Product product;				
	private List<Tax> 	taxList;				// Product tax list
	Integer quantity;							// Quantity 
	private BigDecimal purchaseAmountTaxExclu;	// purchase Amount Tax Excluded
	private BigDecimal purchaseTaxAmount;		// purchase Tax Amount
	private BigDecimal purchaseAmountTaxInc;    // purchase include  Tax Amount
	
	/**
	 * @param  product
	 * @param  quantity
	 * @throws PopulateProdTaxListException
	 * @return 	
	 */	
	public Purchase(Product product, int quantity) throws PopulateProdTaxListException	{
		this.product 	= product;
		this.quantity 	= quantity;
		this.taxList 	= TaxServices.populateProductTaxList(this);
		this.purchaseAmountTaxExclu			= new BigDecimal("0");
		this.purchaseTaxAmount				= new BigDecimal("0");
		this.purchaseAmountTaxInc			= new BigDecimal("0");
	}
	
	
	/**
	 * @param  
	 * @return product	
	 */	
	public Product getProduct() {
		return product;
	}
	
	
	/**
	 * @param  
	 * @return product	
	 */	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	/**
	 * @param  
	 * @return taxList	
	 */	
	public List<Tax> getTaxList() {
		return taxList;
	}
	
	/**
	 * @param  taxList
	 * @return 	
	 */	
	private void setTaxList(List<Tax> taxList) {
		this.taxList = taxList;
	}
	
	/**
	 * @param  
	 * @return quantity	
	 */	
	public Integer getQuantity() {
		return quantity;
	}
	
	/**
	 * @param  quantity
	 * @return 	
	 */	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * @param  
	 * @return purchaseAmountTaxInc	
	 */	
	public BigDecimal getPurchaseAmountTaxInc() {
		return purchaseAmountTaxInc;
	}
	
	/**
	 * @param  purchaseAmountTaxInc
	 * @return 	
	 */	
	public void setPurchaseAmountTaxInc(BigDecimal purchaseAmountTaxInc) {
		this.purchaseAmountTaxInc = purchaseAmountTaxInc;
	}
	
	/**
	 * @param  
	 * @return purchaseAmountTaxExclu	
	 */	
	public BigDecimal getPurchaseAmountTaxExclu() {
		return purchaseAmountTaxExclu;
	}

	/**
	 * @param  purchaseAmountTaxExclu
	 * @return 	
	 */	
	public void setPurchaseAmountTaxExclu(BigDecimal purchaseAmountTaxExclu) {
		this.purchaseAmountTaxExclu = purchaseAmountTaxExclu;
	}

	/**
	 * @param  
	 * @return purchaseTaxAmount	
	 */	
	public BigDecimal getPurchaseTaxAmount() {
		return purchaseTaxAmount;
	}

	/**
	 * @param  purchaseTaxAmount
	 * @return 	
	 */	
	public void setPurchaseTaxAmount(BigDecimal purchaseTaxAmount) {
		this.purchaseTaxAmount = purchaseTaxAmount;
	}

}
