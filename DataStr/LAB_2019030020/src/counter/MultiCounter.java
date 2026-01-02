package counter;

public class MultiCounter {

	private static int[] counters = new int[10];


	/**
	 * Resets the internal counter of counterIndex to zero
	 */
	public  void resetCounter(int counterIndex) {
		if (counterIndex-1 < counters.length)
			counters[counterIndex-1] = 0;
}
	public  int getCount(int counterIndex) {
		if (counterIndex-1 < counters.length)
			return counters[counterIndex-1];
		return -1;
	}

	/**
	 * Increases the current count of counterIndex by 1. Returns always true so that it can be used
	 * in boolean statements
	 * 
	 * @return always true
	 */
	public boolean increaseCounter(int counterIndex) {
		if (counterIndex-1 < counters.length)
			counters[counterIndex-1]++;
		return true;
	}

	/**
	 * Increases the current count of counter given by counterIndex by step. Returns always true so that it can be
	 * used in boolean statements. Step could be negative. It is up to the specific
	 * usage scenario whether this is desirable or not.
	 * 
	 * @param step The amount to increase the counter
	 * @return always true
	 */
	public  boolean increaseCounter(int counterIndex, int step) {
		if (counterIndex-1 < counters.length)
			counters[counterIndex] = counters[counterIndex-1] + step;
		return true;
	}
}
