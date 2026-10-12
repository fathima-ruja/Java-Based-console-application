/** Singly linked list with insert / delete / search / display. (Member 3) */
public class LinkedListOperations {

    private static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    private Node head;
    private int size;
    private long stepCount;

    public int size() { return size; }
    public boolean isEmpty() { return head == null; }
    public long getStepCount() { return stepCount; }
    public void resetStepCount() { stepCount = 0; }
    public void clear() { head = null; size = 0; }

    /** O(1). */
    public void insertAtBeginning(int value) {
        Node n = new Node(value);
        n.next = head;
        head = n;
        size++;
        stepCount++;
    }

    /** O(n). */
    public void insertAtEnd(int value) {
        Node n = new Node(value);
        stepCount++;
        if (head == null) { head = n; size++; return; }
        Node cur = head;
        while (cur.next != null) { cur = cur.next; stepCount++; }
        cur.next = n;
        size++;
    }

    /** O(n). Position is 1-based. */
    public boolean insertAtPosition(int pos, int value) {
        if (pos < 1 || pos > size + 1) return false;
        if (pos == 1) { insertAtBeginning(value); return true; }
        Node cur = head;
        for (int i = 1; i < pos - 1; i++) { cur = cur.next; stepCount++; }
        Node n = new Node(value);
        n.next = cur.next;
        cur.next = n;
        size++;
        stepCount++;
        return true;
    }

    /** O(n). Deletes the first node holding value. */
    public boolean deleteValue(int value) {
        if (head == null) return false;
        stepCount++;
        if (head.data == value) { head = head.next; size--; return true; }
        Node cur = head;
        while (cur.next != null) {
            stepCount++;
            if (cur.next.data == value) { cur.next = cur.next.next; size--; return true; }
            cur = cur.next;
        }
        return false;
    }

    /** O(1). */
    public boolean deleteFirst() {
        if (head == null) return false;
        head = head.next;
        size--;
        stepCount++;
        return true;
    }

    /** O(n). Returns 1-based position or -1. */
    public int search(int value) {
        Node cur = head;
        int pos = 1;
        while (cur != null) {
            stepCount++;
            if (cur.data == value) return pos;
            cur = cur.next;
            pos++;
        }
        return -1;
    }

    public void display() {
        if (head == null) { System.out.println("  Linked list is empty."); return; }
        StringBuilder sb = new StringBuilder("  HEAD -> ");
        for (Node cur = head; cur != null; cur = cur.next) sb.append(cur.data).append(" -> ");
        System.out.println(sb.append("null").append("   (size ").append(size).append(")"));
    }

    public void runMenu() {
        while (true) {
            System.out.println("\n--------------- LINKED LIST OPERATIONS ---------------");
            System.out.println("1. Insert at Beginning");
            System.out.println("2. Insert at End");
            System.out.println("3. Insert at Position");
            System.out.println("4. Delete First Node");
            System.out.println("5. Delete by Value");
            System.out.println("6. Search");
            System.out.println("7. Display List");
            System.out.println("8. Return to Main Menu");
            int c = InputHelper.readIntInRange("Enter your choice: ", 1, 8);
            switch (c) {
                case 1: {
                    int v = InputHelper.readInt("Value: ");
                    insertAtBeginning(v);
                    System.out.println("  Inserted " + v + " at the beginning.");
                    break;
                }
                case 2: {
                    int v = InputHelper.readInt("Value: ");
                    insertAtEnd(v);
                    System.out.println("  Inserted " + v + " at the end.");
                    break;
                }
                case 3: {
                    int pos = InputHelper.readInt("Position (1-" + (size + 1) + "): ");
                    int v = InputHelper.readInt("Value: ");
                    System.out.println(insertAtPosition(pos, v) ? "  Inserted " + v + " at position " + pos : "  Invalid position.");
                    break;
                }
                case 4:
                    System.out.println(deleteFirst() ? "  First node deleted." : "  List is empty - nothing to delete.");
                    break;
                case 5: {
                    if (isEmpty()) { System.out.println("  List is empty - nothing to delete."); break; }
                    int v = InputHelper.readInt("Value to delete: ");
                    System.out.println(deleteValue(v) ? "  Deleted " + v : "  Value " + v + " not found.");
                    break;
                }
                case 6: {
                    if (isEmpty()) { System.out.println("  List is empty."); break; }
                    int v = InputHelper.readInt("Value to search: ");
                    long before = stepCount;
                    int pos = search(v);
                    System.out.println(pos < 0 ? "  Not found." : "  Found at position " + pos + ".");
                    System.out.println("  Steps taken: " + (stepCount - before));
                    break;
                }
                case 7: display(); break;
                default: return;
            }
        }
    }
}
