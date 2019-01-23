package linked_forneymonegerie;

import java.util.NoSuchElementException;

public class LinkedForneymonegerie implements LinkedForneymonegerieInterface {

    // Fields
    // -----------------------------------------------------------
    private ForneymonType head;
    private ForneymonType tail;
    private int size, typeSize, modCount;
    
    
    // Constructor
    // -----------------------------------------------------------
    LinkedForneymonegerie () {
    	head = null;
    	tail = null;
        size = 0;
        typeSize = 0;
        modCount = 0;
    }
    
    
    // Methods
    // -----------------------------------------------------------
    
    // Getter to get modCount for debugging in the Iterator class
    public int modCount () {
    	return modCount;
    }
    
    public boolean empty () {
        return size == 0;
    }
    
    public int size () {
        return size;
    }
    
    public int typeSize () {
        return typeSize;
    }
    
    public boolean collect (String toAdd) {
    	return insertForneymon(toAdd, 1);
    }
    
    public boolean release (String toRemove) {
    	return removeForneymon(toRemove, 1);
    }

    public void releaseType (String toNuke) {
    	ForneymonType foundForneymonType = find(toNuke);
    	
    	if (foundForneymonType == null) {return;}
    	
    	ForneymonType nextForneymon = foundForneymonType.next;
    	ForneymonType prevForneymon = foundForneymonType.prev;
    	
    	// Case: Removing Head
    	if (foundForneymonType == head) {
    		head = head.next;
    		}
    	
    	// Case: Removing a ForneymonType that is the tail
    	else if (foundForneymonType.next == null){
    		prevForneymon.next = nextForneymon;
    		tail = prevForneymon;
    	}
    	
    	// Case: Removing an arbitrary ForneymonType that is not the head or the tail
    	else {
    		prevForneymon.next = nextForneymon;
    		nextForneymon.prev = prevForneymon;
    	}
    	size -= foundForneymonType.count;
    	typeSize--;
    	modCount++;
    }
    
    public int countType (String toCount) {
    	ForneymonType foundType = find(toCount);
    	if (foundType == null) {return 0;}
    	else {
    		return foundType.count;
    	}
    }
    
    public boolean contains (String toCheck) {
    	ForneymonType foundNode = find(toCheck);
    	return foundNode != null;
    }
    
    public String rarestType () {
    	int currentLowestCount = head.count;
    	String currentLowestType = head.type;
    	
    	if (this.empty()) {return null;}
    	
    	for (ForneymonType n = head; n != null; n = n.next) {
    		if (n.count <= currentLowestCount) {
    			currentLowestCount = n.count;
    			currentLowestType = n.type;
    		}
    	}
    	return currentLowestType;
    }
    
    public LinkedForneymonegerie clone () {
    	LinkedForneymonegerie clone = new LinkedForneymonegerie();
    	
    	for (ForneymonType n = head; n != null; n = n.next) {
    		clone.insertForneymon(n.type, n.count);		
    	}
    	clone.size = this.size;
    	clone.typeSize = this.typeSize;
    	return clone;
    }
    
    public void trade (LinkedForneymonegerie other) {
        ForneymonType tempHead;
        int tempSize;
        int tempTypeSize;
        
        tempHead = this.head;
        tempSize = this.size;
        tempTypeSize = this.typeSize;
        
        this.head = other.head;
        this.size = other.size;
        this.typeSize = other.typeSize;
        
        other.head = tempHead;
        other.size = tempSize;
        other.typeSize = tempTypeSize;
        other.modCount++;
        this.modCount++;
    }

    public LinkedForneymonegerie.Iterator getIterator () {
        if (this.empty()) {throw new IllegalStateException();}
        Iterator it = new Iterator(this);
        it.current = head;
        return it;
    }
    
    
    // -----------------------------------------------------------
    // Static methods
    // -----------------------------------------------------------
    
    public static LinkedForneymonegerie diffMon (LinkedForneymonegerie y1, LinkedForneymonegerie y2) {
        LinkedForneymonegerie result = y1.clone();
        for (ForneymonType n = y2.head; n != null; n = n.next) {
        	result.removeForneymon(n.type, n.count);
        }
        return result;
    }
    
    public static boolean sameCollection (LinkedForneymonegerie y1, LinkedForneymonegerie y2) {
    	return diffMon(y1, y2).empty() && y1.size == y2.size && y1.typeSize == y2.typeSize;
    }
    
    // Private helper methods
    // -----------------------------------------------------------

    // TODO: Your helper methods here!  
   
    /**
     * Method which finds if a current forneymonType exists
     * @param forneymonType: name of the type of Forneymon
     * @return reference to ForneymonType which matches the parameter if found, otherwise returns null
     */
    private ForneymonType find(String typeOfForneymon) {
    	for (ForneymonType n = head; n != null; n = n.next) {
    		if (n.type.equals(typeOfForneymon)) {
    			return n;
    		}
    	}
    	return null;
    }
    
    /** Method which finds the final node (ForneymonType) in the LinkedForneymonegerie
     * @return n: reference to final ForneymonType in LinkedForneymonegerie
     */
    private ForneymonType lastNode() {
    	// use tail pointer
    	for (ForneymonType n = head; n != null; n = n.next) {
    		if (n.next == null) {
    			return n;
    		}
    	}
    	return null;
    }
    
    /**
     * Method to remove any amount of a certain Forneymon
     * @param typeName: name of the Forneymon that is to be removed
     * @param count: amount of Forneymon to be removed
     * @return true if any Forneymon is removed, false if Forneymon to be removed
     * does not exist
     */
    private boolean removeForneymon(String typeName, int count) {
    	ForneymonType foundForneymon = find(typeName);
    	if (foundForneymon == null) {return false;}
    	
    	int newCount = foundForneymon.count - count;
    	
    	if (newCount <= 0) {
    		releaseType(foundForneymon.type);
    	} else {
    		foundForneymon.count = newCount;
    		size -= count;
    	}
    	return true;
    }
    
    /**
     * Method to remove any amount of a certain Forneymon
     * @param typeName: name of the Forneymon that is to be added
     * @param count: amount of Forneymon to be added
     * @return true if any Forneymon is added, false if Forneymon is already in Forneymonegerie
     */
    private boolean insertForneymon(String typeName, int count) {
    	ForneymonType foundForneymon = find(typeName);
    	ForneymonType finalForneymon = lastNode();
    	
    	if (empty()) {
    		head = new ForneymonType(typeName, count);
    		head.prev = null;
    		size++;
    		typeSize++;
    		modCount++;
    		return true;
    	}  	
    	if (foundForneymon == null && head != null) {
    		ForneymonType newForneymon = new ForneymonType(typeName, count);
    		finalForneymon.next = newForneymon;
    		newForneymon.prev = finalForneymon; 
    		tail = newForneymon;
    		size++;
    		typeSize++;
    		modCount++;
    		return true;
    	} else {
    		foundForneymon.count++;
    		size++;
    		modCount++;
    		return false;
    	}	
    }
    
    /**
     * Method which creates a String representation of the LinkedForneymonegerie
     * @return result: String representation of LinkedForneymonegerie
     */
    public String toString() {
		String result = "";
		for (ForneymonType n = head; n != null; n = n.next) {
			result += "\"" + n.type + "\"" + ": " + n.count;
			if (n.next != null) {
				result += ", ";
			}
		}
		return "[ " + result + " ]";
	}
    
    
    // Inner Classes
    // -----------------------------------------------------------
    
    public class Iterator implements LinkedForneymonegerieIteratorInterface {
        LinkedForneymonegerie owner;
        ForneymonType current;
        int itModCount;
        int itIndex;
        
        Iterator (LinkedForneymonegerie y) {
        	owner = y;
            current = head;
            itModCount = owner.modCount;
            itIndex = 0;
        }
        
        public boolean hasNext () {
        	if (!isValid()) {return false;}
        	if (itIndex < current.count - 1) {return true;}
        	if (itIndex > current.count - 1 && current.next != null) {return true;}
        	return current != null && current.next != null;
        }
        
        public boolean hasPrev () {
        	if (!isValid()) {return false;}
        	if (itIndex == 0 && current.prev == null) {return false;}
        	if (itIndex == 0 && current.prev != null) {return true;}
        	if (itIndex > 0) {return true;}
        	return false;
        }
        
        public boolean isValid () {
            return itModCount == owner.modCount;
        }
        
        public String getType () {
        	if (!isValid()) {return null;}
            return current.type;
        }

        public void next () {
        	if (current == null) {return;}
        	if (!hasNext()) {throw new NoSuchElementException();}
        	if (!isValid()) {throw new IllegalStateException();}
        	
        	itIndex++;
        	if (itIndex > current.count-1) {
    			current = current.next;
    			itIndex = 0;
    		}
        }
        
        public void prev () {
        	if (current == null) {return;}
        	if (!hasPrev()) {throw new NoSuchElementException();}
        	if (!isValid()) {throw new IllegalStateException();}
        	
        	itIndex--;
        	if (itIndex < 0) {
        		current = current.prev;
        		itIndex = current.count-1;
        	}
        }

        public void replaceAll (String toReplaceWith) {
        	ForneymonType foundForneymonType = find(toReplaceWith);
        	ForneymonType finalForneymon = lastNode();
        	
        	if (!isValid()) {throw new IllegalStateException();}
        	
        	// Case 1: replace what you are currently pointing to --> return
        	if (foundForneymonType != null && foundForneymonType.type.equals(current.type)) {
        		return;
        	}
        	
            // Case 2: toReplaceWith exists in the list --> add the count of what iterator is pointing
            //		   to and add count to what is already in list (look at picture)
        	if (foundForneymonType != null) {
        		if (current == head) {head = head.next;}
        		foundForneymonType.count += current.count;
        		releaseType(current.type);
        	}
        	
            // Case 3: toReplaceWith doesn't exist in the list --> put it at end w/ count of current
        	if (foundForneymonType == null) {
        		ForneymonType newForneymon = new ForneymonType(toReplaceWith, current.count);
        		if (current == head) {head = head.next;}
        		finalForneymon.next = newForneymon;
        		releaseType(current.type);
        	}
       
            itModCount++;
            owner.modCount++;
        }
        
    }
    
    private class ForneymonType {
        ForneymonType next, prev;
        String type;
        int count;
        
        ForneymonType (String t, int c) {
            type = t;
            count = c;
        }
    }  
}