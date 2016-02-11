package com.ad.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

import org.junit.Test;

import com.ad.partition.Partition;
import com.ad.partition.impl.PartitionImpl;

 

/**
 * TestCases - Junit tests class : 
 * 
 * @version 1.0
 * @author 	MGA
 * @date   	08/02/2016
 * 
 */
public class TestCases {
		
	/**
	 * 
	 * Test function for Partition of Integer List with stream functions
	 * JDK >= 8
	 * 
	 * @param 
	 * @return 
	 * 
	 */
	@Test
	public void partitionIntListByStreamTest()  {
		System.out.println("partitionIntListByStreamTest() Starts ======================"); 
		//TODO : add log
		Partition<Integer> partition = new PartitionImpl<Integer>(); //TODO : Separate Interface and class  
				
		/* Integer List */	 
		List<Integer> inputIntlist = Arrays.asList(1,6,3,4,5,7);
		
		//inputIntlist.stream().filter(i -> i != null ).sorted((i1,i2)->i1.compareTo(i2)).forEach(System.out::println);
		inputIntlist.stream()
		.filter(i -> i != null )
        .map(i-> i*2)        
        .forEach(System.out::println);
		  
		  
		System.out.println("In :: " + inputIntlist);
		
		
		List<List<Integer>>  outputSplittedtList;


		outputSplittedtList = partition.streamListPartition(inputIntlist, 2);		
		System.out.println("Out :: " + outputSplittedtList);
		
		//Expected Splitted List	
		List<List<Integer>> expectedSplittedList = new ArrayList<List<Integer>>();
		expectedSplittedList.add(Arrays.asList(1,6));
		expectedSplittedList.add(Arrays.asList(3,4));
		expectedSplittedList.add(Arrays.asList(5,null));
		expectedSplittedList.add(Arrays.asList(7));		
		
		/* Convert two lists into string and compare them */
		String expectedSplittedListString 	= partition.concatWithSep(expectedSplittedList);
		String outputSplittedtListString 	= partition.concatWithSep(outputSplittedtList);
		assertEquals(expectedSplittedListString, outputSplittedtListString);		
	}
	
	/**
	 * 
	 * Test function for Partition of Integer List 
	 * JDK <= 8
	 * 
	 * @param 
	 * @return 
	 * 
	 */
	@Test
	public void partitionIntListTest()  { 
		System.out.println("partitionIntListTest() Starts ======================"); 
		Partition<Integer> partition = new PartitionImpl<Integer>(); //TODO : Separate Interface and class  
				
		/* Integer List */	 
		List<Integer> inputIntlist = Arrays.asList(1,6,3,4,5,null,7);
		System.out.println("In :: " + inputIntlist);
		
		
		List<List<Integer>>  outputSplittedtList;


		outputSplittedtList =  partition.listPartition(inputIntlist, 2);		
		System.out.println("Out :: " + outputSplittedtList);
		
		//Expected Splitted List	
		List<List<Integer>> expectedSplittedList = new ArrayList<List<Integer>>();
		expectedSplittedList.add(Arrays.asList(1,6));
		expectedSplittedList.add(Arrays.asList(3,4));
		expectedSplittedList.add(Arrays.asList(5,null));
		expectedSplittedList.add(Arrays.asList(7));		
		
		/* Convert two lists into string and compare them */
		String expectedSplittedListString 	= partition.concatWithSep(expectedSplittedList);
		String outputSplittedtListString 	= partition.concatWithSep(outputSplittedtList);
		assertEquals(expectedSplittedListString, outputSplittedtListString);		
	}
	
	/**
	 * 
	 * Test function for Partition of String List with stream functions
	 * JDK >= 8
	 * 
	 * @param 
	 * @return 
	 * 
	 */
	@Test
	public void partitionStringListByStreamTest()  { 
		System.out.println("partitionStringListTest() Starts ======================"); 
		Partition<String> stringPartition = new PartitionImpl<String>(); //TODO : Separate Interface and Impl  

		/* String List */
		List<String> inputStringList = new ArrayList<String>(); 
		inputStringList.add("str1");
		inputStringList.add("str2");
		inputStringList.add("null");
		inputStringList.add("str4");		
		inputStringList.add("str5");
		inputStringList.add("str6");		
		inputStringList.add("str7");
		inputStringList.add("str8");			
		inputStringList.add("str9");	//TODO : test with null values		
		System.out.println("In :: " + inputStringList);
		
		List<List<String>> outPutStringList =  stringPartition.streamListPartition(inputStringList, 2); 
		System.out.println("Out :: " + outPutStringList);
		
		//Expected Splitted List	
		List<List<String>> expectedSplittedList = new ArrayList<List<String>>();
		expectedSplittedList.add(Arrays.asList("str1","str2"));
		expectedSplittedList.add(Arrays.asList(null,"str4"));
		expectedSplittedList.add(Arrays.asList("str5","str6"));
		expectedSplittedList.add(Arrays.asList("str7","str8"));
		expectedSplittedList.add(Arrays.asList("str9"));
				
		/* Convert two lists into string and compare them */
		String expectedSplittedListString 	= stringPartition.concatWithSep(expectedSplittedList);
		String outputSplittedtListString 	= stringPartition.concatWithSep(outPutStringList);
		assertEquals(expectedSplittedListString, outputSplittedtListString);	
	}
	
	/* Partition by index ( other needs , other then the exercise )
	@Test
	public void partitionListByIndexTest()  { 
		
		List<String> stringList = new ArrayList<String>(); 
		stringList.add("str1");
		stringList.add("str2");
		stringList.add("str3");
		stringList.add("str4");		
		stringList.add("str5");
		stringList.add("str6");		
		stringList.add("str7");
		stringList.add("str8");			
		stringList.add("str9");	
		System.out.println("String by index ======================");
		List<Integer> indexes = Arrays.asList(0,2,4);	
		System.out.println(indexes.stream().map(stringList::get).collect(Collectors.toList()));
		
		 
		// TODO : compare two sorted list 
		Collection<String> listOne = new ArrayList(Arrays.asList("a","b", "c", "c", "d", "e", "f", "g"));
		Collection<String> listTwo = new ArrayList(Arrays.asList("a","b", "c", "e", "d", "f", "g"));
		//
		List<String> sourceList = new ArrayList<String>(listOne);
		List<String> destinationList = new ArrayList<String>(listTwo);
		//	
		sourceList.removeAll( listTwo );
		destinationList.removeAll( listOne );	
		//
		System.out.println( sourceList );
		System.out.println( destinationList );
			
	}
	*/
}
 