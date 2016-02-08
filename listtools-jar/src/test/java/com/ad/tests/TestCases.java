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
		//TODO : add log
		Partition partition = new PartitionImp(); //TODO : Conception Separate  
				
		// Integer List	 
		List<Integer> list3 = Arrays.asList(1,7,0,4,5);
		
		// TODO : will be changed by stream partition
		System.out.println("Integer Partition ======================");
		System.out.println(partition.partitionOldWay(list3, 4));
		
		// String List
		List<String> stringList = new ArrayList<String>(); 
		stringList.add("s1");
		stringList.add(null);
		
		stringList.add("s2");
		stringList.add(null);
		
		stringList.add("s3");
		stringList.add(null);
		
		stringList.add("s4");
		stringList.add(null);
			
		stringList.add("s5");
		stringList.add(null);
		
		stringList.add("s6");
		stringList.add(null);
		
		stringList.add("s7");
		
		
		
		System.out.println("String streamPartition ======================");
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
		
		// Partition by index ( other needs , other then the exercise )	
		System.out.println("String by index ======================");
		List<Integer> indexes = Arrays.asList(0,2,4);	
		System.out.println(indexes.stream().map(stringList::get).collect(Collectors.toList()));
							 
		
		// TODO : compare results ... 
		assertEquals("true", "true");
	}
	 
}
 