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
import com.ap.util.AmountTools;

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
			
			 
						
		}
	}
}
 