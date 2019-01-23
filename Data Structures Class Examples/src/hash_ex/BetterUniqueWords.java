package hash_ex;

import java.util.Scanner;
import java.util.HashSet;

public class BetterUniqueWords {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a sentence.");

		String[] words = input.nextLine().split(" ");

		HashSet<String> setPoint = new HashSet<String>();
		for (String w : words) {
			setPoint.add(w);
		}
		System.out.println("There are " + setPoint.size() + " unique words");
		input.close();
	}

}
