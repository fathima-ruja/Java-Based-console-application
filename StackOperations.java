import java.util.Arrays;

/** Array-based stack (LIFO) that grows automatically. (Member 2) */
public class StackOperations {
    private int[] items = new int[5];
    private int top = -1;

    public boolean isEmpty() { return top == -1; }
    public int size() { return top + 1; }

    /** O(1) amortised. */
    public void push(int value) {
        if (top == items.length - 1) items = Arrays.copyOf(items, items.length * 2);
        items[++top] = value;
    }

    /** O(1). Throws if the stack is empty (underflow). */
    public int pop() {
        if (isEmpty()) throw new IllegalStateException("Stack underflow: cannot pop from an empty stack.");
        return items[top--];
    }

    /** O(1). */
    public int peek() {
        if (isEmpty()) throw new IllegalStateException("Stack is empty: nothing to peek.");
        return items[top];
    }

    public void display() {
        if (isEmpty()) { System.out.println("  Stack is empty."); return; }
        System.out.println("  TOP -> " + joinTopDown() + " <- BOTTOM");
    }

    private String joinTopDown() {
        StringBuilder sb = new StringBuilder();
        for (int i = top; i >= 0; i--) {
            sb.append(items[i]);
            if (i > 0) sb.append(" | ");
        }
        return sb.toString();
    }

    public void runMenu() {
        while (true) {
            System.out.println("\n--------------- STACK OPERATIONS ---------------");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Display Stack");
            System.out.println("5. Return to Main Menu");
            int c = InputHelper.readIntInRange("Enter your choice: ", 1, 5);
            try {
                switch (c) {
                    case 1: {
                        int v = InputHelper.readInt("Value to push: ");
                        push(v);
                        System.out.println("  Pushed " + v);
                        break;
                    }
                    case 2: System.out.println("  Popped " + pop()); break;
                    case 3: System.out.println("  Top element: " + peek()); break;
                    case 4: display(); break;
                    default: return;
                }
            } catch (IllegalStateException e) {
                System.out.println("  Error: " + e.getMessage());
            }
        }
    }
}
