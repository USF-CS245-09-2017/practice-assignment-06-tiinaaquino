import java.util.ArrayList;

public class BinaryHeap {
	private int[] arr = new int[10];
	private int size;

	// constructor
	public BinaryHeap() {
		size = 0;
	}
	
	/**
	 * adds the element to the heap
	 * @param priority
	 */
	public void add(int priority) {
		if (arr.length == size) {
			grow_heap();
		}
		arr[size] = priority;
		int index = size;
		while ((index > 0) && arr[index] < arr[parent(index)]) {
			swap(index, parent(index));
			index = parent(index);
		}
		size++;
	}
	
	/**
	 * grows the heap
	 */
	public void grow_heap() {
		int newSize = arr.length * 2;
		int[] newArr = new int[newSize];
		for (int i = 0; i < size; i++) {
			newArr[i] = arr[i];
		}
		arr = newArr;
	}
	
	/**
	 * swaps the given elements in the array
	 * @param i
	 * @param j
	 */
	public void swap(int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	/**
	 * @param index
	 * @return
	 * 		the parent
	 */
	public int parent(int index) {
		return (index - 1) / 2;
	}
	
	/**
	 * @param index
	 * @return
	 * 		the left child
	 */
	public int left_child(int index) {
		return (index * 2) + 1;
	}
	
	/**
	 * @param index
	 * @return
	 * 		the right child
	 */
	public int right_child(int index) {
		return (index * 2) + 2;
	}
	
	/**
	 * compares the child and its left
	 * @param index
	 * @return
	 * 		the smaller child
	 */
	public int smaller_child(int index) {
		int child = left_child(index);
		int right = right_child(index);
		
		if (arr[right] < arr[child]) {
			child = right;
		}
		return child;
	}
	
	/**
	 * removes the priority
	 * @return
	 * 		the element removed
	 */
	public int remove() {
		int removed = arr[0];
		if (size == 0) {
			System.out.println("Array is empty.");
			System.exit(1);
		}
		arr[0] = arr[size-1];
		size--;
		int index = 0;
		int child = left_child(index);
		
		if (left_child(index) < size) {
			child = smaller_child(index);
		}
		
		while(child < size && arr[child] < arr[index]) {
			swap(index, child);
			index = child;
			
			if (left_child(index) < size) {
				child = smaller_child(index);
			}
		}
		return removed;
	}
	
}