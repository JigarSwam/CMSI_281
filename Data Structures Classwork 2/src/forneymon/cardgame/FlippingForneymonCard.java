// Jigar Swaminarayan & Luis Garcia
package forneymon.cardgame;

public class FlippingForneymonCard extends ForneymonCard {
    private boolean isFaceDown;

    FlippingForneymonCard() {
        super();
        isFaceDown = true;
    }

    FlippingForneymonCard(String n, String t, boolean flip) {
        super(n, t);
        isFaceDown = flip;
    }

    public boolean flip() {
        isFaceDown = !isFaceDown;
        return isFaceDown;
    }

    int match(FlippingForneymonCard other) {
        if (other.isFaceDown || this.isFaceDown) {
            return 2;
        } else if (other.getName() == this.getName() && other.getType() == this.getType()) {
            return 1;
        }
        return 0;
    }

    public String toString() {
        return isFaceDown ? "?: ?" : super.toString();
    }
}