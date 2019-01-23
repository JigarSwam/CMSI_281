package tree.heap;

import java.util.ArrayList;

class BinaryHeap {

	ArrayList<Integer> heap;

	BinaryHeap() {
		heap = new ArrayList<Integer>();
	}

	/*
	 * Continues to bubble values up the tree until we find a node that is greater
	 * than it
	 */

	public void bubbleUp(int index) {
		if (index == 0) {
			return;
		}

		int parent = getParent(index);

		if (heap.get(parent) < heap.get(index)) {
			Integer temp = heap.get(index);
			heap.set(index, heap.get(parent));
			heap.set(parent, temp);
			bubbleUp(parent);
		}

	}

	public void bubbleDown(int index) {
		int childL = getChild(index, 'L');
		int childR = getChild(index, 'R');
		if (childL > heap.size() - 1 && childR > heap.size() - 1) {
			return;
		}
		int largeChild;
		if (childR > heap.size() - 1) {
			largeChild = childL;
		} else {
			largeChild = heap.get(childL) > heap.get(childR) ? childL : childR;
		}

		if (heap.get(largeChild) > heap.get(index)) {
			Integer temp = heap.get(index);
			heap.set(index, heap.get(largeChild));
			heap.set(largeChild, temp);
			bubbleDown(largeChild);
		}

	}

	public void insert(Integer toInsert) {
		heap.add(toInsert);
		bubbleUp(heap.size() - 1);
	}

	// Traversal helpers
	public int getParent(int index) {
		return (index - 1) / 2;
	}

	public int getChild(int index, char child) {
		int result = (index * 2) + 1;
		if (child == 'R') {
			result++;
		}
		return result;
	}

	public void print() {
		for (int i = 0; i < heap.size(); i++) {
			System.out.println(heap.get(i));
		}
	}

	public ArrayList<Integer> getSortedElements() {
		ArrayList<Integer> original = heap;
		ArrayList<Integer> clone = (ArrayList<Integer>) heap.clone();
		ArrayList<Integer> solution = new ArrayList<Integer>();
		heap = clone;
		int count = clone.size() - 1;
		while (count >= 0) {
			System.out.println(clone);
			solution.add(0, clone.get(0));
			clone.remove(0);
			System.out.println(clone);
			bubbleDown(0);
			count--;
		}
		heap = original;
		return solution;
	}

	public static void main(String[] args) {
		BinaryHeap max = new BinaryHeap();
		max.insert(20);
		max.insert(10);
		max.insert(30);
		max.insert(50);
		max.insert(60);
		max.print();
		System.out.print(max.getSortedElements());
	}

}