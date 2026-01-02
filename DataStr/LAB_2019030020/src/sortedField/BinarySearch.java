package sortedField;

import java.util.Random;

import counter.MultiCounter;
import main.Generate;

/**
 * This class is consisted of an one dimensional integer array.
 * In this case we use binary search to find a key and which is
 * also applied to find the in range keys.
 * 
 * counter(3) is used for disk accesses
 * @author Αλεξανδρα
 *
 */

public class BinarySearch {
/**
 * an integer array that all the sorted keys are stored
 */
	
	private int data[];
	
	/**
	 * instance if class multicounter used for counting disk accesses
	 */
		MultiCounter c = new MultiCounter();

		/**
		 * Constructor. Given newData must be sorted!
		 * @param newData
		 */
		
	public BinarySearch(int newData[]) {
			this.data = newData;
		}


		/**
		 * Searches data array for given key.This is achieved with binary searching
		 * as shown to one of the frontistirios
		 * Returns the key if found, otherwise Integer.MIN_VALUE
		 * @param left
		 * @param right
		 * @param key
		 * @return key if found or Integer.MIN_VALUE otherwise
		 */
		
	    private int binarySearch(int left, int right, int key) 
	    { 
	        if (c.increaseCounter(3) && right >= left) { 
	            int mid = left + (right - left) / 2;			//find middle
	            c.increaseCounter(3);
	  
													            // if element at position middle is equal
													            // to the key return middle 
	            if (c.increaseCounter(3) && data[mid] == key) 
	                return mid; 
	  
	            												// if element at position middle is greater
													            // than the key ,recurse with right index equal
	            if (c.increaseCounter(3) && data[mid] > key)    // to mid-1
	                return binarySearch(left, mid - 1, key); 
	            	//c.increaseCounter(3);
	  
													            // the element can only be present 
													            // in right subarray,recurse with right index equal 
	            c.increaseCounter(3);							// left index equal to mid + 1
	            return binarySearch(mid + 1, right, key); 
	            
	        } 
	  
														        // the element couldnt be found
														        // we return Integer.MIN_VALUE in this case!
	        return Integer.MIN_VALUE; 
	    } 
		
/**
 * Search the array in order to find all the keys that are
 * inside the random range	
 * @param left
 * @param right
 * @param min
 * @param max
 */
		public void binaryRange(int left,int right,int min,int max) {
			int mid = 0;
			
			while(c.increaseCounter(3) && left<=right) {					// find middle of array
				 mid = left+(right-left)/2;
				
				if(c.increaseCounter(3) && data[mid]>min) {				// if middle's key is greater than min
					right = mid-1;;										// search in the left subtree
				}else if(c.increaseCounter(3) && data[mid]<min) {		// else if middle's key is lesser than min
																		// search in the right subtree
					left = mid + 1;
				}else {													// middle's key is equal to min, break
					//found
					break;
					
				}
				
	        }
			
			for(; mid < 100000 ; mid++) {								// as mid is less than data's length 
				if(c.increaseCounter(3) && data[mid] <= max) {			// and if middle's key is lesser or equal to max
				//System.out.println(data[mid]);						// key found
				}else													// stop if
				break;
					
			}
			
		}
		
		/**
		 * randomBinarySearch is used to find random keys in the integer array
		 * It also counts the average number of comparisons executed for this purpose	
		 * @param searches
		 */
				
				public void randomBinarySearch(int searches ) {
					c.resetCounter(3);
					
					Generate r = new Generate();
					int numbers[] = r.generateRandomInts();
					int left=0;
					int right=100000-1; 
					
					System.out.println("Search " + searches + " random integers....");
					
					for(int i=0;i<searches;i++) {
					int	pos=binarySearch(left,right, numbers[i]);
						if(c.increaseCounter(3) && pos != Integer.MIN_VALUE ) {
							if(c.increaseCounter(3) && numbers[i] == data[pos]) {
							
								System.out.println("Your key:"+numbers[i] +" was found");
							
						}
						}
					}
				    System.out.println("The average number of comparisons per random search  in sorted array is " + c.getCount(3)/searches);
				}
				
/**
 * Executing given number of range searches,by 
 * generating random ranges	and prints the
 * average number of comparisons per range search
 * 		
 * @param range
 * @param searches
 */
				public void printRange(int range,int searches) {
					c.resetCounter(3);
					
					Random r= new Random();
					System.out.println("Execute " + searches + " range searches....");
					
					int left=0;
					int right=100000-1; 
					for (int i =0; i<searches; i++) {
						int kMin = r.nextInt(1000000)+1;
						int kMax = kMin + range;
						
						binaryRange(left,right,kMin,kMax);	
					}
					System.out.println("Range is "+"(" + range + ")");
					System.out.println("The average number of comparisons per random range in the sorted array is " + c.getCount(3) /searches);
					
				}
				
				
		}
		

		
			
		
