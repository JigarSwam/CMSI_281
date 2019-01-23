// Jigar Swaminarayan & Luis Garcia
package forneymon.cardgame;

public class ForneymonCard {
    private String name;
    private String type;

    private String burneymon = "Burnymon";
    private String dampymon = "Dampymon";
    private String leafymon = "Leafymon";

    ForneymonCard() {
        name = "MissingNu";
        type = "Burneymon";
    }

    ForneymonCard(String n, String t) {
        if (n == null || n.length() < 1) {
            throw new IllegalArgumentException();
        }
        if (t != burneymon && t != dampymon && t != leafymon) {
            throw new IllegalArgumentException();
        }
        name = n;
        type = t;
    }

    public String toString() {
        return type + ": " + name;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

}