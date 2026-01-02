package bstArray;
import interfaces.BinarySearchTree;
import main.Generate;
import java.util.Random;
import java.util.Vector;

import counter.MultiCounter;
/**
 * This class represents a binary search tree in a 3-dimensional array form.
 * The tree's elements (keys,right,left) are stored in one line each.
 * In the first line there are the keys of the tree,in the second line there are the values that show in
 * which position of the array the left child is located  and at the third line there are  those who indicate
 * the left child and are produced by the available positions.
 * The right line in other words , also represents a stack that stores all the available positions
 * of the array.Its function in to let as know,in the event of insertion, in which position the 
 * node can be placed.
 * 
 * counter(1) is used to this class
 * @author Αλεξανδρα
 *
 */

public class ArrayBST implements BinarySearchTree {
		
	/**
	 * An int that shows in which position of the array the BST's root is.
	 */
		private int root;
	/**
	 * An int that works as a pointer and indicates the next available position of the stack
	 */
		private int avail;
	/**
	 * An int that shows the size of the array.
	 */
		private int size;
	/**
	 * A two dimensional array in which all the BST's values are stored including the avail-stack.In the first
	 * line keys are stored, in the second the position of the left child ,in the third there is the position
	 * of the right child and the avail-stack.
	 */
		private int[][] data;
	/**
	 * An instance of the class multicounter used for counting disk accesses. 
	 */
		MultiCounter c = new MultiCounter();
	/**
	 * A Vector of integers that contains the elements of the BST in a sorted manner.
	 */
		private Vector<Integer> sortedVector;	
		
		
	/**
	 * BST's class constructor with argument the size of the array.
	 * @param N
	 */
		public ArrayBST(int N) {
			this.size = N;									// assign the class variable size to the local variable N
			this.avail = 0;									// initialize stack's pointer to zero
			this.root = -1;									// set arrays root to -1(null) to show that the BST has no elements yet
			sortedVector = new Vector<Integer>(size);		// initialize vector to the size of the array
			this.data = new int[size][3];					// assign the constructor's argument to the array's size
			
			for(int i=0; i < size-1; i++) {					// initialization of the stack so as to contain all possible
				data[i][2] = i+1;							// positions of the array
			}
			
			data[size-1][2] = -1;							// the last element of the avail-stack is null 
		} 
		
		
		
	/**
	 * constructor that initializes root to null
	 */
		public ArrayBST() {
			root = -1;										
		} 
		
		
	
		
	/**
	 * Method that returns the stack's next available position for insertion.
	 * In every variable assignment we increase the counter to indicate that
	 * the disk has been accessed.
	 * @return the position that available node was found
	 */
		private int getNode() {
																	// checks the case that there is no available position
																	// and so the BST is full
			if(c.increaseCounter(1) && avail == -1) {				// if condition returns true and avail =-1 method
				return -1;											// returns -1
			}
			
																	
																	// in other case ,if the BST is not full, we store
																	// the next available position in  variable position
			int position = avail;		
			c.increaseCounter(1);									
			
																	// move the avail to the next available position
			avail = data[position][2];
			c.increaseCounter(1);
			
																	// return the available position
			return position;
		}
		
		
	/**
	 * insert method is implemented from BinarySearchTree interface
	 * and it assigns the return value of insertKey to variable root 
	 */
		@Override
		public void insert(int key) {
	         root = insertKey(root, key);
	         
	    }
		
	/**
	 * insertKey method is basically examining all the possible
	 * scenarios for insertions. Specifically, firstly it checks
	 * if there are any existing keys in the BST ,if not it inserts
	 * the first node and sets it as the root.The next time it's 
	 * called ,obviously root isn't null,so it checks if the key we
	 * are inserting is lesser or greater than the root and inserts 
	 * accordingly.Now if the key is to be inserted after a non leaf
	 * key the function insert key is recursively called until the
	 * key is correctly positioned.
	 * Lastly, in every variable assignment we increase the counter and in every if,if else and while
	 * statement as,well.
	 *
	 * NOTE : in if statements increaseCounter returns always true!   

	 * @param root
	 * @param key
	 * @return the root of BST
	 */
		
		public int insertKey(int root, int key) {
																	// nextAvail is local variable that shows next available position for  
																	// insertion
				int cur;											// cur is a local variable that is used to indicate the current 
				int nextAvail;										// position of the array
				// if the BST_ARRAY is empty
				if( c.increaseCounter(1) && (root == -1) ) {
					
				nextAvail = getNode();							// nextAvail contains the available position of the BST, in which
				c.increaseCounter(1);							// the key will be inserted as a leaf due to the non-existence 
							// of other keys.
				root = nextAvail;   							// root will now contain this position in the array 
				c.increaseCounter(1);
				
				data[nextAvail][0] = key;						// key insertion
				c.increaseCounter(1);
				
				data[nextAvail][1] = -1;		                // left and right child don't point anywhere in the BST because  
				c.increaseCounter(1);							// the inserted node is a leaf
				
				data[nextAvail][2] = -1;		
				c.increaseCounter(1);
				
				return root;
				
				
				}
				
				else {												// in this case there are existing nodes in the array, so that
																	// creates the need for its traverse
				
				cur = root;											// start searching from BST's root 
				c.increaseCounter(1);
				
				     
				if((c.increaseCounter(1) && key < data[cur][0]) && (c.increaseCounter(1) && data[cur][1] == -1))  {					// first case in which the key to be inserted is lesser then the current key
																																	// and the current key's left child points nowhere.
				nextAvail = getNode();																								// get next available position
				c.increaseCounter(1);
				
				
				if(c.increaseCounter(1) && nextAvail != -1 ) {			// if there is one, insert the node , else end		
																		// insertion
				data[cur][1] = nextAvail;								// current postion's left child is now pointing 
				c.increaseCounter(1);									// to the next available position
				
				data[nextAvail][0] = key;								// info of the next available position is equal 
				c.increaseCounter(1);									// to the key
				
				data[nextAvail][1] = -1;								// left and right child don't point anywhere in the BST because the 
				c.increaseCounter(1);									// the BST because the inserted node is a leaf
				
				data[nextAvail][2] = -1;	
				c.increaseCounter(1);
				}
				
				return root;														// insertion is done, exit insert
				
				
				}else if(c.increaseCounter(1) && key < data[cur][0] ){	// second case in which the key to be inserted is lesser than the current key		
				
				c.increaseCounter(1,2);									// but the current key's left child points somewhere.
				cur = data[cur][1];
				insertKey(cur, key);									// recursive call for traversing the tree
																		// cur points to the left child of current position and while loop
				
				
				}
				else if( (c.increaseCounter(1) && key > data[cur][0] ) && (c.increaseCounter(1) && data[cur][2] == -1) ) {			// third case in which the key to be inserted is greater than the current key
																																	// and the current key's right child points nowhere.
				
				
				nextAvail = getNode();									// same exact process as the previous if
				c.increaseCounter(1);
				
				if(c.increaseCounter(1) && nextAvail != -1) {
				
				data[cur][2] = nextAvail;
				c.increaseCounter(1);
				
				data[nextAvail][0] = key;
				c.increaseCounter(1);
				
				data[nextAvail][1] = -1;	
				c.increaseCounter(1);
				
				data[nextAvail][2] = -1;	
				c.increaseCounter(1);
				}
				
				return root;	
				}
				
				else {													// fourth case in which the key to be inserted is greater than the current key		
																		// but the current key's right child points somewhere.
				c.increaseCounter(1,2);									// recursive call for traversing the tree
				cur = data[cur][2];	
				insertKey(cur, key);									// cur points to the right child of current position
											
				}
				
				}
				return root;
				}
		

			
	@Override
	/**
	 * find is an implemented method from the BinarySearchTree interface
	 * that is the main mechanism to search for a key in a BST. Firstly,
	 * it checks if the BST is empty,then if its not it checks if key is
	 * in the root of the tree. If those conditions are false it checks 
	 * if the key is lesser/greater than the root's key and if the root 
	 * has a right/left child ,if yes key coulden't be found, if not
	 * a recursive call is made having as first argument the BST's 
	 * current position of right/left child.
	 */
	public int find(int root, int key) {
		// TODO Auto-generated method stub
			
			    // if the BST_ARRAY is empty
			    
				if( c.increaseCounter(1) && (root == -1) ) {				
				System.out.println("Sorry, the BST is empty there are no keys to search...");				
				return -1;
				}
				
				 int cur = root;											// start searching from the root of BST, this is indicated by assigning root 
				 c.increaseCounter(1);										// to local variable of current position
				 
				if( c.increaseCounter(1) && (data[cur][0] == key) ) {		// if the key of the current position is the same with the given key			
					System.out.println("The key "+key+" was  found...");		// print key was found.		
					return cur;
					}
				

				if((c.increaseCounter(1) && (key < data[cur][0])) && (c.increaseCounter(1) && (data[cur][1] == -1))) {  	// if the key we are searching is lesser then the current key, 
																															// but the current key's left child is null ,the key is nowhere to be found.
					
				//System.out.println("The "+key+" was not found...");
				return cur;													// return current position

				}
				
				if((c.increaseCounter(1) && (key > data[cur][0])) && (c.increaseCounter(1) && (data[cur][2] == -1))) {		// if the key we are searching is greater than the current key, 
																															// but the current key's right child is null ,the key is nowhere to be found.

				//System.out.println("The "+key+" was not found...");
				return cur;						

}
				//else {														// else if the current key's left child is not a null
				
				if(c.increaseCounter(1) && (key < data[cur][0]) && c.increaseCounter(1) && (data[cur][1] != -1) )  {			
					
					//cur = data[cur][1];	
					//c.increaseCounter(2,2);									// the new current position is the current position's left child
					return find( data[cur][1], key);									// there is need for traversing the tree so we use recursion
					
				}

				
				
					
					if(c.increaseCounter(2) && (key > data[cur][0]) && c.increaseCounter(2) && (data[cur][2] != -1) )  {			// else if the current key's right child is not a null
						
						c.increaseCounter(2,2);
						cur = data[cur][2];											// the new current position is the current position's left child
						//return find(cur, key);										// there is need for traversing the tree so we use recursion
									
					}
					return find(cur, key);	
					
	}
	
	
				
/** 
 * 	printRange is used for finding the keys inside the 
 *  asked range and counting the disk accesses executed	
 *  for that purpose	
 */

		@Override
		public void printRange(int root,int low, int high) {
			// TODO Auto-generated method stub
			int pos = 0;		
			
			if(c.increaseCounter(1) && (root == -1) ) {				// if root is null then stop range
				return;
			}
			
			if (c.increaseCounter(1) && (data[root][0] > low) ) {	// if the key of root pointer larger than min then recurse,with root now equal to root's left child
				pos = data[root][1];
				c.increaseCounter(1);			
				printRange(pos , low, high);
			}
			if( c.increaseCounter(1) && (data[root][0] < high) ) {	// if the key of root pointer is less than max thenrecursee, with root now equal to root's right child
				pos = data[root][2];
				c.increaseCounter(1);
//				
				printRange(pos, low, high);}
			
			if( ( c.increaseCounter(1) && (data[root][0] >= low) ) && ( c.increaseCounter(1) && (data[root][0] <= high) ) ) {		// if the key of root pointer is between min and max ,
				//System.out.println(data[root][0]);																				// key is inside the range
			}

			
		}
		
/**
* printRandomSearches method is used for searching 
* for random integers in the array and printing the
* the average number of comparisons per random search		
* @param searches
*/
			
	public void searchRandom(int searches) {
		c.resetCounter(1);
					
		Generate gen = new Generate();
		int integers[] = gen.generateRandomInts();
					
		System.out.println("Search " + searches + " random integers ....");
				
				for(int i=0; i<searches; i++) {
					find(root,integers[i]);

					}
					
		System.out.println("The average number of comparisons per random search  in the binary search tree (array) is " + c.getCount(1)/searches);
				}
				
		
/**
 *  searchingRange method creates a random range and the calls
 *  printRange so us to find the keys in that range.It also
 *  prints the average disk accesses it takes to execute.
 * @param range
 * @param searches
 */
		
		public void searchingRange(int range, int searches) {
			c.resetCounter(1);
			
			Random randomGenerator = new Random();
			
			int kMin = 0;
			int kMax = 0;
			
			System.out.println("Execute " + searches + " range searches....");
			
			for(int i=0; i<searches; i++) {
				kMin = randomGenerator.nextInt(1000000); 
				kMax = kMin + range;
				
				printRange(root,kMin, kMax);
			}
			System.out.println("Range is "+"(" + range + ")");
			System.out.println("The average number of comparisons per random range in the binary search tree (array) is " + c.getCount(1) /searches);
		}
		
		
		/**
		 * insertInfo method is used to insert nodes, in other words
		 * keys form an integer array into the BST , by calling helper method insert (see
		 * later on in the class BstArray) ,and also present the average 
		 * number of comparisons per insertion.
		 * @param info
		 */
			public void insertInfo(int[] info) {
				c.resetCounter(1);
				
				System.out.println("Inserting  keys in the binary search tree (array)....");
				
				for(int i=0; i<info.length; i++) {
					insert(info[i]);												// call insert method
				}
				System.out.println("The average number of comparisons per insertion in the binary search tree (array) is " + c.getCount(1)/size);
			}


		

		
		

}
