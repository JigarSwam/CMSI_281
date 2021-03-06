// Jigar Swaminarayan
package forneymonegerie;

public class Forneymonegerie implements ForneymonegerieInterface {

	// Fields
	// ----------------------------------------------------------
	private ForneymonType[] collection;
	private int size;
	private int typeSize;
	private static final int START_SIZE = 16;

	// Constructor
	// ----------------------------------------------------------
	Forneymonegerie() {
		collection = new ForneymonType[START_SIZE];
		size = 0;
		typeSize = 0;
	}

	// Methods
	// ----------------------------------------------------------
	public boolean empty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public int typeSize() {
		return typeSize;
	}
	
	public boolean collect(String toAdd) {
		checkAndGrow();
		int index = find(toAdd);
		if (index == -1) {
			collection[typeSize] = new ForneymonType(toAdd, 1);
			typeSize++;
			size++;
			return true;
		}
		else {
			collection[index].count++;
			size++;
			return false;
		}
	}

	
	public boolean release(String toRemove) {
		int index = find(toRemove);
		if (index == -1) {
			return false;
		}
		else {
			collection[index].count--;
			if (collection[index].count == 0) {
				releaseType(toRemove);
			}
			size--;
			return true;
		}
	}

	public void releaseType(String toNuke) {
		int index = find(toNuke);
		if (index == -1) {
			return;
		}
		else {
			size -= collection[index].count;
			typeSize--;
			shiftLeft(index);
		}
	}

	public int countType(String toCount) {
		int index = find(toCount);
		if (index == -1) {
			return 0;
		} else {
			return collection[index].count;
		}
	}

	public boolean contains(String toCheck) {
		int index = find(toCheck);
		if (index == -1) {
			return false;
		} else {
			return true;
		}
	}

	public String nth(int n) {
		String nthType = "";
		for (int i = 0; i < typeSize; i++) {
			if (n < collection[i].count) {
				nthType = collection[i].type;
				return nthType;
			} else {
				n -= collection[i].count;
			}
		}
		return nthType;
	}

	public String rarestType() {
		if (this.empty()) {
			return null;
		}
		
		int currentLowestCount = collection[0].count;
		String typeWithLowestCount = collection[0].type;
		for (int i = 1; i < typeSize; i++) {
			if (collection[i].count <= currentLowestCount) {
				currentLowestCount = collection[i].count;
				typeWithLowestCount = collection[i].type;
			}
		}
		return typeWithLowestCount;
	}

	public Forneymonegerie clone() {
		Forneymonegerie clone = new Forneymonegerie();
		for (int i = 0; i < this.size; i++) {
			clone.collection[i] = this.collection[i];
		}
		clone.size = this.size;
		clone.typeSize = this.typeSize;
		return clone;
	}

	public void trade(Forneymonegerie other) {
		ForneymonType[] tempCollection = new ForneymonType[typeSize];
		int tempSize;
		int tempTypeSize;
		tempCollection = this.collection;
		tempSize = this.size;
		tempTypeSize = this.typeSize;
		this.collection = other.collection;
		this.size = other.size;
		this.typeSize = other.typeSize;
		other.collection = tempCollection;
		other.size = tempSize;
		other.typeSize = tempTypeSize;
	}

	// Static methods
	// ----------------------------------------------------------
	public static Forneymonegerie diffMon(Forneymonegerie y1, Forneymonegerie y2) {
		Forneymonegerie differentMon = y1.clone();
		int index;
		for (int i = 0; i < y2.typeSize; i++) {
			if (differentMon.contains(y2.collection[i].type)) {
				index = differentMon.find(y2.collection[i].type);
				if (differentMon.collection[i].count  > y2.collection[index].count) {
					differentMon.collection[i].count -= y2.collection[index].count;
				} else {
					differentMon.shiftLeft(i);
				}
			}
		}
		return differentMon;
	}

	public static boolean sameCollection(Forneymonegerie y1, Forneymonegerie y2) {
		// return diffMon(y1, y2).empty() && diffMon(y2, y1).empty();
		throw new UnsupportedOperationException();
	}

	// Private helper methods
	// ----------------------------------------------------------

	// TODO: Add yours here!

	private void shiftLeft(int index) {
		for (int i = index; i < size - 1; i++) {
			collection[i] = collection[i + 1];
		}
	}
	
	private int find(String forneymonType) {
		int isFound = -1;
		for (int i = 0; i < typeSize; i++) {
			if (forneymonType.equals(this.collection[i].type)) {
				isFound = i;
			}
		}
		return isFound;
	}

	private void checkAndGrow() {
		if (size < collection.length) {
			return;
		}
		ForneymonType[] newItems = new ForneymonType[collection.length * 2];
		for (int i = 0; i < collection.length; i++) {
			newItems[i] = collection[i];
		}
		collection = newItems;
	}

	public String toString() {
		String result = "";
		for (int i = 0; i < typeSize; i++) {
			result += "\"" + collection[i].type + "\"" + ": " + collection[i].count;
			if (i < typeSize - 1) {
				result += ", ";
			}
		}
		return "[ " + result + " ]";
	}

	// Private Classes
	// ----------------------------------------------------------
	private class ForneymonType {
		String type;
		int count;

		ForneymonType(String t, int c) {
			type = t;
			count = c;
		}
	}
}
