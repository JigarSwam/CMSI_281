// Luis Garcia, Jigar Swaminarayan, & Bennett Shingledecker
package web_nav;

import java.util.ArrayList;
// TODO: Choose imports based on DS choice
import java.util.Scanner;

public class WebNavigator {

    // Fields
    private String current; // Tracks currently visited site
    ArrayList<String> sites;
    private int currentIndex;

    // Constructor
    WebNavigator() {
        current = null;
        sites = new ArrayList<String>();
        currentIndex = -1;
    }

    // Methods
    // [!] YOU DO NOT HAVE TO MODIFY THIS METHOD FOR YOUR SOLUTION
    public boolean getNextUserCommand(Scanner input) {
        String command = input.nextLine();
        String[] parsedCommand = command.split(" ");

        // Switch on the command (issued first in input line)
        switch (parsedCommand[0]) {
        case "exit":
            System.out.println("Goodbye!");
            return false;
        case "visit":
            visit(parsedCommand[1]);
            break;
        case "back":
            back();
            break;
        case "forward":
            forw();
            break;
        default:
            System.out.println("[X] Invalid command, try again");
        }

        System.out.println("Currently Visiting: " + current);

        return true;
    }

    /*
     * Visits the current site, clears the forward history, and records the visited
     * site in the back history
     */
    public void visit(String site) {
        currentIndex++;
        sites.add(currentIndex, site);
        current = sites.get(currentIndex);
        if (currentIndex < sites.size()) {
            for (int i = currentIndex + 1; i < sites.size(); i++) {
                sites.remove(i);
                i--;
            }
        }
    }

    /*
     * Changes the current site to the one that was last visited in the order on
     * which visit was called on it
     */
    public void back() {
        try {
            currentIndex--;
            current = sites.get(currentIndex);
        } catch (IndexOutOfBoundsException ioobe) {
            currentIndex++;
            return;
        }
    }

    public void forw() {
        try {
            currentIndex++;
            current = sites.get(currentIndex);
        } catch (IndexOutOfBoundsException ioobe) {
            currentIndex--;
            return;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        WebNavigator navi = new WebNavigator();

        System.out.println("Welcome to ForneyFox, enter a command from your ForneyFox user manual!");
        while (navi.getNextUserCommand(input)) {
        }
        System.out.println("Goodbye!");
    }

}