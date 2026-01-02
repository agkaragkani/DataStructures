package interfaces;

/**
 * It is public interfase that is implemented by ArrayBST and ThreadedBST
 * @author Αλεξανδρα
 *
 */

public interface BinarySearchTree {
	public void insert(int key);
	public int find(int root,int key);			
	public void printRange(int root,int low, int high);

}
