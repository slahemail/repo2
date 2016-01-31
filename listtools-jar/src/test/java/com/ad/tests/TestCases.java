package com.ad.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ad.partition.Partition;
import com.ad.partition.PartitionImp;
import com.ad.tools.Tools;

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
		Partition partition = new PartitionImp(); //TODO : Conception Separate  
				
		// Integer List	 
		List<Integer> list3 = Arrays.asList(1,7,0,4,5);
		System.out.println(partition.partitionOldWay(list3, 2));
		
		// String List
		List<String> stringList = new ArrayList<String>(); 
		stringList.add("e1");
		stringList.add(null);
		
		stringList.add("e2");
		stringList.add(null);
		
	    stringList.add("e3");
		stringList.add(null);
		
		stringList.add("e4");
	    stringList.add(null);
			
		stringList.add("e5");
		stringList.add(null);
		
		stringList.add("e6");
		stringList.add(null);
		
		stringList.add("e7");
		
		
		
		System.out.println("splitBySeparator2 ======================");
		List<List<String>> l3 = stringList.stream().collect(partition.streamPartition(2));
		System.out.println(l3);
		
			
	    // TODO : some tests ...		
		/*
	    motherList = partition(list3, 2);
		System.out.println(motherList);	
		
	
		motherList = partition(list3, 1);
		System.out.println(motherList);	
		
		
		
		motherList = partition(list3, 7);
		System.out.println(motherList);
		
		
		motherList = partition(list3, -1);
		System.out.println(motherList);	
		
		
		motherList = partition(null, 2);
		System.out.println(motherList);	
		
		motherList = partition(Arrays.asList(1),1 );
		System.out.println(motherList);	
		*/
		
		// Partiton by index ( other needs , other then the exercise )		
		List<Integer> indexes = Arrays.asList(0,1,2);	
		System.out.println(indexes.stream().map(stringList::get).collect(Collectors.toList()));
							 
		
		// TODO : compare results ... 
		assertEquals("true", "true");
	}
	 
}
 