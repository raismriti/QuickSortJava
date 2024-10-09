package project5;

//Name: Smriti Rai
//Professor: Shruthi Chappidi
//Class: CS3345.005
//Due: 5/8/2021

import java.util.*;
import java.io.*;
import java.time.*;

// beginning of main class
public class Main 
{
	public static void main(String[] args) 
	{
		// should only take 4 command line arguments 
		if (args.length == 4) 
		{
			// try and catch method
			try 
			{
				// new file with arguments 
				File firstFile = new File(args[1]);
				File secondFile = new File(args[2]);
				File thirdFile = new File(args[3]);
				
				// create new files 
				firstFile.createNewFile();
				secondFile.createNewFile();
				thirdFile.createNewFile();
				
				PrintWriter report = new PrintWriter(firstFile);
				PrintWriter unsorted = new PrintWriter(secondFile);
				PrintWriter sorted = new PrintWriter(thirdFile);
				
				// apply parseInt 
				int size = Integer.parseInt(args[0]);
				
				ArrayList<Integer> list = QuickSorter.generateRandomList(size);
				
				unsorted.println(list);
				unsorted.close();
				
				// possible for 4 arguments 
				ArrayList<Integer> copy1 = new ArrayList<Integer>(list);
				ArrayList<Integer> copy2 = new ArrayList<Integer>(list);
				ArrayList<Integer> copy3 = new ArrayList<Integer>(list);
				ArrayList<Integer> copy4 = new ArrayList<Integer>(list);
				
				Duration first_element, random_element, median_of_three_elements, median_of_three_random_elements;
				
				first_element = QuickSorter.timedQuickSort(copy1, QuickSorter.PivotStrategy.FIRST_ELEMENT);
				
				random_element = QuickSorter.timedQuickSort(copy2, QuickSorter.PivotStrategy.RANDOM_ELEMENT);
				
				// apply the methods from the QuickSorter method 
				median_of_three_random_elements = QuickSorter.timedQuickSort(copy3,QuickSorter.PivotStrategy.MEDIAN_OF_THREE_RANDOM_ELEMENTS);
				median_of_three_elements = QuickSorter.timedQuickSort(copy4, QuickSorter.PivotStrategy.MEDIAN_OF_THREE_ELEMENTS);
				
				sorted.println(copy1);
				sorted.close();
				
				// print final report 
				report.println("Array Size = " + size);
				report.println("FIRST_ELEMENT : " + first_element);
				report.println("RANDOM_ELEMENT : " + random_element);
				report.println("MEDIAN_OF_THREE_RANDOM _ELEMENTS : " + median_of_three_random_elements);
				report.println("MEDIAN_OF_THREE_ELEMENTS : " + median_of_three_elements);
				
				//closeFile 
				report.close();
			}
			// throw an error for any issues when creating file 
			catch (Exception ex) 
			{
				System.out.println("Error! Cannot create file.");
			}
		} 
		else 
		{
			System.out.println("Error! Invalid Input.");
		}
	}
} // end of main class