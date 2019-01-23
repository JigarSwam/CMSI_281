package dictreenary;

import java.util.ArrayList;

public class Dictreenary implements DictreenaryInterface {

	// Fields
	// -----------------------------------------------------------
	TTNode root;

	// Constructor
	// -----------------------------------------------------------
	Dictreenary() {
		root = null;
	}

	// Methods
	// -----------------------------------------------------------

	public boolean isEmpty() {
		return root == null;
	}

	public void addWord(String toAdd) {
		String normalizedWord = normalizeWord(toAdd);
		this.root = recursiveAdd(this.root, normalizedWord);

	}

	public boolean hasWord(String query) {
		String normalizedQuery = normalizeWord(query);
		return nodeTraversal(normalizedQuery, root);
	}

	public String spellCheck(String query) {

		// Case: Query already exists
		if (hasWord(query)) {
			return query;
		}

		// Case: Has to go through characters of query and swap to check and
		// returns null if not found
		for (int i = 0; i < query.length() - 1; i++) {
			String potentialMatch = query.substring(0, i) + query.charAt(i + 1) + query.charAt(i)
					+ query.substring(i + 2, query.length());
			if (hasWord(potentialMatch)) {
				return potentialMatch;
			}
		}
		return null;
	}

	public ArrayList<String> getSortedWords() {
		ArrayList<String> sortedWords = new ArrayList<String>();
		sortWords(root, "", sortedWords);

		return sortedWords;
	}

	// Helper Methods
	// -----------------------------------------------------------

	private String normalizeWord(String s) {
		// Edge case handling: empty Strings illegal
		if (s == null || s.equals("")) {
			throw new IllegalArgumentException();
		}
		return s.trim().toLowerCase();
	}

	/*
	 * Returns: int less than 0 if c1 is alphabetically less than c2 0 if c1 is
	 * equal to c2 int greater than 0 if c1 is alphabetically greater than c2
	 */
	private int compareChars(char c1, char c2) {
		return Character.toLowerCase(c1) - Character.toLowerCase(c2);
	}

	// [!] Add your own helper methods here!

	// TODO: Change this to else ifs after the first if and second if
	private TTNode recursiveAdd(TTNode root, String toAdd) {
		char firstLetter = toAdd.charAt(0);

		// base case: root is null --> root = firstLetter;
		if (root == null) {
			root = new TTNode(firstLetter, false);
		}

		// Case: Need to go left
		if (compareChars(firstLetter, root.letter) < 0) {
			root.left = recursiveAdd(root.left, toAdd);
		}

		// Case: Need to go right
		if (compareChars(firstLetter, root.letter) > 0) {
			root.right = recursiveAdd(root.right, toAdd);
		}

		// Case: Need to go down the middle
		if (compareChars(firstLetter, root.letter) == 0) {
			if (toAdd.length() > 1) {
				root.mid = recursiveAdd(root.mid, toAdd.substring(1));
			} else {
				root.wordEnd = true;
			}
		}
		return root;
	}

	private boolean nodeTraversal(String toFind, TTNode current) {

		if (current == null || toFind.equals("")) {
			return false;
		}

		// Case: We have one letter left and the node we compare it to is
		// the same and it is also the word end
		if (toFind.length() == 1 && compareChars(current.letter, toFind.charAt(0)) == 0 && current.wordEnd) {
			return true;
		}

		if (compareChars(current.letter, toFind.charAt(0)) == 0) {
			return nodeTraversal(toFind.substring(1), current.mid);
		} else if (compareChars(toFind.charAt(0), current.letter) < 0) {
			return nodeTraversal(toFind, current.left);
		} else {
			return nodeTraversal(toFind, current.right);
		}

	}

	private void sortWords(TTNode current, String wordPrefix, ArrayList<String> al) {

		if (current == null) {
			return;
		}

		sortWords(current.left, wordPrefix, al);

		if (current.wordEnd) {
			al.add(wordPrefix + current.letter);
		}

		sortWords(current.mid, wordPrefix + current.letter, al);

		sortWords(current.right, wordPrefix, al);

	}

	// TTNode Internal Storage
	// -----------------------------------------------------------

	/*
	 * Internal storage of Dictreenary words as represented using a Ternary Tree
	 * with TTNodes
	 */
	private class TTNode {

		boolean wordEnd;
		char letter;
		TTNode left, mid, right;

		TTNode(char c, boolean w) {
			letter = c;
			wordEnd = w;
		}

	}

}
