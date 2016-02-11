package com.ad.partition.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.ad.partition.Partition;


/**
 * PartitionImp Class - List Partition tools implementation. 
 * 
 * @version 1.0
 * @author MGA
 * @date   07/02/2016
 * 
 */
public class PartitionImpl <T> implements Partition<T>, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * Split given List to sub list flowing the given size
	 * JDK <= 8  
	 * 
	 * @param size : size of sub list
	 * @return splitted list
	 * 
	 */
	public List<List<T>> listPartition(List<T> inList, Integer size) {
		if (verifieListAndPram(inList, size)) {
			return null; //TODO :  change to Exception ?
		}			 
		
		// TODO : add exceptions and comments 
		// TODO : deal with null cases 
		// TODO : deal with min max 
		List<List<T>> motherList = new ArrayList<List<T>>();
		try {		
		  int i,j;
		  
		  for( i=0; i< inList.size();    ) {
			  List<T> childList = new ArrayList<T>();			  
			  for (j=0 ; j< size; j++ ) {
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

	/**
	 * 
	 * Split given List to sub list flowing the given size
	 * JDK >= 8 ( using stream functions )
	 * 
	 * @param size : size of sub list
	 * @return splitted list
	 * 
	 */
	@Override
	public List<List<T>> streamListPartition(List<T> inList, Integer size) {
		if (verifieListAndPram(inList, size)) {
			return null; //TODO :  change to Exception ?
		}
		AtomicInteger index = new AtomicInteger(0);		
		final List<T> current = new ArrayList<>();	
		return inList.stream().collect(Collector.of( 
							() -> new ArrayList<List<T>>(),
							(l, elem) -> {
								/* System.out.println("sep = " + sep + " | index = " + index); */
								current.add(elem); index.incrementAndGet()	;
	                        	if(  index.compareAndSet(size, 0)  )  {	                        		
	                        		l.add(new ArrayList<>(current)); 
                	            	current.clear();          	  
                	            }  	                        	
                	         },
                	         (l1, l2) -> {throw new RuntimeException("");},
                	         l -> {if(current.size() != 0) l.add(current); return l;}
				));		
	}
		
	/**
	 * 
	 * Concact the given List of List in one String 
	 * JDK >= 8
	 * 
	 * @param  listOfList : size of sub list
	 * @return String with all elements 
	 * 
	 */
	public String concatWithSep(List<List<T>> listOfList) {		
	 
		//TODO :  Check if listOfList is null 		
		// TODO : exceptions	 
		//String concatedString = null;
		
		StringJoiner listJoinner =  new StringJoiner("|");
		StringJoiner elemJoinner = new StringJoiner(",");
		
		for (List<T> list : listOfList ) {
			for (T elem : list ) {
				if (elem == null) {
					elemJoinner.add("null");
				}
				else {
					elemJoinner.add(elem.toString());
				}	
			}
			listJoinner.add(elemJoinner.toString());
			elemJoinner = new StringJoiner(",");
		}
		return listJoinner.toString();				
	}
	
	/**
	 * 
	 * Split given List flowing the given index List
	 * JDK >= 8 ( using stream functions )
	 * 
	 * @param list : list to be splitted 
	 * @param indexes : list of needed index
	 * @return splitted list
	 * 
	 */
	public <T> List<T> getByIndices(List<T> list, List<Integer> indexes) {
		// TODO : Exceptions 
		// TODO : Min, Max and Null 
	    return indexes.stream().map(list::get).collect(Collectors.toList());	    
	}
	
	public boolean verifieListAndPram(List<T> inList, Integer size) {		
		try {
			if (size != null && inList != null 
			&&  size <= inList.size() && size > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		
	}
	
	// TODO : concatWithSep with streams 
	/*
	public String streamConcatWithSep(List<List<T>> l) {		

		String firstResult = outputSplittedtList 
		        .stream()
		        .map(flat::get)
		        .collect(Collectors.joining(joinSeparator));
				 
	    String secondResult = myClassList
	            .stream()
	            .map(MyClass::getMyString)
	            .collect(Collectors.joining(joinSeparator));
	    //Just compare them using equals:
	    System.out.println(firstResult.equals(secondResult));		 
		 
		
		final String listJoinSeparator = " | ";
		final String joinSeparator = ", ";
			 		 
		List<Integer> exList =   Arrays.asList(1,2);
		//exList = exList.stream().map(i->i+2);
		
		Arrays.stream(new int[] {1, 2, 3})
		.map(n -> 2 * n + 1)
		 ;
			
			
		List<Integer> flat = 
						expectedSplittedList.stream()
				        .flatMap(l -> l.stream())
				        .collect(Collectors.toList());
		
		
		System.out.println("flat ==> " + flat);	
	}
	*/	
}