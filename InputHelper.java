import java.util.Scanner;

/** Shared, validated console input helpers. (Member 5 - Integration) */
public class InputHelper {
    private static final Scanner SC = new Scanner(System.in);

    public static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = SC.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("  Invalid input. Please enter a whole number.");
            }
        }
    }

    public static int readIntInRange(String prompt, int min, int max) {
        while (true) {
            int v = readInt(prompt);
            if (v >= min && v <= max) return v;
            System.out.println("  Please enter a number between " + min + " and " + max + ".");
        }
    }

    public static String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = SC.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("  Input cannot be empty.");
        }
    }
}
