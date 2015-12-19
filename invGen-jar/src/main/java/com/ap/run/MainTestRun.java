package com.ap.run;

import java.math.BigDecimal;

import com.ap.dto.Invoice;
import com.ap.dto.products.Book;
import com.ap.dto.products.Drug;
import com.ap.dto.products.Food;
import com.ap.dto.products.Product;
import com.ap.exceptions.RoundAmountException;
import com.ap.exceptions.InvGenerationException;
import com.ap.services.TaxServices;
import com.ap.tools.AmountTools;

/**
 * MainTestRun - Main class to run some tests. 
 * 
 * @version 1.0
 * @author A
 * @date   13/12/2015
 * 
 */
public class MainTestRun {
	/**
	 * Get main params and run tests.
	 * @param args	
	 * @return 
	 */
	public static void main(String[] args) {		
		if(args.length == 0 || !(args[0].equals("test"))) {
			System.out.println("Please use commande <java -jar invGen-jar.jar test> to run tests");
		    System.exit(0);
		} else if (args[0].equals("test")) {
			/* 
			 * Round Tests 
			 * 
			 * */ 
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
				
				
			} catch (NumberFormatException e) {
				System.out.println("Error : Please check the input number");
			} catch (RoundAmountException e ) {
				System.out.println(e.getMessage());
			}
			
			/* 
			 * Tax Calculation Tests 
			 * 
			 * */ 			
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
				i1.addPurchase(product1,1);
				i1.addPurchase(product2,1);
				i1.addPurchase(product3,1);		
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
				i2.addPurchase(product4,1);
				i2.addPurchase(product5,1);
			}
			catch (InvGenerationException e){
				System.out.println(e.getMessage());
			}		
			//
			try {
				TaxServices.taxCalc(i2);
			}
			catch (InvGenerationException e){
				System.out.println(e.getMessage());  //TODO : Deal with Exception ...			
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
				i3.addPurchase(product6,1);
				i3.addPurchase(product7,1);
				i3.addPurchase(product8,1);
				i3.addPurchase(product9,1);
			}
			catch (Exception e){
				System.out.println("\n Add purchase exception \n"); //TODO : change the Exception
			}		
			//
			try {
				TaxServices.taxCalc(i3);
			}
			catch (InvGenerationException e){
				System.out.println(e.getMessage());  //TODO : Deal with Exception ...			
			}
			
			/* Display the out put - before junit test validation */
			System.out.println	("\n### OUTPUT\n\n");
			/* Display the invoice data for test 1*/
			System.out.println("#### OutPut 1\n\n");		
			i1.generate();
			/* Display the invoice data for test 2 */	
			System.out.println("#### OutPut 2\n\n");
			i2.generate();
			/* Display the invoice data for test 3*/
			System.out.println("#### OutPut 3\n\n");
			i3.generate();
						
		}
	}
}
 