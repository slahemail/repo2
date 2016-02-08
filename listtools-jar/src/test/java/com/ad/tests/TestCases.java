package com.ad.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.ad.partition.Partition;
import com.ad.partition.PartitionImp;

 

/**
 * TestCases - Junit tests class : 
 * 
 * @version 1.0
 * @author 	MGA
 * @date   	08/02/2016
 * 
 */
public class TestCases {
 
	@Test
	public void partitionTest()  { 
		//TODO : add log
		Partition<Integer> partition = new PartitionImp<Integer>(); //TODO : Separate Interface and class  
				
		/* Integer List */	 
		List<Integer> inputIntlist = Arrays.asList(1,2,3,4,5);
		List<List<Integer>>  outputSplittedtList;
		
		/* Integer List - jdk <= 8 */	 
		System.out.println("Integer Partition jdk <= 8 ======================");
		outputSplittedtList = partition.partitionOldWay(inputIntlist, 2);
		System.out.println(outputSplittedtList);
		
		
		/* Integer List - jdk = 8 */
		System.out.println("Integer Partition jdk 8 ======================");
		outputSplittedtList = inputIntlist.stream().collect(partition.streamPartition(2));		
		System.out.println(outputSplittedtList);
		
		//---------------------------------------------------------
		/*
		List<List<Integer>> expectedSplittedList = new ArrayList<List<Integer>>();
		expectedSplittedList.add(Arrays.asList(1,2));
		expectedSplittedList.add(Arrays.asList(3,4));
		expectedSplittedList.add(Arrays.asList(5));
		
		
		final String listJoinSeparator = " | ";
		final String joinSeparator = ", ";

	    String firstResult = outputSplittedtList 
	            .stream()
	            .map(outputSplittedtList::get)
	            .collect(Collectors.joining(joinSeparator));

	    String secondResult = myClassList
	            .stream()
	            .map(MyClass::getMyString)
	            .collect(Collectors.joining(joinSeparator));

	    //Just compare them using equals:
	    System.out.println(firstResult.equals(secondResult));
	    */
		//---------------------------------------------------------
		
				
		/* String List */
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
		
		
		System.out.println("String streamPartition jdk <= 8 ======================");
		Partition<String> partitionString = new PartitionImp<String>(); //TODO : Separate Interface and class  
		List<List<String>> l3 =  partitionString.partitionOldWay(stringList, 2) ;
		System.out.println(l3);
		
		System.out.println("String streamPartition jdk 8 ======================");
		List<List<String>> l4 = stringList.stream().collect(partitionString.streamPartition(2));
		System.out.println(l4);
		
					
		/* Partition by index ( other needs , other then the exercise ) */	
		System.out.println("String by index ======================");
		List<Integer> indexes = Arrays.asList(0,2,4);	
		System.out.println(indexes.stream().map(stringList::get).collect(Collectors.toList()));
						
		/*
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
		*/
		
		
		// TODO : compare results ... 
		assertEquals("true", "true");
	}
	 
}
 