import java.util.*;
    public class GraphRepresentation {
    static void adjacencyMatrix(int V, int[][] edges) {
        int[][] matrix = new int[V][V];

        for (int[] edge : edges) {
            matrix[edge[0]][edge[1]] = 1;
            matrix[edge[1]][edge[0]] = 1;
        }

        System.out.println("\n--- Adjacency Matrix ---");
        System.out.print("   ");
        for (int i = 0; i < V; i++) System.out.print("V" + i + " ");
        System.out.println();

        for (int i = 0; i < V; i++) {
            System.out.print("V" + i + " ");
            for (int j = 0; j < V; j++) {
                System.out.print(" " + matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void adjacencyList(int V, int[][] edges) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int i = 0; i < V; i++) adj.put(i, new ArrayList<>());

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        System.out.println("\n--- Adjacency List ---");
        for (int i = 0; i < V; i++) {
            System.out.print("V" + i + " -> ");
            List<Integer> neighbors = adj.get(i);
            for (int j = 0; j < neighbors.size(); j++) {
                System.out.print("V" + neighbors.get(j));
                if (j < neighbors.size() - 1) System.out.print(", ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        System.out.println("=============================================");
        System.out.println("  Graph Representation - 4 Vertex Graph");
        System.out.println("  (Each vertex connected to every other)");
        System.out.println("=============================================");

        int V = 4;
        int[][] edges = {{0,1},{0,2},{0,3},{1,2},{1,3},{2,3}};

        adjacencyMatrix(V, edges);
        adjacencyList(V, edges);

        System.out.println("\n[Other formats: Incidence Matrix, Compressed Sparse Row (CSR)]");
    }
}
