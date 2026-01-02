package main;

public class Generate {
	public int[] generateRandomInts() {
		 int  START_INT = 1;
		 int END_INT = 1000001;
		 int NO_OF_ELEMENTS = 100000;
		 java.util.Random randomGenerator = new java.util.Random();
		 int[] randomInts = randomGenerator.ints(START_INT, END_INT).distinct().limit(NO_OF_ELEMENTS).toArray();
		 return randomInts;
	}
}
