package bstTreaded;

import java.util.Random;


import counter.MultiCounter;
import interfaces.BinarySearchTree;
import main.Generate;
/**
 * ThreadedBST class represents a binary search tree in a 5-dimensional array form.
 * The tree's elements (keys,right,left,left thread, right thread) are stored in one line each.
 * The first tree lines are identical to the ArrayBst, but in this case there are two more
 * lines type boolean that indicate if the spesific nose is threading or not.This is really 
 * helpful with the tree traversal.
 * 
 * counter(2) is used for disk accesses
 * 
 * @author Αλεξανδρα
 *
 */

public class ThreadedBST implements BinarySearchTree {
/**
 * 5 dimensional array that functions as the BST threaded tree
 */
	private int[][] data;
/**
 * static final value that is interpreted as false
 */
	final static int fal = -3;
/**
 * static final value that is interpreted as true
 */
	final static int tru = -2;
/**
 * instance if class multicounter used for counting disk accesses
 */
	MultiCounter c = new MultiCounter();
/**
 * integer that shows in which position of the array the BST's root is
 */
	private int root;
/**
 * integer that shows the size of the array
 */
	private int size;
/**
 * integer that works as a pointer and indicates the next available position of the stack
 */
	private int avail;
	
/**
 * thraded BST's class constructor with argument the size of the array
 * @param size
 */
	public ThreadedBST (int size) {
		
		this.size = size;
		this.data = new int[size][5];				//Initialize 5 dimensional integer [] array.
		avail = 0;									// when the BST_ARRAY is created the first available position is 0
		root = -1;									// root is equal to -1 because there is no root when the BST_ARRAY is created
	
		for(int i=0; i < size; i++) {
			data[i][0] = -1;
			data[i][1] = -1;
			data[i][3] = tru;
			data[i][2] = i + 1;
			data[size - 1][2] = -1;
			data[i][4] = tru;
			}		
		
		} 
	/**
	 Method that returns the stack's next available position for insertion.
	 * In every variable assignment we increase the counter to indicate that
	 * the disk has been accessed.
	 * @return the position that available node was found
	 */
	
	private int getNode() {
																// checks the case that there is no available position
																// and so the BST is full
		if(c.increaseCounter(2) && avail == -1) {				// if condition returns true and avail =-1 method
			return -1;											// return -1
		}
		
																// in other case ,if the BST is not full, we store
																// the next available position in  variable position
		int position= avail;									
		c.increaseCounter(2);
		
																// move the avail to the next available position
		avail = data[position][2];
		c.increaseCounter(2);
		
																// return the available position
		return position;
	}
	
	
/**
 * findNext method is used for traversing through a threaded BST
 * so us to find in an in-order way (acceding) the next bigger 
 * key of the BST
 * @param current
 * @return
 */
	private int  findNext(int current) 
	{
		  if (c.increaseCounter(2) && data[current][4] == tru) {
			  return data[current][2];								// if the right pointer of the node in question
			}
		  															// is threading , then return its right child
	    current = data[current][2];									// new current pointer is the current's right child
	    c.increaseCounter(2);
	    															// while the left pointer of the node in question
	    while (c.increaseCounter(2) && data[current][3] == fal) {	// is not threading , then if current pointer 
	    	if (c.increaseCounter(2) && current!=-1 ) {
	        current = data[current][1];								// is not null ,new current pointer is the current's
	        c.increaseCounter(2);									// left child
	    }
	    	
	    }
	    return current;
	}
/**
 * same function with findNext ,but this time it finds the previous
 * @param current current position
 * @return current
 */
	private int findPrev(int current) {
		
		if (c.increaseCounter(2) && data[current][3]==tru){		// if the left pointer of the node in question
																// is threading , then return its left child
			return data[current][1];
		}
		current= data[current][1];
		c.increaseCounter(2);															// new current pointer is the current's left child

		while (c.increaseCounter(2) && data[current][4]==fal) {	// while the right pointer of the node in question
			c.increaseCounter(2);								// is not threading , then new current pointer is the current's 
			current = data[current][2];							// the current's right child
		}
		return current;
	}
		

/**
 * insert is an overwritten method implemented by BST interface.
 * Its argument is the key to be inserted, and it functions by
 * assigning the return value of method insertKey to the root of
 * the threaded tree.
 * 
 */
	@Override
	public void insert(int key) {
		// TODO Auto-generated method stub
		root = insertKey(root, key);
	

	}
/**
 * method insertKey is basically the function that performs the
 * insertion.This specific method is , to a degree , based to
 * a code that was shown to us during the frontistirios
 * (https://www.geeksforgeeks.org/threaded-binary-tree-insertion/)
 * So like the BST array method, it firstly	finds next available 
 * position for insertion and it assigns it to local variable
 * avail. Now it's ready to start checking insertion conditions.
 * Firstly, if the root is null (-1) , the available position is 
 * the new root,root's key is has the value of the argument.In other
 * it enters a while loop that checks if key will be duplicated,
 * if key is lesser/greater than current position's key and current
 * node is a parent and terminates when current position is -1.Last
 * two conditions actually insert nodes if current position is -1
 * or the current node is a child.
 * 
 * Lastly, in every variable assignment we increase the counter and in every if,if else and while
	 * statement as,well.
	 *
	 * NOTE : in if statements increaseCounter returns always true!   

 * @param root BST's root
 * @param key value we want to insert
 * @return
 */
	public int insertKey(int root, int key) {
			
			int avail = getNode();										// get next available position for insertion
			if (c.increaseCounter(4) && root==-1) {						// check if BST already has elements
				
				root = avail; 											// if not the root of the BST is the available 
																		// position
				data[root][0]= key;										// root 's  info is the key value
				data[root][1]=-1;										// left/right point nowhere
				data[root][2]=-1;
				data[root][3]= fal;										// left/right thread are both false because this
				data[root][4]= fal;										// is a parent
				//System.out.println(data[root][2]);
				c.increaseCounter(4, 6);
				return root;
				}
			else {
				
				int current = root;										// current position is equal to the root
				int next = root;										// next position is equal to the root
				c.increaseCounter(2, 2);
				while(c.increaseCounter(2) && next != -1) {
					if (c.increaseCounter(2) && key == data[current][0]) {						// if current's position key is equal to the key
						System.out.printf("Duplicate Key !\n");									// to be inserted , stop insertion
			            return root;
					}
					current = next;																// set current equal to next
					c.increaseCounter(2);
					if (c.increaseCounter(2) && key<data[current][0]) {							// if key is lesser than the current key
						if(c.increaseCounter(2) && data[current][3]==fal) {						// and right thread is false,it points to 
						next = data[current][1];												// next key, set next equal to current position's
						c.increaseCounter(2);													// left child
						}else																	
							break;																// terminate while loop
						
					}else 
					{
						if(c.increaseCounter(2) && data[current][4] == fal) {					// if key is greater than the current key
						next = data[current][2];												// and left thread is false,it points to 
						c.increaseCounter(2);													// next key, set next equal to current position's
						}																		// right child
						else
							break;																// terminate while loop
						
					}
				}
					 if (c.increaseCounter(2) && key<data[current][0] ) {						// if key is lesser than the current key
						 if(c.increaseCounter(2) && next==-1 || c.increaseCounter(2) && data[current][3] == tru) {
							 																	// if next=-1 or left thread is true(it points 
							 																	// a previously inserted key with lesser value)
						 
						 data[avail][0]= key;													// available position 's  info is the key value
						 data[avail][1]= data[current][1];										// available position 's left child is equal to current  position 's left child
						 data[current][1]=avail;												// current position 's left child is equal to available
						 data[avail][2]= current;												// available position 's right child is equal to current
						 // data[avail][3] = data[current][3];								    available position 's left thread is equal to current  position 's left thread
						 data[current][3] = fal;												// current  position 's left thread is false
						 c.increaseCounter(2,5);
						 //System.out.println(data[current][2]);
						}
					 }
					else  if (c.increaseCounter(2) && key>data[current][0] ) {					// if key is greater than the current key
						  if(c.increaseCounter(2) && next==-1 || c.increaseCounter(2) && data[current][4] == tru) {		
							  																	// if next=-1 or right thread is true(it points 
							  																	// a previously inserted key with greater value)
							  
						  data[avail][0] = key;													// available position 's  info is the key value
						  data[avail][2] = data[current][2];									// available position 's right child is equal to current  position 's right child
						  data[current][2] = avail;												// current position 's right child is equal to available
						  data[avail][1] = current;												// available position 's left child is equal to current							
						  data[current][4] = fal;												// current  position 's right thread is false
						  c.increaseCounter(2,5);
						 // System.out.println(data[current][2]);
						  
						  }
					}
//				else  if(key <data[prev][0])  {       
//					data[q][0] = key;					//το καινουριο key πχ 14 μπαινει στο δεξι παιδι του 15,πριν μπει το δεξι του 15 εδειχνε στο αμεσωσ μικροτερο
//					data[q][1] = data[prev][1];     //οποτε τωρα το αριστερο του νεου κι 14 πρεπει να δειχνει εκει που εδειχνε πριν το αριστερο κι του 15
//					data[prev][1] = q;				//το αριστερο του 15 ισουται με q
//					data[prev][3] = fal;		//κσι επειδη το αριστερο του 15 δειχνει πια στο δεκατεσσερα το θρεντ δεν υπαρχει ειναι δηλαδη false
//					data[q][2] = prev;				//to deji paidi toy 14 =15
//				}
//				else if(key> data[prev][0]) { ///Η ΣΚΕΤΟ ELSE
//					data[q][0] = key;					//το καινουριο key πχ 14 μπαινει στο δεξι παιδι του 15,πριν μπει το δεξι του 15 εδειχνε στο αμεσωσ μικροτερο
//					data[q][2] = data[prev][1];     //οποτε τωρα το αριστερο του νεου κι 14 πρεπει να δειχνει εκει που εδειχνε πριν το αριστερο κι του 15
//					data[prev][2] = q;				//το αριστερο του 15 ισουται με q
//					data[prev][4] = fal;		//κσι επειδη το αριστερο του 15 δειχνει πια στο δεκατεσσερα το θρεντ δεν υπαρχει ειναι δηλαδη false
//					data[q][1] = prev;	
//				}
			}
			return root;
	}
/**
 * find is an overwritten method implemented from BST that 
 * searches in the threaded BST  for the argument key. 
 * counter 5 is responsible for random searches	
 */
	@Override
	public int find(int root, int key) {
		// TODO Auto-generated method stub
		 // if the BST_ARRAY is empty
	    
		if( c.increaseCounter(2) && (root == -1) ) {				
		System.out.println("Sorry, the BST is empty there are no keys to search...");				
		return -1;
			}
		
		 int cur = root;											// start searching from the root of BST, this is indicated by assigning root 
		// c.increaseCounter(2);										// to local variable of current position
		 
		if( c.increaseCounter(2) && (data[cur][0] == key) ) {		// if the key of the current position is the same with the given key			
			System.out.println("The key "+key+" was found...");		// print key was found.		
			return cur;
			}
		

		if((c.increaseCounter(2) && (key < data[cur][0])) && (c.increaseCounter(2) && (data[cur][1] == -1) || c.increaseCounter(2) && (data[cur][3] == tru))) {  	// if the key we are searching is lesser then the current key, 
																																									// but the current key's left child is null ,the key is nowhere to be found.
			
		//System.out.println("The "+key+" was not found...");
		return cur;																																					// return current position

		}else
			if( c.increaseCounter(2) && (key < data[cur][0]) && c.increaseCounter(2) && (data[cur][1] != -1) && c.increaseCounter(2) && (data[cur][3] == fal)  )  {			
																					// the new current position is the current position's left child
				return find(data[cur][1], key);										// there is need for traversing the tree so we use recursion
				
			}
		
		if((c.increaseCounter(5) && (key > data[cur][0])) && (c.increaseCounter(2) && (data[cur][2] == -1)  || c.increaseCounter(2) && (data[cur][4] == tru))) {		// if the key we are searching is greater than the current key, 
																																										// but the current key's right child is null ,the key is nowhere to be found.

			//System.out.println("The "+key+" was not found...");
			return cur;						

			}else 																	// else if the current key's left child is not a null

			
			if(c.increaseCounter(2) && (key > data[cur][0]) && c.increaseCounter(2) && (data[cur][2] != -1) && c.increaseCounter(2) && (data[cur][3] == fal) )  {			// else if the current key's right child is not a null
															// the new current position is the current position's left child
				return find( data[cur][2], key);								// there is need for traversing the tree so we use recursion
							
			}
			return cur;	
			
		
	}

	
/**
 * printRange is an overwritten method that is used for
 * the threaded traversal with aim to find all the keys that 
 * belong to the later given random range.It uses methods
 * findNext and findPrev that make use of the abilities
 * of the threaded tree to make a less costly traversal.	
 */
	@Override
	public void printRange(int root,int low, int high) {
		//int pre = 0;
				if (c.increaseCounter(2) && root == -1) 				// if root is null tree is empty, stop printRange
					return ;
				while (c.increaseCounter(2) && root != -1) {			
					if ((c.increaseCounter(2) && data[root][0] >= low &&c.increaseCounter(2) && data[(findPrev(root))][0] <= low)) 	// if pointer root's key is greater/equal than/to low
							break;																									// and its in order previous key is lesser/equal than/to low
							//pre = predecessor(root);																				// exit while loop
						//if (c.increaseCounter(6) && data[(findPrev(root))][0] <= kMin) 
								//break;
							/*if (c.increaseCounter(7) && data[root][3]==tru){							// in the comment section i present an alternative implementation,
								if (c.increaseCounter(7) && data[root][1]==-1) {						// where method find prev is inside printRange, although the range
									pre = root;															// was working it cost disk accesses, so different implementation
																										// was chosen!
									
								}
									
								pre = data[root][1];
								
							}
							
							root= data[root][1];

							while ( c.increaseCounter(7) && data[root][4]==fal) {
								c.increaseCounter(7);
								root = data[root][2];
							}
							
							pre = root;*/
						
						
						//pre = predecessor(root);
						//if (c.increaseCounter(7) && data[pre][0] <= kMin) 
						//break;
					//}*/
					if (c.increaseCounter(2) && data[root][0] < low) {															// if pointer root's key is less than low, new root is the 
						root = data[root][2];																					// previous root's right child
						c.increaseCounter(2);
					}
					else {
						root = data[root][1];	
						c.increaseCounter(2);																// in other case  new root is the  previous root's left child
					}
				}
																			
					//System.out.println(data[root][0]);																		   when while loop is terminated we print the in range key
					root = findNext(root);																						// and the procced to find its in order next
					c.increaseCounter(2);
				
					while ((c.increaseCounter(2) && root!=-1)&&(c.increaseCounter(2) && data[root][0]<=high)){					// while pointer root's key is less than high,print  the in
					  // System.out.println(data[root][0]);																		// order keys and set root to the next in order key
					   root = findNext(root);
					   c.increaseCounter(2);
				 }
				return ;
			
	
	}
	/**
	* insertInfo method is used to insert nodes, in other words
	* keys form an integer array into the BST , by calling helper method insert (see
	* later on in the class BstArray) ,and also present the average 
	* number of comparisons per insertion.
	* @param info
	*/
		public void insertInfo(int[] info) {
			c.resetCounter(2);
			System.out.println("Inserting keys in the binary search tree (threaded)....");
			
			for(int i=0; i<info.length; i++) {
				insert(info[i]);												// call insert method
			}
			System.out.println("The average number of comparisons per insertion in the binary search tree (threaded) is " +  c.getCount(2)/size);
		}	
		
		
	
	
	/**
	 * search random is called in order to print how many comparisons 
	 * have been made while searching for random integers
	 * @param searches number of searches executed
	 * @param integers array of integers used for the search
	 */
		public void searchRandom(int searches) {
			c.resetCounter(2);

			Generate gen = new Generate();
			int integers[] = gen.generateRandomInts();
			
			System.out.println("Search " + searches + " random integers....");
		
			for(int i=0; i<searches; i++) {
				find(root,integers[i]);

			}
			System.out.println("The average number of comparisons per random search  in the binary search tree (threaded) is " + c.getCount(2)/searches);
		}
		
		
		
		public void searchingRange(int range, int searches) {
			c.resetCounter(2);
			
			Random r = new Random();
			int min=0;
			int max=0;
			
			System.out.println("Execute " + searches + " range searches....");
			
			for(int i=0; i<searches; i++) {
				 min = r.nextInt(1000000)+1 ;	//1000000 is the bound, min must be less than 1000000	
				 max = min + range;  
				 
				printRange(root,min, max);
			
			}
			System.out.println("Range is "+"(" + range + ")");
			System.out.println("The average number of comparisons per random range in the binary search tree (threaded) is " + c.getCount(2) /searches);
		}



}

	
	
