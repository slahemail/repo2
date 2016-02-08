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
public interface Partition <T>{	
	/* List Partition */
	public List<List<T>> partitionOldWay(List<T> inList, int segment) ;  // jdk <= 8
	public Collector<T, List<List<T>>, List<List<T>>> streamPartition(int sep);  // jdk = 8 
	
	/* Partition of a List by Index */
	public <T> List<T> getByIndices(List<T> list, List<Integer> indexes);	
}