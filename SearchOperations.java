import java.util.Arrays;

/** Linear and binary search with step counting and timing. (Member 1) */
public class SearchOperations {

    public static class SearchResult {
        public final int index;
        public final long steps;
        public final long nanos;

        SearchResult(int index, long steps, long nanos) {
            this.index = index;
            this.steps = steps;
            this.nanos = nanos;
        }
    }

    /** O(n) - works on unsorted data. Steps = comparisons made. */
    public static SearchResult linearSearch(int[] a, int key) {
        long start = System.nanoTime();
        long steps = 0;
        int found = -1;
        for (int i = 0; i < a.length; i++) {
            steps++;
            if (a[i] == key) { found = i; break; }
        }
        return new SearchResult(found, steps, System.nanoTime() - start);
    }

    /** O(log n) - requires SORTED data. Steps = comparisons made. */
    public static SearchResult binarySearch(int[] sorted, int key) {
        long start = System.nanoTime();
        long steps = 0;
        int lo = 0, hi = sorted.length - 1, found = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            steps++;
            if (sorted[mid] == key) { found = mid; break; }
            else if (sorted[mid] < key) lo = mid + 1;
            else hi = mid - 1;
        }
        return new SearchResult(found, steps, System.nanoTime() - start);
    }

    public void runMenu(ArrayOperations array) {
        while (true) {
            System.out.println("\n--------------- SEARCHING OPERATIONS ---------------");
            System.out.println("(Searches use the data currently stored in the Array component)");
            System.out.println("1. Linear Search");
            System.out.println("2. Binary Search (uses a sorted copy)");
            System.out.println("3. Compare Linear vs Binary");
            System.out.println("4. Return to Main Menu");
            int c = InputHelper.readIntInRange("Enter your choice: ", 1, 4);
            if (c == 4) return;
            if (array.isEmpty()) {
                System.out.println("  The array is empty. Add data in 'Array Operations' first.");
                continue;
            }
            int key = InputHelper.readInt("Value to search: ");
            int[] original = array.toArray();
            int[] sorted = original.clone();
            Arrays.sort(sorted);
            if (c == 1 || c == 3) {
                SearchResult r = linearSearch(original, key);
                print("Linear Search", r, "on unsorted array");
                ResultLog.add("Search", "Linear Search", r.steps, r.nanos, "n=" + original.length + ", " + (r.index >= 0 ? "found" : "not found"));
            }
            if (c == 2 || c == 3) {
                SearchResult r = binarySearch(sorted, key);
                print("Binary Search", r, "on sorted copy " + Arrays.toString(sorted));
                ResultLog.add("Search", "Binary Search", r.steps, r.nanos, "n=" + sorted.length + ", " + (r.index >= 0 ? "found" : "not found"));
            }
            if (c == 3) {
                System.out.println("  Why the difference? Linear search is O(n); binary search halves the range each step, O(log n).");
            }
        }
    }

    private void print(String name, SearchResult r, String note) {
        System.out.println("  " + name + " (" + note + ")");
        System.out.println("    Result : " + (r.index >= 0 ? "found at index " + r.index : "not found"));
        System.out.println("    Steps  : " + r.steps + "   Time: " + r.nanos + " ns");
    }
}
