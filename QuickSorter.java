package project5;

// Name: Smriti Rai
// Professor: Shruthi Chappidi
// Class: CS3345.005
// Due: 5/8/2021

import java.time.*;
import java.util.*;

// static utility class 
// beginning of public class
public class QuickSorter {
	private QuickSorter() {}

	public static <E extends Comparable<E>> Duration timedQuickSort(ArrayList<E> list, PivotStrategy strat)
			throws NullPointerException
	{
		if (list.isEmpty() || strat == null) 
		{
			throw new NullPointerException("Invalid Argument");
		}
		// actual sort itself using java.lang.System.
		long start = System.nanoTime();
		    // first element as pivot
		if (strat == PivotStrategy.FIRST_ELEMENT) 
		{
			quickSort_first(list, 0, list.size() - 1);
			// randomly choosing the pivot element 
		} else if (strat == PivotStrategy.RANDOM_ELEMENT) 
		{
			quickSort_random(list, 0, list.size() - 1);
			// choosing the median of 3 randomly chosen elements as the pivot
		} else if (strat == PivotStrategy.MEDIAN_OF_THREE_RANDOM_ELEMENTS) 
		{
			quickSort_medianOfRandomThree(list, 0, list.size() - 1);
			// median of first, center and last element 
		} else if (strat == PivotStrategy.MEDIAN_OF_THREE_ELEMENTS) 
		{
			quickSort_medianOfThree(list, 0, list.size() - 1);
		}
		
		// actual sort itself using java.lang.System.
		long finish = System.nanoTime();
		Duration elapsed = Duration.ofNanos(finish - start);
		return elapsed;
	}
	
	// enum -> enumeration 
	public static enum PivotStrategy {
		FIRST_ELEMENT, RANDOM_ELEMENT, MEDIAN_OF_THREE_RANDOM_ELEMENTS, MEDIAN_OF_THREE_ELEMENTS
	}

	// swap method
	// simple method which will swap values using temp
	private static <E extends Comparable<E>> void swap(ArrayList<E> list, int i, int j)
	{
		E temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
	}
	
	// quickSort_first
	private static <E extends Comparable<E>> void quickSort_first(ArrayList<E> list, int lowNum, int highNum) 
	{
		// checks to see if the low value is actually lower than the high value
		if (lowNum >= highNum) 
		{
			return;
		}
		// applies swap method to swap lowNum and highNum 
		swap(list, lowNum, highNum);
		int pivot = quickSort(list, lowNum, highNum);
		quickSort_first(list, lowNum, pivot - 1);
		quickSort_first(list, pivot + 1, highNum);
	}
	
	// quickSort
	private static <E extends Comparable<E>> int quickSort(ArrayList<E> list, int lowNum, int highNum) 
	{
		E pivotVal = list.get(highNum);
		int i = lowNum - 1;
		// apply swap method
		for (int j = lowNum; j < highNum; j++) 
		{
			if (list.get(j).compareTo(pivotVal) <= 0)
			{
				i++;
				// applies swap method to swap i and j value 
				swap(list, i, j);
			}
		}
		// applies swap method to swap i+1 and highNum  
		swap(list, i + 1, highNum);
		return i + 1;
	} 
	

	// if a value is nonnegative, then the following method will throw an  
	// IllegalArgumentException only exception being thrown 
	private static int rand(int minNum, int maxNum) throws IllegalArgumentException 
	{
		if (minNum > maxNum || (maxNum - minNum + 1 > Integer.MAX_VALUE)) 
		{
			throw new IllegalArgumentException("Invalid range");
		}

		// applying java.util.Random to generate integral values uniformly 
		// across the entire range of the int data type 
		return new Random().nextInt(maxNum - minNum + 1) + minNum;
	}

	// quickSort_random 
	// applies rand and swap methods 
	private static <E extends Comparable<E>> void quickSort_random(ArrayList<E> list, int lowNum, int highNum) 
	{
		// checks to see if the low value is actually lower than the high value
		if (lowNum >= highNum) 
		{
			return;
		}
		// applies rand method 
		int randomPivot = rand(lowNum, highNum);
		
		// applies swap method 
		swap(list, randomPivot, highNum);
		
		int pivot = quickSort(list, lowNum, highNum);
		quickSort_random(list, lowNum, pivot - 1);
		quickSort_random(list, pivot + 1, highNum);
	}

	// quickSort_medianOfRandomThree
	// applies rand method to get three random ints 
	private static <E extends Comparable<E>> void quickSort_medianOfRandomThree(ArrayList<E> list, int lowNum, int highNum)
	{
		if (lowNum >= highNum) 
		{
			return;
		}
		
		// assign 3 random ints with a low and high num
		int randA = rand(lowNum, highNum);
		int randB = rand(lowNum, highNum);
		int randC = rand(lowNum, highNum);
		
		int medianRandomPivot = median(list, randA, randB, randC);
		
		if (medianRandomPivot != highNum) 
		{
			// applies swap method 
			swap(list, medianRandomPivot, highNum);
		}
		int pivot = quickSort(list, lowNum, highNum);
		quickSort_medianOfRandomThree(list, lowNum, pivot - 1);
		quickSort_medianOfRandomThree(list, pivot + 1, highNum);
	}

	// quickSort_medianOfThree
	// finds the median of first, center and last element 
	private static <E extends Comparable<E>> void quickSort_medianOfThree(ArrayList<E> list, int lowNum, int highNum) 
	{
		if (lowNum >= highNum)
		{
			return;
		}
		int medianThreePivot = median(list, lowNum, highNum, (lowNum + highNum) / 2);
		if (medianThreePivot != highNum)
		{
			// applies swap method 
			swap(list, medianThreePivot, highNum);
		}
		int pivot = quickSort(list, lowNum, highNum);
		quickSort_medianOfThree(list, lowNum, pivot - 1);
		quickSort_medianOfThree(list, pivot + 1, highNum);
	}

	// median
	// this method will compare the ints and find the middle value 
	private static <E extends Comparable<E>> int median(ArrayList<E> list, int a, int b, int c) 
	{
		if (list.get(a).compareTo(list.get(b)) > 0)
		{
			if (list.get(b).compareTo(list.get(c)) > 0)
			{
				return b;
			} else if (list.get(a).compareTo(list.get(c)) > 0)
			{
				return c;
			} else {
				return a;
			}
		} else {
			if (list.get(a).compareTo(list.get(c)) > 0)
			{
				return a;
			} else if (list.get(b).compareTo(list.get(c)) > 0)
			{
				return c;
			} else {
				return b;
			}
		}
	}

	// generates and returns a new integer array-based list with the given size 
	// that consists of random and unsorted values
	public static ArrayList<Integer> generateRandomList(int size) {
		// given size should be nonnegative
		ArrayList<Integer> list = new ArrayList<Integer>(size);
		Random random = new Random();
		for (int i = 0; i < size; i++) {
			int num = random.nextInt();
			list.add(num);
		}
		return list;
	}
} // end of public class 
