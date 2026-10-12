import java.util.Arrays;
import java.util.Random;

/** Fixed-capacity array with insert / delete / search / display. (Member 1) */
public class ArrayOperations {
    private final int[] data;
    private int size;
    private long stepCount; // counts element reads/writes/shifts for complexity demos

    public ArrayOperations(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity must be positive");
        data = new int[capacity];
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    public boolean isFull() { return size == data.length; }
    public long getStepCount() { return stepCount; }
    public void resetStepCount() { stepCount = 0; }
    public int[] toArray() { return Arrays.copyOf(data, size); }
    public void clear() { size = 0; }

    /** O(1): append at end. */
    public boolean insert(int value) {
        if (isFull()) return false;
        data[size++] = value;
        stepCount++;
        return true;
    }

    /** O(n): insert at index, shifting elements right. */
    public boolean insertAt(int index, int value) {
        if (isFull() || index < 0 || index > size) return false;
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
            stepCount++;
        }
        data[index] = value;
        size++;
        stepCount++;
        return true;
    }

    /** O(n): delete at index, shifting elements left. */
    public boolean deleteAt(int index) {
        if (index < 0 || index >= size) return false;
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
            stepCount++;
        }
        size--;
        stepCount++;
        return true;
    }

    /** O(n): linear search, returns index or -1. */
    public int indexOf(int value) {
        for (int i = 0; i < size; i++) {
            stepCount++;
            if (data[i] == value) return i;
        }
        return -1;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("  Array is empty.");
            return;
        }
        System.out.println("  " + Arrays.toString(toArray()) + "  (size " + size + " / capacity " + data.length + ")");
    }

    public void runMenu() {
        while (true) {
            System.out.println("\n--------------- ARRAY OPERATIONS ---------------");
            System.out.println("1. Insert at End");
            System.out.println("2. Insert at Index");
            System.out.println("3. Delete by Index");
            System.out.println("4. Delete by Value");
            System.out.println("5. Search (Linear)");
            System.out.println("6. Display Array");
            System.out.println("7. Fill with Random Numbers");
            System.out.println("8. Return to Main Menu");
            int c = InputHelper.readIntInRange("Enter your choice: ", 1, 8);
            switch (c) {
                case 1: {
                    int v = InputHelper.readInt("Value: ");
                    System.out.println(insert(v) ? "  Inserted " + v : "  Array is full (capacity " + data.length + ").");
                    break;
                }
                case 2: {
                    if (isFull()) { System.out.println("  Array is full."); break; }
                    int idx = InputHelper.readInt("Index (0-" + size + "): ");
                    int v = InputHelper.readInt("Value: ");
                    System.out.println(insertAt(idx, v) ? "  Inserted " + v + " at index " + idx : "  Invalid index.");
                    break;
                }
                case 3: {
                    if (isEmpty()) { System.out.println("  Array is empty - nothing to delete."); break; }
                    int idx = InputHelper.readInt("Index (0-" + (size - 1) + "): ");
                    System.out.println(deleteAt(idx) ? "  Deleted element at index " + idx : "  Invalid index.");
                    break;
                }
                case 4: {
                    if (isEmpty()) { System.out.println("  Array is empty - nothing to delete."); break; }
                    int v = InputHelper.readInt("Value to delete: ");
                    int idx = indexOf(v);
                    if (idx < 0) System.out.println("  Value " + v + " not found.");
                    else { deleteAt(idx); System.out.println("  Deleted " + v + " (was at index " + idx + ")"); }
                    break;
                }
                case 5: {
                    if (isEmpty()) { System.out.println("  Array is empty."); break; }
                    int v = InputHelper.readInt("Value to search: ");
                    long before = stepCount;
                    int idx = indexOf(v);
                    System.out.println(idx < 0 ? "  Not found." : "  Found at index " + idx + ".");
                    System.out.println("  Steps taken: " + (stepCount - before));
                    break;
                }
                case 6:
                    display();
                    break;
                case 7: {
                    int room = data.length - size;
                    if (room == 0) { System.out.println("  Array is full."); break; }
                    int n = InputHelper.readIntInRange("How many numbers (1-" + room + "): ", 1, room);
                    Random r = new Random();
                    for (int i = 0; i < n; i++) insert(r.nextInt(100));
                    display();
                    break;
                }
                default:
                    return;
            }
        }
    }
}
