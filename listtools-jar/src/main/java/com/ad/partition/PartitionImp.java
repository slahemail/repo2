package com.ad.partition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * PartitionImp Class - List Partition tools implementation. 
 * 
 * @version 1.0
 * @author MGA
 * @date   07/02/2016
 * 
 */
public class PartitionImp implements Partition, Serializable {
	public List<List<Integer>> partitionOldWay(List<Integer> inList, int segment) {
		// TODO : add excptions and comments 
		// TODO : deal with null cases 
		// TODO : deal with min max 
		List<List<Integer>> motherList = new ArrayList<List<Integer>>();
		try {		
		  int i,j;
		  
		  for( i=0; i< inList.size();    ) {
			  List<Integer> childList = new ArrayList<Integer>();			  
			  for (j=0 ; j< segment; j++ ) {
				  if (i+j < inList.size())
					  childList.add(inList.get(i+j));
				  else 
					  break;				  
			  }
			  i=i+j;
			  motherList.add(childList);
		  }			
		}
		catch (Exception e) {			
		}		
		return motherList;		
	}

	public Collector<String, List<List<String>>, List<List<String>>> streamPartition(int sep) {		
		 AtomicInteger index = new AtomicInteger(0);		
		 final List<String> current = new ArrayList<>();		
		 return Collector.of(
				  			() -> new ArrayList<List<String>>(),		    		
				  			(l, elem) -> {  System.out.println(sep + "-" + index.toString() );
				                        	if(  !index.compareAndSet(sep, 0)  )  {
				                        		current.add(elem);
				                        		index.incrementAndGet()	;	  
			                	            } else  {				                	            	
			                	            	l.add(new ArrayList<>(current)); current.clear();			
			                	            }					                        	                      	            	                    	            
	                        	         },
	                        (l1, l2) -> {throw new RuntimeException("Should not run this in parallel");},
	                        l -> {if(current.size() != 0) l.add(current); return l;}
	                        );	    
	}
		
	public <T> List<T> getByIndices(List<T> list, List<Integer> indexes) {		
	    return indexes.stream().map(list::get).collect(Collectors.toList());	    
	}
	
}
