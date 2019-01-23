package sentinal;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sentinal implements SentinalInterface {
    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------
    private PhraseHash posHash, negHash;
    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------
    Sentinal(String posFile, String negFile) throws FileNotFoundException {
        posHash = new PhraseHash();
        negHash = new PhraseHash();
        loadSentimentFile(posFile, true);
        loadSentimentFile(negFile, false);
    }
    // -----------------------------------------------------------
    // Methods
    // -----------------------------------------------------------
    public void loadSentiment(String phrase, boolean positive) {
        if (positive) {
            posHash.put(phrase);
        } else {
            negHash.put(phrase);
        }
    }
    public void loadSentimentFile(String filename, boolean positive) throws FileNotFoundException {
        Scanner in = getScanner(filename);
        while (in.hasNextLine()) {
            String phrase = in.nextLine();
            loadSentiment(phrase, positive);
        }
        in.close();
    }
    public String sentinalyze(String filename) throws FileNotFoundException {
        Scanner in = getScanner(filename);
        int counter = 0;
        while (in.hasNextLine()) {
            String sentence = in.nextLine();
            counter = analyze(sentence);
//          String phrase = sentence.substring(0, sentence.indexOf(" "));
//          while(phrase.length()<negHash.longestLength())
//          if (parsePhrase(negHash, phrase)) {
//              counter--;
//          }
//          if (parsePhrase(posHash, phrase)) {
//              counter++;
//          }
        }
        return (counter < 0) ? "negative" : (counter == 0) ? "neutral" : "positive";
    }
    // -----------------------------------------------------------
    // Helper Methods
    // -----------------------------------------------------------
    // TODO: add your helper methods here!
    private Scanner getScanner(String filename) throws FileNotFoundException {
        File file = new File(filename);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        return new Scanner(file);
    }
    private int analyze(String sentence) {
        if (sentence.isEmpty()) {
            return 0;
        }
        String phrase = getPhrase(sentence, 1);
        int quality = 0;
        int sentLength = phraseLength(sentence);
        int posLength = posHash.longestLength();
        int negLength = negHash.longestLength();
        int longest = (posLength > negLength) ? posHash.longestLength() : negHash.longestLength();
        for (int i = 1; i < longest; i++) {// gets all the combinations of phrases
            if (i <= posLength && posHash.get(phrase) != null) {// in poshash
                quality += 1 + analyze(sentence.substring(phrase.length()).trim());
                break;
            }
            if (i <= negLength && negHash.get(phrase) != null) {// in neghash
                quality += -1 + analyze(sentence.substring(phrase.length()).trim());
                break;
            }
            if (sentLength == 1) { // last and not in hashes
                return 0;
            }
            if (i + 1 == longest && i + 1 <= sentLength) { // needs to find next combinations
                quality += analyze(sentence.substring(sentence.indexOf(" ")).trim());
                break;
            }
            if (i == sentLength) {// sentence smaller than largest phrase
                quality += analyze(sentence.substring(sentence.indexOf(" ")).trim());
                break;
            }
            phrase = getPhrase(sentence, i + 1); // get phrase one word larger
        }
        return quality;
    }
    private String getPhrase(String sentence, int maxDesiredLength) {
        sentence = sentence + " ";
        int length = 1;
        int index = sentence.indexOf(" ");
        int lastIndex = index;
        while (index > 0 && maxDesiredLength != 1) {
            if (length == maxDesiredLength) {
                lastIndex = index;
                break;
            }
            length++;
            lastIndex = index;
            index = sentence.indexOf(" ", index + 1);
        }
        return sentence.substring(0, lastIndex);
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