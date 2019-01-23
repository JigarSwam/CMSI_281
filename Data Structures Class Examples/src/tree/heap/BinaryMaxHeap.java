package tree.heap;

import java.util.ArrayList;

public class BinaryMaxHeap {
	
	//Constructor
	BinaryMaxHeap () {
		heap = new ArrayList<Integer>();
	}
	
	// Fields
	ArrayList<Integer> heap;
	
	// Methods
	public void add (Integer toInsert) {
		// Start by adding to end of ArrayList
		// (deepest, right most child)
		heap.add(toInsert);
		
		// TODO: Check that we're left with
		// a heap
		bubbleUp(heap.size() - 1);
	}
	
	// Helper methods
	
	private void bubbleUp (int index) {
		// Simple case: at the root:
		if (index == 0) { return; }
		
		// Otherwise, check parent:
		int parent = getParent(index);
		
		// Case: need to bubble up
		if (heap.get(parent) < heap.get(index)) {
			Integer temp = heap.get(index);
			heap.set(index, heap.get(parent));
			heap.set(parent, temp);
			// [!] Then, check again:
			bubbleUp(parent);
		}
	}
	
	public int getParent (int index) {
		return (index - 1) / 2;
	}
	
	public int getChild (int index, char child) {
		int result = (index * 2) + 1;
		if (child == 'R') {
			result++;
		}
		return result;
	}
	
	public void print () {
		System.out.println(heap);
	}
	
	public static void main (String [] args) {
		BinaryMaxHeap max = new BinaryMaxHeap();
		max.add(20);
		max.add(10);
		max.add(30);
		max.add(50);
		max.add(60);
		max.print();
	}

}
