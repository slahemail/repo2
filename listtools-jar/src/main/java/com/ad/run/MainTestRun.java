package com.ad.run;

/**
 * MainTestRun - Main class to run some tests.
 * 
 * @version 1.0
 * @author 	MGA
 * @date  	07/02/2016
 *
 */
public class MainTestRun {

	/**
	 * Get main params and run tests.
	 * @param args
	 * @return 
	 * 
	 */
	public static void main(String[] args) {
		if(args.length == 0 || !(args[0].equals("test"))) {
			System.out.println("Please use commande <java -jar partition-jar.jar test> to run tests");
		    System.exit(0);
		} else if (args[0].equals("test")) {
			/* 
			 * Tests 
			 * 
			 * */ 
			try {
				System.out.println("+--------------------START---------------------+"); 
				//TODO : Add some main tests ...
				System.out.println("+--------------------END-----------------------+");
				
			} catch (NumberFormatException e) {
				System.out.println("Error : Please check the input number");
			} 
			// TODO : 
			/*catch (ListToolsException e ) {
				System.out.println(e.getMessage());
			}*/						
		}	 
	}
}
