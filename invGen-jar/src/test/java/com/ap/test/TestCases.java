package com.ap.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ap.dto.Invoice;
import com.ap.dto.Tax;
import com.ap.dto.products.Book;
import com.ap.dto.products.Drug;
import com.ap.dto.products.Food;
import com.ap.dto.products.Product;
import com.ap.exceptions.RoundAmountException;
import com.ap.exceptions.TaxCalcException;
import com.ap.exceptions.InvGenerationException;
import com.ap.services.TaxServices;
import com.ap.util.AmountTools;
import com.ap.util.HibernateUtil;

/**
 * TestCases - Junit tests class : 
 * 
 * @version 1.0
 * @author A
 * @date   13/12/2015
 * 
 */
public class TestCases {
	private static Logger logger = LoggerFactory.getLogger( TestCases.class );
	
	/**
	 * roundTest - Test for round tax amount.
	 * 	Example :	0.99 ==> 1.00
	 *              1.02 ==> 1.05 
	 * 
	 * @return 	void
	 *  
	 */
	@Test
	public void roundTest()  {    
		// Round Tests 
		try {
			System.out.println("+---------------+--------------+"); 
			System.out.println("| Taxe calculée | Taxe imputée |");
			System.out.println("+---------------+--------------+");
			System.out.println("|          0.99 |          " + AmountTools.roundAmount(new BigDecimal("0.99")) + "|");
			System.out.println("+---------------+--------------+");
			System.out.println("|          1.00 |          " + AmountTools.roundAmount(new BigDecimal("1.00")) + "|");
			System.out.println("+---------------+--------------+");
			System.out.println("|          1.01 |          " + AmountTools.roundAmount(new BigDecimal("1.01")) + "|");
			System.out.println("+---------------+--------------+");
			System.out.println("|          1.02 |          " + AmountTools.roundAmount(new BigDecimal("1.02")) + "|");
			System.out.println("+---------------+--------------+\n\n\n");
			
			assertEquals(AmountTools.roundAmount(new BigDecimal("0.99")),new BigDecimal("1.00"));
			assertEquals(AmountTools.roundAmount(new BigDecimal("1.00")),new BigDecimal("1.00"));
			assertEquals(AmountTools.roundAmount(new BigDecimal("1.01")),new BigDecimal("1.05"));
			assertEquals(AmountTools.roundAmount(new BigDecimal("1.02")),new BigDecimal("1.05"));	
		} catch (NumberFormatException e) {
			logger.error("Error : Please check the input number");
		} catch (RoundAmountException e ) {
			logger.error("roundTest - Error :" +  e.getMessage()); 
		}
	}
	
	
	/**
	 * roundTest - Test for calculating tax and totals of Invoice.
	 * 
	 * @return 	void
	 * @throws TaxCalcException 
	 *  
	 */
	@Test
	public void taxCalucationTest()  {  
		/* Start Hibernate session */
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();		 
		Session session = sessionFactory.openSession();
		
		/* Initialization of all taxes  */
		Tax taxSTD = new Tax("STD Tax", 10F);   
		Tax taxIMP = new Tax("Imported Tax", 5F); 
		
		
		session.beginTransaction();
		session.save(taxSTD);
		session.save(taxIMP);
		session.getTransaction().commit();
		
		/* Get taxes list */
		Query q = session.createQuery("From Tax ");        
        List<Tax> resultList = q.list();  //TODO :check this warning 
        System.out.println("num of employess:" + resultList.size());
        for (Tax tax : resultList) {
            logger.debug("next employee: {}", tax.getTaxDesc());
        }
                
		
		/* Initialization of products */
		Product product1 	= new Book("livre", new BigDecimal("12.49"), false);  //TODO_MED:2.0 ou 2,0
		Product product2 	= new Product("CD musical", new BigDecimal("14.99"), false);   
		Product product3 	= new Food("barre de chocolat", new BigDecimal("0.85"), false);		
		//
		Product product4 	= new Food("boite de chocolats importée", new BigDecimal("10.00"), true);   
		Product product5 	= new Product("flacon de parfun importé", new BigDecimal("47.50"), true);   
		//		
		Product product6 	= new Product("flacon de parfun importé", new BigDecimal("27.99"), true);   
		Product product7 	= new Product("flacon de parfun", new BigDecimal("18.99"), false);
		Product product8 	= new Drug("boite de pilules contre la migraine", new BigDecimal("9.75"), false);
		Product product9 	= new Food("boite de chocolats importés", new BigDecimal("11.25"), true);
       
		
		/* Invoice 1 - Test 1*/
		System.out.println("### INPUT\n\n"); 
		System.out.println("#### Input 1\n\n");
		//
		System.out.println("* 1 livre à 12.49\n");
		System.out.println("* 1 CD musical à 14.99\n");
		System.out.println("* 1 barre de chocolat à 0.85\n");
		//		
		Invoice i1 = new Invoice();
		
		/* Add products in basket and populate taxes list for each product */
		try {
			TaxServices.addPurchase(i1,product1,1);
			TaxServices.addPurchase(i1,product2,1);
			TaxServices.addPurchase(i1,product3,1);		
		}
		catch (InvGenerationException e){
			System.out.println(e.getMessage());  //TODO : Deal with Exception ...			
		} 
		/* Calculate taxes and totals */
		try {
			TaxServices.taxCalc(i1);
		}
		catch (InvGenerationException e){
			System.out.println(e.getMessage());  //TODO : Deal with Exception ...
			Assert.fail("Test failed : " + e.getMessage());
		}
				
		/* Invoice 2 - Test 2 - For comments please see test 1 */		
		System.out.println("### INPUT\n\n"); 
		System.out.println("#### Input 2\n\n");
		//
		System.out.println("* 1 boite de chocolats importée à 10.00\n");
		System.out.println("* 1 flacon de parfun importé à 47.50\n");		
		//	
		Invoice i2 = new Invoice();		
		try {
			TaxServices.addPurchase(i2,product4,1);
			TaxServices.addPurchase(i2,product5,1);
		}
		catch (InvGenerationException e){
			System.out.println(e.getMessage());
			Assert.fail("Test failed : " + e.getMessage());
		}		
		//
		try {
			TaxServices.taxCalc(i2);
		}
		catch (InvGenerationException e){
			System.out.println(e.getMessage());  //TODO : Deal with Exception ...	
			Assert.fail("Test failed : " + e.getMessage());
		}
				
		/* Invoice 3 - Test 3 - For comments please see test 1 */		
		System.out.println("### INPUT\n\n"); 
		System.out.println("#### Input 3\n\n");
		//
		System.out.println("* 1 flacon de parfun importé à 27.99\n");
		System.out.println("* 1 flacon de parfun à 18.99\n");
		System.out.println("* 1 boite de pilules contre la migraine à 9.75\n");
		System.out.println("* 1 boite de chocolats importés à 11.25\n");
		
	
		//		
		Invoice i3 = new Invoice();		
		try {
			TaxServices.addPurchase(i3,product6,1);
			TaxServices.addPurchase(i3,product7,1);
			TaxServices.addPurchase(i3,product8,1);
			TaxServices.addPurchase(i3,product9,1);
		}
		catch (Exception e){
			System.out.println("\n Add purchase exception \n"); //TODO : change the Exception
			Assert.fail("Test failed : " + e.getMessage());
		}		
		//
		try {
			TaxServices.taxCalc(i3);
		}
		catch (InvGenerationException e){
			System.out.println(e.getMessage());  //TODO : Deal with Exception ...
			Assert.fail("Test failed : " + e.getMessage());
		}
		
		/* Display the out put - before junit test validation */
		System.out.println("\n### OUTPUT\n\n");
		/* Display the invoice data for test 1*/
		System.out.println("#### OutPut 1\n\n");		
		TaxServices.generate(i1);
		/* Display the invoice data for test 2 */	
		System.out.println("#### OutPut 2\n\n");
		TaxServices.generate(i2);
		/* Display the invoice data for test 3*/
		System.out.println("#### OutPut 3\n\n");
		TaxServices.generate(i3);
		
		
		/* Validate the test 1 */
		assertEquals(new BigDecimal("12.49"), i1.getBasket().get(0).getPurchaseAmountTaxInc());
		assertEquals(new BigDecimal("16.49"), i1.getBasket().get(1).getPurchaseAmountTaxInc());
		assertEquals(new BigDecimal("0.85"), i1.getBasket().get(2).getPurchaseAmountTaxInc());
		assertEquals(new BigDecimal("1.50"), i1.getInvoiceTaxAmount());
		assertEquals(new BigDecimal("29.83"), i1.getInvoiceTotalAmount());
				
		/* Validate the test 2*/
		assertEquals(new BigDecimal("10.50"), i2.getBasket().get(0).getPurchaseAmountTaxInc());
		assertEquals(new BigDecimal("54.65"),i2.getBasket().get(1).getPurchaseAmountTaxInc());	
		assertEquals(new BigDecimal("7.65"), i2.getInvoiceTaxAmount());
		assertEquals(new BigDecimal("65.15"), i2.getInvoiceTotalAmount());
				
		/* Validate the test 3*/
		assertEquals(new BigDecimal("32.19"),i3.getBasket().get(0).getPurchaseAmountTaxInc());
		assertEquals(new BigDecimal("20.89"),i3.getBasket().get(1).getPurchaseAmountTaxInc());
		assertEquals(new BigDecimal("9.75"),i3.getBasket().get(2).getPurchaseAmountTaxInc());
		assertEquals(new BigDecimal("11.85"),i3.getBasket().get(3).getPurchaseAmountTaxInc());
		assertEquals(new BigDecimal("6.70"),i3.getInvoiceTaxAmount());
		assertEquals(new BigDecimal("74.68"),i3.getInvoiceTotalAmount());

	}
}
