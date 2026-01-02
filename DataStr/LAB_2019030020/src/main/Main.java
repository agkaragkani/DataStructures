package main;

import java.util.Arrays;

import sortedField.BinarySearch;
import bstArray.ArrayBST;
import bstTreaded.ThreadedBST;
//import bstThreaded.BST_Threaded;


public class Main {

	final static int maxNodes = 100000;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Generate r = new Generate(); 
		
		System.out.println("---------------------------------BST ARRAY--------------------------------");
		System.out.println();
		
		ArrayBST bstArray = new ArrayBST(maxNodes);
		int numbers[] = r.generateRandomInts();
		bstArray.insertInfo(numbers);
		bstArray.searchRandom(100);
		bstArray.searchingRange(1, 100);
		bstArray.searchingRange(100000, 100);
		//bstArray.randomSearch();
		/*
		// RANDOM VALUES TO DETERMINE IF BST RANGE WORKS
		bstArray.insert(8);
		bstArray.insert(6);
		bstArray.insert(5);
		bstArray.insert(7);
		bstArray.insert(10);
		bstArray.insert(9);
		bstArray.insert(15);
		bstArray.insert(29);
		bstArray.insert(17);
		bstArray.insert(1000000);
		bstArray.printRange(0, 6, 1000000);*/
		
		System.out.println();
		System.out.println("---------------------------------BST THREADED--------------------------------");
		
		
		ThreadedBST bst = new ThreadedBST(maxNodes);
		int integers[] = r.generateRandomInts();
		bst.insertInfo(integers);
		bst.searchRandom(100);
		bst.searchingRange(1, 100);
		bst.searchingRange(100000, 100);
		
	
	/* // RANDOM VALUES TO DETERMINE IF BST RANGE WORKS
		bst.insert(8);
		bst.insert(6);
		bst.insert(5);	
		bst.insert(7);
		bst.insert(10);
		bst.insert(9);
		bst.insert(11);
		bst.insert(15);
		bst.insert(29);
//		bst.insert(17);
		bst.insert(1000000);
		bst.findInRange(0, 6, 7);*/
		
		System.out.println();
		System.out.println("---------------------------------SORTED ARRAY--------------------------------");
	
		
 		int sortedInt[] = r.generateRandomInts();
		System.out.println();
		Arrays.sort(sortedInt);
		BinarySearch bs = new BinarySearch(sortedInt);
		bs.randomBinarySearch(100);
		bs.printRange(1,100);
		bs.printRange(100000, 100);
		
		 // RANDOM VALUES TO DETERMINE IF SORTED RANGE WORKS
		//int[] intArray = new int[]{ 1,2,3,4,5,6,7,8,9,10 }; 
		//BinarySearch bs = new BinarySearch(intArray);
     	//bs.binaryRange(0, 9, 2, 9);
		
	}
	
	
	



}
