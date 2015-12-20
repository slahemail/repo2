package com.ap.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.ap.dto.Invoice;
import com.ap.dto.Purchase;
import com.ap.dto.Tax;
import com.ap.dto.products.Product;
import com.ap.exceptions.InvGenerationException;
import com.ap.exceptions.PopulateProdTaxListException;
import com.ap.exceptions.TaxCalcException;
import com.ap.util.AmountTools;

/**
 * TaxServices Service : 
 * 		- Populates taxes list for each product.
 * 		- Calculate taxes Amount for each product. 
 * 		- Calculates totals for the invoice.
 * 		- Rounds Amounts. 
 * 
 * @version 1.0
 * @author A
 * @date   13/12/2015
 * 
 */
public interface TaxServices {
	
	/* Initialization of all taxes  */
	Tax taxSTD = new Tax("STD Tax", 10F);   
	Tax taxIMP = new Tax("Imported Tax", 5F);   
	
	
	/**
	 * populateProductTaxList - Populates taxes list for the product.
	 * 
	 * @return 	purchase of basket is populated with taxes list to be applied. 
	 * @param 	purchase : One purchase of the invoice basket
	 * @exception so far the exception is generic 
	 * @throws PopulateProdTaxListException
	 * @author A
	 * @date   13/12/2015	  
	 *  
	 */
	public static List<Tax> populateProductTaxList(Purchase purchase) throws PopulateProdTaxListException {
		try {
			Product product = purchase.getProduct();
			List<Tax> taxList = new ArrayList<Tax>();  
			/* No STD tax for "Book" , "Drug" & "Food" products */
			if (!(product.getType().equals("Book") || product.getType().equals("Drug") || product.getType().equals("Food"))) {
				taxList.add(taxSTD);			
			}	
			/* If the product is imported add the taxIMP to its tax lis */
			if (product.getIsImported()) {
				taxList.add(taxIMP);
			}			
			return taxList;
		}
		catch (Exception e) {
			throw new PopulateProdTaxListException();
		}
		finally {			
		}
	}
	
	/**
	 * taxCalc - Calculates the tax amounts of Invoice 
	 * by calculating the tax amount of each product 
	 * of the basket .
	 * 
	 * @return 	void 
	 * @param 	invoice : the invoice
	 * @exception so far the exception is generic 
	 * @throws TaxCalcException
	 * @author A
	 * @date   13/12/2015	  
	 *  
	 */
	public static void taxCalc(Invoice invoice) throws TaxCalcException {
		try {		
			/* - Basket may has may purchase 
			 * - a purchase has only one Product which can has many taxes 
			 */
			for(Purchase purchase : invoice.getBasket()) {
				/*Just to get (the Purchase Amont tax Exculding Tax) * Quantity*/   
				purchase.setPurchaseAmountTaxExclu(purchase.getProduct().getPriceTaxExclu().multiply(new BigDecimal(purchase.getQuantity())));
				//
				for (Tax tax : purchase.getTaxList())  {
					/* Convert taxRate to BigDecimal */
					BigDecimal taxRate = new BigDecimal(tax.getTaxRate().toString());
					/* Divide taxRate by 100 */
					taxRate =  taxRate.divide(new BigDecimal("100"));
					/* get the Purchase tax Amount without quantity. i.e. Quantity = 1 so far */
					purchase.setPurchaseTaxAmount(purchase.getPurchaseTaxAmount().add( AmountTools.roundAmount(purchase.getProduct().getPriceTaxExclu().multiply(taxRate))));
					/* get  (Purchase tax  Amount * quantity ) */
					purchase.setPurchaseTaxAmount(AmountTools.roundAmount(purchase.getPurchaseTaxAmount().multiply(new BigDecimal(purchase.getQuantity()))));
				}
				/* Purchase Amount include taxes */ 
				purchase.setPurchaseAmountTaxInc(purchase.getPurchaseAmountTaxExclu().add(purchase.getPurchaseTaxAmount()));
				/* Invoice Tax Amount */
				invoice.setInvoiceTaxAmount(AmountTools.roundAmount(invoice.getInvoiceTaxAmount().add(purchase.getPurchaseTaxAmount())));
				/* Invoice total Amount Tax included */
				invoice.setInvoiceTotalAmount(invoice.getInvoiceTotalAmount().add(purchase.getPurchaseAmountTaxInc()));
			}
		}
		catch (Exception e) {
			throw new TaxCalcException();			
		}		
	}	
	
	/**
	 * addPurchase - Add purchase to Invoice with quantity 
	 * @param product
	 * @param quantity 
	 * @return 	
	 *  
	 */
	public static void addPurchase(Invoice invoice, Product product, int quantity) throws  InvGenerationException {	
		if (quantity < 1 ) {
			throw new InvGenerationException("Quantity should be > 1");  //TODO
		}
		Purchase purchase = new Purchase(product, quantity);
		invoice.getBasket().add(purchase);		 
	}
	
	
	/**
	 * generate - Display the invoice date ( Quantity, Purchase, Tax Amount, total Amount)
	 * @param   
	 * @return 	
	 */	
	public static void generate(Invoice invoice) { 		
		for(Purchase purchase : invoice.getBasket()) {
			System.out.println(purchase.getQuantity() + " " +   purchase.getProduct().getDescription() + " à " + String.format(Locale.ENGLISH, "%.2f",purchase.getPurchaseAmountTaxInc()) + "\n" );		    
		}
		System.out.println("Montant des taxes : " + String.format(Locale.ENGLISH, "%.2f",invoice.getInvoiceTaxAmount()) + "\n");
		System.out.println("Total : " + String.format(Locale.ENGLISH, "%.2f",invoice.getInvoiceTotalAmount()) + "\n");		
	}
}
