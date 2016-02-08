package com.ad.partition;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;



/**
 * PartitionImp Class - List Partition tools implementation. 
 * 
 * @version 1.0
 * @author MGA
 * @date   07/02/2016
 * 
 */
public class PartitionImp <T> implements Partition<T>, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* Integer List Partition */
	public List<List<T>> partitionOldWay(List<T> inList, int segment) {
		// TODO : add exceptions and comments 
		// TODO : deal with null cases 
		// TODO : deal with min max 
		List<List<T>> motherList = new ArrayList<List<T>>();
		try {		
		  int i,j;
		  
		  for( i=0; i< inList.size();    ) {
			  List<T> childList = new ArrayList<T>();			  
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

	
	@Override
	public Collector<T, List<List<T>>, List<List<T>>> streamPartition(int sep) {
		AtomicInteger index = new AtomicInteger(0);		
		final List<T> current = new ArrayList<>();	
		return Collector.of( 
							() -> new ArrayList<List<T>>(),
							(l, elem) -> {
								System.out.println("sep = " + sep + " | index = " + index);
								current.add(elem); index.incrementAndGet()	;
	                        	if(  index.compareAndSet(sep, 0)  )  {	                        		
	                        		l.add(new ArrayList<>(current)); 
                	            	current.clear();          	  
                	            }  	                        	
                	         },
                	         (l1, l2) -> {throw new RuntimeException("");},
                	         l -> {if(current.size() != 0) l.add(current); return l;}
				);		
	}
		
	
	/* Partition by Index */
	public <T> List<T> getByIndices(List<T> list, List<Integer> indexes) {		
	    return indexes.stream().map(list::get).collect(Collectors.toList());	    
	}
}