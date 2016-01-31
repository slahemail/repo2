package com.ad.partition;

import java.util.List;
import java.util.stream.Collector;

/**
 * Partition Interface - List Partition tools. 
 * 
 * @version 1.0
 * @author MGA
 * @date   07/02/2016
 * 
 */
public interface Partition {
	public Collector<String, List<List<String>>, List<List<String>>> streamPartition(int sep);
	public List<List<Integer>> partitionOldWay(List<Integer> inList, int segment) ;
	public <T> List<T> getByIndices(List<T> list, List<Integer> indexes);	
}
