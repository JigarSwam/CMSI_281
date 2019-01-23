import java.util.Scanner;
import java.util.HashSet;

public class UniqueWords{
  public static void main(String[] args){
    Scanner input = new Scanner(System.in);
    //adds a space to the end so for loop catches all words
    String sentence = input.nextLine()+" ";
    //removes duplicate items automatically
    HashSet<String> words = new HashSet<String>();
    //will hold the endIndex of the last added word
    int holdIndex = 0;
    for(int i=0; i<sentence.length();i++){
      if(sentence.charAt(i)== ' '){
        //trims words to avoid whitespace differences
        words.add(sentence.substring(holdIndex,i).trim());
        holdIndex = i;
      }
    }
    System.out.println(words.size());
  }
}