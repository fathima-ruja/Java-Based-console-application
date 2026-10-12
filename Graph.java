import java.util.*;

/** Undirected graph using an adjacency list, with BFS and DFS. (Member 4) */
public class Graph {

    public static class TraversalResult {
        public final List<String> order;
        public final long steps;
        public final long nanos;

        TraversalResult(List<String> order, long steps, long nanos) {
            this.order = order;
            this.steps = steps;
            this.nanos = nanos;
        }
    }

    private final Map<String, List<String>> adj = new LinkedHashMap<>();

    public int vertexCount() { return adj.size(); }
    public boolean hasVertex(String v) { return adj.containsKey(v); }
    public String firstVertex() { return adj.isEmpty() ? null : adj.keySet().iterator().next(); }
    public void clear() { adj.clear(); }

    private static String norm(String s) { return s.trim().toUpperCase(); }

    public boolean addVertex(String v) {
        v = norm(v);
        if (v.isEmpty() || adj.containsKey(v)) return false;
        adj.put(v, new ArrayList<>());
        return true;
    }

    /** Returns null on success, otherwise an error message. */
    public String addEdge(String a, String b) {
        a = norm(a);
        b = norm(b);
        if (!adj.containsKey(a)) return "Vertex " + a + " does not exist.";
        if (!adj.containsKey(b)) return "Vertex " + b + " does not exist.";
        if (a.equals(b)) return "Self-loops are not allowed.";
        if (adj.get(a).contains(b)) return "Edge " + a + " - " + b + " already exists.";
        adj.get(a).add(b);
        adj.get(b).add(a);
        return null;
    }

    public int edgeCount() {
        int total = 0;
        for (List<String> l : adj.values()) total += l.size();
        return total / 2;
    }

    public String toDisplayText() {
        if (adj.isEmpty()) return "Graph is empty.";
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices: ").append(adj.size()).append("   Edges: ").append(edgeCount()).append("\n");
        for (Map.Entry<String, List<String>> e : adj.entrySet()) {
            sb.append(e.getKey()).append(" -> ")
              .append(e.getValue().isEmpty() ? "(no neighbours)" : e.getValue())
              .append("\n");
        }
        return sb.toString().trim();
    }

    public void display() {
        String text = toDisplayText();
        System.out.println("  " + text.replace("\n", "\n  "));
    }

    /** BFS: O(V + E). Steps = vertices visited + edges examined. */
    public TraversalResult bfs(String start) {
        start = norm(start);
        if (!adj.containsKey(start)) {
            throw new IllegalArgumentException("Vertex " + start + " does not exist.");
        }

        long t0 = System.nanoTime();
        long steps = 0;
        List<String> order = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.add(start);
        seen.add(start);
        while (!q.isEmpty()) {
            String cur = q.poll();
            order.add(cur);
            steps++;
            List<String> neighbors = adj.getOrDefault(cur, new ArrayList<>());
            for (String nb : neighbors) {
                steps++;
                if (seen.add(nb)) q.add(nb);
            }
        }
        return new TraversalResult(order, steps, System.nanoTime() - t0);
    }

    /** DFS (iterative, stack): O(V + E). Same step definition as BFS. */
    public TraversalResult dfs(String start) {
        start = norm(start);
        if (!adj.containsKey(start)) {
            throw new IllegalArgumentException("Vertex " + start + " does not exist.");
        }

        long t0 = System.nanoTime();
        long steps = 0;
        List<String> order = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Deque<String> stack = new ArrayDeque<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            String cur = stack.pop();
            if (!visited.add(cur)) continue;
            order.add(cur);
            steps++;
            List<String> nbs = adj.getOrDefault(cur, new ArrayList<>());
            for (int i = nbs.size() - 1; i >= 0; i--) { // reversed so the first neighbour is visited first
                steps++;
                if (!visited.contains(nbs.get(i))) stack.push(nbs.get(i));
            }
        }
        return new TraversalResult(order, steps, System.nanoTime() - t0);
    }

    /** Is there a path from -> to? (BFS based) */
    public boolean pathExists(String from, String to) {
        from = norm(from);
        to = norm(to);
        if (!adj.containsKey(from) || !adj.containsKey(to)) return false;
        return bfs(from).order.contains(to);
    }

    public void loadSample() {
        clear();
        for (String v : new String[]{"A", "B", "C", "D", "E", "F", "G", "H"}) addVertex(v);
        String[][] edges = {{"A", "B"}, {"A", "C"}, {"B", "D"}, {"B", "E"}, {"C", "F"}, {"C", "G"}, {"E", "H"}, {"F", "H"}};
        for (String[] e : edges) addEdge(e[0], e[1]);
    }

    public void runMenu() {
        while (true) {
            System.out.println("\n--------------- GRAPH OPERATIONS ---------------");
            System.out.println("1. Add Vertex");
            System.out.println("2. Add Edge");
            System.out.println("3. Display Graph");
            System.out.println("4. BFS Traversal");
            System.out.println("5. DFS Traversal");
            System.out.println("6. Search Vertex / Check Path");
            System.out.println("7. Load Sample Graph");
            System.out.println("8. Return to Main Menu");
            int c = InputHelper.readIntInRange("Enter your choice: ", 1, 8);
            switch (c) {
                case 1: {
                    String v = InputHelper.readNonEmpty("Vertex name: ");
                    System.out.println(addVertex(v) ? "  Added vertex " + norm(v) : "  Vertex already exists.");
                    break;
                }
                case 2: {
                    if (vertexCount() < 2) { System.out.println("  Add at least two vertices first."); break; }
                    String a = InputHelper.readNonEmpty("From vertex: ");
                    String b = InputHelper.readNonEmpty("To vertex: ");
                    String err = addEdge(a, b);
                    System.out.println(err == null ? "  Added edge " + norm(a) + " - " + norm(b) : "  " + err);
                    break;
                }
                case 3: display(); break;
                case 4:
                case 5: {
                    if (adj.isEmpty()) { System.out.println("  Graph is empty."); break; }
                    String s = InputHelper.readNonEmpty("Start vertex: ");
                    String normalizedStart = norm(s);
                    if (!hasVertex(normalizedStart)) { System.out.println("  Vertex not found."); break; }
                    boolean isBfs = (c == 4);
                    TraversalResult r = isBfs ? bfs(normalizedStart) : dfs(normalizedStart);
                    String name = isBfs ? "BFS" : "DFS";
                    System.out.println("  " + name + " order: " + String.join(" -> ", r.order));
                    System.out.println("  Steps: " + r.steps + "   Time: " + r.nanos + " ns");
                    ResultLog.add("Graph Traversal", name, r.steps, r.nanos, "start=" + normalizedStart + ", visited " + r.order.size());
                    break;
                }
                case 6: {
                    if (adj.isEmpty()) { System.out.println("  Graph is empty."); break; }
                    String a = InputHelper.readNonEmpty("Start vertex: ");
                    String b = InputHelper.readNonEmpty("Vertex to find: ");
                    if (!hasVertex(norm(a))) { System.out.println("  Start vertex not found."); break; }
                    if (!hasVertex(norm(b))) { System.out.println("  Vertex " + norm(b) + " is not in the graph."); break; }
                    System.out.println(pathExists(a, b) ? "  " + norm(b) + " is reachable from " + norm(a) + "." : "  " + norm(b) + " is NOT reachable from " + norm(a) + ".");
                    break;
                }
                case 7:
                    loadSample();
                    System.out.println("  Sample graph loaded (8 vertices, 8 edges).");
                    break;
                default: return;
            }
        }
    }
}
