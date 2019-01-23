package sentinal;

public class PhraseHash implements PhraseHashInterface {

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------

    private final static int BUCKET_START = 1000;
    private final static double LOAD_MAX = 0.7;
    private int size, longest;
    private String[] buckets;

    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------

    PhraseHash() {
        size = 0;
        longest = 0;
        buckets = new String[BUCKET_START];

        // TODO: Populate fields as appropriate
        // [!] Don't forget to dynamically allocate buckets!
    }

    // -----------------------------------------------------------
    // Public Methods
    // -----------------------------------------------------------

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void put(String s) {
        if (!isEmpty()) {
            checkAndGrow();
        }
        // there will always be an empty space before or after
        int index = hash(s);
        while (buckets[index] != null) {
            if (buckets[index].equals(s)) {
                return;
            }
            if (++index == buckets.length) {
                index = 0;
            }
        }
        buckets[index] = s;
        size++;
        int length = phraseLength(s);
        if (length > longest) {
            longest = length;
        }
    }

    public String get(String s) {
        int index = hash(s);
        while (buckets[index] != null) {
            if (s.equals(buckets[index])) {
                return s;
            }
            if (++index == buckets.length) {
                index = 0;
            }
        }
        return null;
    }

    public int longestLength() {
        return longest;
    }

    // -----------------------------------------------------------
    // Helper Methods
    // -----------------------------------------------------------
// Hash method is arbitrarily decided. At the section with the greatest 
    // concentration of words the hash function will slow down
    // the concentration factor will be random and coincidental depending on the data used to hash,
    //so make this concentration small by removing items from the concentration by hashing with different
    // characteristics at each level of hashing 
    // by making the categories more widespread
    private int hash(String s) {
        // useful data
        char firstChar = s.charAt(0);
        char lastChar = s.charAt(s.length() - 1);
        boolean evenLastChar = (lastChar % 2 == 0);
        // boolean evenFirstChar = (firstChar % 2 == 0);
        boolean evenLength = (s.length() % 2 == 0);
        int lenBucket = buckets.length;

        int index = (evenLength) ? 0 : lenBucket / 2; // splits array in half by length
        index += (evenLastChar) ? 0 : lenBucket / 4; // splits half in half by last char
        // 26 is number of letters in alphabet
        int lengthToLetterRatio = buckets.length / (4 * 26);
        // 97 is the ascii value of lowercase a
        int alphaOrderPos = firstChar - 97;
        index += lengthToLetterRatio * alphaOrderPos; // splits quarter alphabetically
        return index; // could make it more random by following a function that picks a value based on the data
    }

    private void checkAndGrow() {
        boolean needsToGrow = check();
        if (needsToGrow) {
            grow();
        }
    }

    private boolean check() {
        return size / (buckets.length * 1.0) >= 0.7;
    }

    private void grow() {
        // makes load == 0.1
        int newSize = buckets.length * (int) (LOAD_MAX * 10);
        String[] temp = buckets;
        buckets = new String[newSize];
        for (int i = 0; i < temp.length; i++) {
            put(temp[i]);
        }
    }

    private int phraseLength(String phrase) {
        int count = 1;
        int index = phrase.indexOf(" ");
        while (index > 0) {
            count++;
            index = phrase.indexOf(" ", index + 1);
        }
        return count;
    }

}