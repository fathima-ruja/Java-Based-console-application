import java.util.Arrays;
import java.util.Random;

/** Performance / complexity demonstration. (Member 5) */
public class PerformanceComparison {

    public static void run(Graph userGraph) {
        System.out.println("\n=============================================");
        System.out.println(" PERFORMANCE COMPARISON");
        System.out.println("=============================================");
        int n = InputHelper.readIntInRange("Data size n (10 - 100000): ", 10, 100000);

        Random rnd = new Random(42);
        int[] sorted = new int[n];
        for (int i = 0; i < n; i++) sorted[i] = i * 2;          // distinct, sorted, even numbers
        int[] shuffled = sorted.clone();
        for (int i = n - 1; i > 0; i--) {                       // Fisher-Yates shuffle
            int j = rnd.nextInt(i + 1);
            int t = shuffled[i]; shuffled[i] = shuffled[j]; shuffled[j] = t;
        }
        int presentKey = sorted[n / 2];
        int absentKey = -1;                                     // never present (worst case)

        System.out.println();
        System.out.println(ResultLog.header());

        // ---- Searching ----
        SearchOperations.SearchResult r;
        r = SearchOperations.linearSearch(shuffled, absentKey);
        show("Search", "Linear (absent key)", r.steps, r.nanos, "n=" + n);
        r = SearchOperations.binarySearch(sorted, absentKey);
        show("Search", "Binary (absent key)", r.steps, r.nanos, "n=" + n);
        r = SearchOperations.linearSearch(shuffled, presentKey);
        show("Search", "Linear (present key)", r.steps, r.nanos, "n=" + n);
        r = SearchOperations.binarySearch(sorted, presentKey);
        show("Search", "Binary (present key)", r.steps, r.nanos, "n=" + n);

        // ---- Insert at front: Array vs Linked List ----
        int m = Math.min(n, 5000);
        ArrayOperations arr = new ArrayOperations(m);
        long t0 = System.nanoTime();
        for (int i = 0; i < m; i++) arr.insertAt(0, i);
        long arrTime = System.nanoTime() - t0;
        show("Insert at front", "Array (shift)", arr.getStepCount(), arrTime, m + " inserts");

        LinkedListOperations ll = new LinkedListOperations();
        t0 = System.nanoTime();
        for (int i = 0; i < m; i++) ll.insertAtBeginning(i);
        long llTime = System.nanoTime() - t0;
        show("Insert at front", "Linked List", ll.getStepCount(), llTime, m + " inserts");

        // ---- Graph traversal ----
        Graph g = userGraph;
        String note = "your graph";
        if (g.vertexCount() == 0) {
            g = new Graph();
            g.loadSample();
            note = "sample graph";
        }
        String start = g.firstVertex();
        Graph.TraversalResult b = g.bfs(start);
        show("Graph Traversal", "BFS", b.steps, b.nanos, note + ", start=" + start);
        Graph.TraversalResult d = g.dfs(start);
        show("Graph Traversal", "DFS", d.steps, d.nanos, note + ", start=" + start);

        System.out.println("\nExplanation:");
        System.out.println(" - Linear search is O(n); binary search is O(log n) but needs sorted data.");
        System.out.println("   For n=" + n + " the worst case is ~" + n + " vs ~" + (int) (Math.log(n) / Math.log(2) + 1) + " comparisons.");
        System.out.println(" - Inserting at the front of an array shifts every element (O(n) each, O(n^2) total);");
        System.out.println("   a linked list just re-links the head, so each insert is O(1).");
        System.out.println(" - BFS and DFS are both O(V + E); step counts are similar, but visit order differs:");
        System.out.println("   BFS goes level by level (queue), DFS goes deep first (stack).");
    }

    private static void show(String op, String algo, long steps, long nanos, String note) {
        System.out.println(ResultLog.row(op, algo, steps, nanos, note));
        ResultLog.add(op, algo, steps, nanos, note);
    }
}
