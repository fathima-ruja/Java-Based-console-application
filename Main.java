import java.util.NoSuchElementException;

/** Main menu and system integration. (Member 5) */
public class Main {
    public static void main(String[] args) {
        ArrayOperations array = new ArrayOperations(100);
        StackOperations stack = new StackOperations();
        QueueOperations queue = new QueueOperations();
        LinkedListOperations list = new LinkedListOperations();
        SearchOperations search = new SearchOperations();
        Graph graph = new Graph();

        try {
            while (true) {
                System.out.println("\n=============================================");
                System.out.println(" DATA STRUCTURE & GRAPH ANALYZER");
                System.out.println("=============================================");
                System.out.println("1. Array Operations");
                System.out.println("2. Stack Operations");
                System.out.println("3. Queue Operations");
                System.out.println("4. Linked List Operations");
                System.out.println("5. Searching Operations");
                System.out.println("6. Graph Operations");
                System.out.println("7. Performance Comparison");
                System.out.println("8. Display All Results");
                System.out.println("9. Exit");
                int choice = InputHelper.readIntInRange("Enter your choice: ", 1, 9);
                switch (choice) {
                    case 1: array.runMenu(); break;
                    case 2: stack.runMenu(); break;
                    case 3: queue.runMenu(); break;
                    case 4: list.runMenu(); break;
                    case 5: search.runMenu(array); break;
                    case 6: graph.runMenu(); break;
                    case 7: PerformanceComparison.run(graph); break;
                    case 8: ResultLog.displayAll(); break;
                    default:
                        System.out.println("Goodbye!");
                        return;
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("\nInput closed. Exiting.");
        }
    }
}
