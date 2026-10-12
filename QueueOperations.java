/** Circular-array queue (FIFO) that grows automatically. (Member 2) */
public class QueueOperations {
    private int[] items = new int[5];
    private int front = 0;
    private int count = 0;

    public boolean isEmpty() { return count == 0; }
    public int size() { return count; }

    /** O(1) amortised. */
    public void enqueue(int value) {
        if (count == items.length) grow();
        items[(front + count) % items.length] = value;
        count++;
    }

    /** O(1). Throws if the queue is empty (underflow). */
    public int dequeue() {
        if (isEmpty()) throw new IllegalStateException("Queue underflow: cannot dequeue from an empty queue.");
        int v = items[front];
        front = (front + 1) % items.length;
        count--;
        return v;
    }

    /** O(1). */
    public int peek() {
        if (isEmpty()) throw new IllegalStateException("Queue is empty: nothing at the front.");
        return items[front];
    }

    private void grow() {
        int[] bigger = new int[items.length * 2];
        for (int i = 0; i < count; i++) bigger[i] = items[(front + i) % items.length];
        items = bigger;
        front = 0;
    }

    public void display() {
        if (isEmpty()) { System.out.println("  Queue is empty."); return; }
        StringBuilder sb = new StringBuilder("  FRONT -> ");
        for (int i = 0; i < count; i++) {
            sb.append(items[(front + i) % items.length]);
            if (i < count - 1) sb.append(" | ");
        }
        System.out.println(sb.append(" <- REAR"));
    }

    public void runMenu() {
        while (true) {
            System.out.println("\n--------------- QUEUE OPERATIONS ---------------");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Peek / Front");
            System.out.println("4. Display Queue");
            System.out.println("5. Return to Main Menu");
            int c = InputHelper.readIntInRange("Enter your choice: ", 1, 5);
            try {
                switch (c) {
                    case 1: {
                        int v = InputHelper.readInt("Value to enqueue: ");
                        enqueue(v);
                        System.out.println("  Enqueued " + v);
                        break;
                    }
                    case 2: System.out.println("  Dequeued " + dequeue()); break;
                    case 3: System.out.println("  Front element: " + peek()); break;
                    case 4: display(); break;
                    default: return;
                }
            } catch (IllegalStateException e) {
                System.out.println("  Error: " + e.getMessage());
            }
        }
    }
}
