import java.util.ArrayList;
import java.util.List;

/** Stores results of searches, traversals and comparisons for "Display All Results". (Member 5) */
public class ResultLog {
    private static final List<String> ENTRIES = new ArrayList<>();

    public static String header() {
        return String.format("%-16s %-24s %10s %14s  %s", "Operation", "Algorithm", "Steps", "Time (ns)", "Details")
                + "\n" + "-".repeat(84);
    }

    public static String row(String op, String algo, long steps, long nanos, String note) {
        return String.format("%-16s %-24s %10d %14d  %s", op, algo, steps, nanos, note);
    }

    public static void add(String op, String algo, long steps, long nanos, String note) {
        ENTRIES.add(row(op, algo, steps, nanos, note));
    }

    public static void displayAll() {
        System.out.println("\n=============================================");
        System.out.println(" ALL RECORDED RESULTS");
        System.out.println("=============================================");
        if (ENTRIES.isEmpty()) {
            System.out.println("No results recorded yet. Run a search, traversal or performance comparison first.");
            return;
        }
        System.out.println(header());
        for (String e : ENTRIES) System.out.println(e);
    }
}
