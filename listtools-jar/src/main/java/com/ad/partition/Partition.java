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
	public Collector<T, List<List<T>>, List<List<T>>> streamListPartition(int sep);  // jdk = 8 
	public List<List<T>> listPartition(List<T> inList, int segment) ;  // jdk <= 8
	public String concatWithSep(List<List<T>> l);  
	
	/* Partition of a List by Index */
	public <T> List<T> getByIndices(List<T> list, List<Integer> indexes);	
}