package forneymon.cardgame;

public class ForneymonCardTest {
    public static void main(String[] args) {
        try {
            FlippingForneymonCard test = new FlippingForneymonCard("", "Burnymon", false);
        } catch (IllegalArgumentException iae) {
            System.out.println("Caught a blank name");
        }
        try {
            FlippingForneymonCard test = new FlippingForneymonCard(null, "Burnymon", false);
        } catch (IllegalArgumentException iae) {
            System.out.println("Caught a blank name");
        }

        FlippingForneymonCard leafy = new FlippingForneymonCard("leafy", "Leafymon", true);
        FlippingForneymonCard leafier = new FlippingForneymonCard("leafier", "Leafymon", true);
        System.out.println(leafy);
        System.out.println(leafy.match(leafier));
        leafy.flip();
        leafier.flip();
        System.out.println(leafy.match(leafier));
        System.out.println(leafy);
        FlippingForneymonCard dampy = new FlippingForneymonCard("dampy", "Dampymon", false);
        System.out.println(dampy);
        System.out.println(dampy.match(new FlippingForneymonCard("dampy", "Dampymon", false)));
        System.out.println(dampy.flip()); // expects true

        FlippingForneymonCard burny = new FlippingForneymonCard("burny", "Burnymon", false);

        // "Burnymon: burny"
        System.out.println(burny);
        burny.flip();
        // "?: ?"

        FlippingForneymonCard missingNu = new FlippingForneymonCard();
        // "?: ?"
        System.out.println(missingNu);
        missingNu.flip();
        // "Burnymon: MissingNu"
        System.out.println(missingNu);

        // 2
        System.out.println(burny.match(missingNu));
        burny.flip();
        // 0
        System.out.println(burny.match(missingNu));
    }

}