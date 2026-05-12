import java.util.*;

public class TopologicalSort {

    private Map<String, List<String>> graph = new HashMap<>();
    private Set<String> allVertices = new TreeSet<>();

    public void addEdge(String u, String v) {
        graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        allVertices.add(u);
        allVertices.add(v);
    }

    private void dfsUtil(String v, Set<String> visited, Stack<String> stack) {
        visited.add(v);
        List<String> neighbors = graph.getOrDefault(v, new ArrayList<>());
        for (String neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                dfsUtil(neighbor, visited, stack);
            }
        }
        stack.push(v);
    }

    public List<String> topologicalSort() {
        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();

        for (String vertex : allVertices) {
            if (!visited.contains(vertex)) {
                dfsUtil(vertex, visited, stack);
            }
        }

        List<String> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    public static void main(String[] args) {
        TopologicalSort g = new TopologicalSort();


        String[][] edges = {
                {"m","q"}, {"m","r"}, {"m","t"}, {"m","x"},
                {"n","o"}, {"n","q"}, {"n","u"},
                {"o","r"}, {"o","s"}, {"o","v"},
                {"p","o"}, {"p","s"}, {"p","z"},
                {"q","t"},
                {"r","u"}, {"r","y"},
                {"s","r"},
                {"u","t"},
                {"v","w"}, {"v","x"}, {"v","y"},
                {"w","z"}
        };

        System.out.println("=============================================");
        System.out.println("  Topological Sort using DFS");
        System.out.println("=============================================");
        System.out.println("\nGraph edges (directed):");

        for (String[] edge : edges) {
            g.addEdge(edge[0], edge[1]);
            System.out.println("  " + edge[0] + " -> " + edge[1]);
        }

        List<String> result = g.topologicalSort();

        System.out.println("\nTopological Sort Order:");
        System.out.println(String.join(" -> ", result));
    }
}
