// Luis Garcia & Jigar Swaminarayan
package intlist;

public class IntList {

    // Fields
    private int[] items;
    private int size;
    private static final int START_SIZE = 8;

    // Constructor
    IntList() {
        items = new int[START_SIZE];
        size = 0;
    }

    public int getAt(int index) {
        indexValidityCheck(index);
        return items[index];
    }

    public void append(int toAdd) {
        checkAndGrow();
        items[size] = toAdd;
        size++;
    }

    public void prepend(int toAdd) {
        checkAndGrow();
        shiftRight(0);
        items[0] = toAdd;
        size++;
    }

    public void insertAt(int toAdd, int index) {
    	indexValidityCheck(index);
        checkAndGrow();
        shiftRight(index);
        items[index] = toAdd;
        size++;
    }

    public void removeAll(int toRemove) {
        for (int i = 0; i < size; i++) {
        	indexValidityCheck(i);
            if (items[i] == toRemove) {
                shiftLeft(i);
                i--;
                size--;
            }
        }
    }

    public void removeAt(int index) {
        indexValidityCheck(index);
        shiftLeft(index);
        size--;
    }
    
    public String toString() {
    	String result = "";
    	for (int i = 0; i < size; i++) {
    		result += getAt(i);
    		if (i < size - 1) {
    			result += ",";
    		}
        }
    	return "[" + result + "]";
    }
    

    private void indexValidityCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /*
     * Expands the size of the list whenever it is at capacity
     */
    private void checkAndGrow() {
        // Case: big enough to fit another item, so no
        // need to grow
        if (size < items.length) {
            return;
        }

        // Case: we're at capacity and need to grow
        // Step 1: create new, bigger array; we'll
        // double the size of the old one
        int[] newItems = new int[items.length * 2];

        // Step 2: copy the items from the old array
        for (int i = 0; i < items.length; i++) {
            newItems[i] = items[i];
        }

        // Step 3: update IntList reference
        items = newItems;
    }

    /*
     * Shifts all elements to the right of the given index one left
     */
    private void shiftLeft(int index) {
        for (int i = index; i < size - 1; i++) {
            items[i] = items[i + 1];
        }
    }

    private void shiftRight(int index) {
        for (int i = size; i >= index; i--) {
            items[i + 1] = items[i];
        }
    }

    public static void main(String[] args) {
        IntList i = new IntList();
        // Creating the initial IntList
        i.append(1);
        i.append(2);
        i.append(3);
        System.out.println("Initial IntList:");
        System.out.println(i);
        // Test for prepend();
        i.prepend(5);
        System.out.println("After Adding 5 to front:");
        System.out.println(i);
        // Test for prepend();
        i.prepend(6);
        System.out.println("After Adding 6 to front:");
        System.out.println(i);
        // Test for size calculation;
        System.out.println("Size Check, Size: " + i.size);
        // Test for insertAt();
        i.insertAt(0, 2);
        System.out.println("After Inserting a 0 at 2");
        System.out.println(i);
        // Test for size;
        System.out.println("Size Check, Size: " + i.size);
        // Adding multiple '0' to test removeAll();
        i.prepend(3);
        i.append(3);
        i.insertAt(3, 7);
        System.out.println("After adding multiple 3's to be removed");
        System.out.println(i);
        System.out.println("Size Check, Size: " + i.size);
        // Test for removeAll();
        i.removeAll(3);
        System.out.println("After removing all 3's");
        System.out.println(i);
        System.out.println("Size Check, Size: " + i.size);
        System.out.println("----- END TESTS -----");
    }

}