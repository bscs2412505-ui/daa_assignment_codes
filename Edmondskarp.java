import java.util.*;

public class Edmondskarp {

    static int V;
    static String[] names = {"A", "B", "C", "D", "E", "F", "G"};

    static boolean bfs(int[][] residual, int s, int t, int[] parent) {
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v = 0; v < V; v++) {
                if (!visited[v] && residual[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                    if (v == t) return true;
                }
            }
        }
        return false;
    }

    static int edmondsKarp(int[][] capacity, int s, int t) {
        V = capacity.length;
        int[][] residual = new int[V][V];
        for (int i = 0; i < V; i++)
            residual[i] = capacity[i].clone();

        int[] parent = new int[V];
        int maxFlow = 0;

        while (bfs(residual, s, t, parent)) {
            int pathFlow = Integer.MAX_VALUE;
            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, residual[u][v]);
            }

            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                residual[u][v] -= pathFlow;
                residual[v][u] += pathFlow;
            }

            List<Integer> path = new ArrayList<>();
            for (int v = t; v != s; v = parent[v]) path.add(v);
            path.add(s);
            Collections.reverse(path);
            StringBuilder sb = new StringBuilder();
            for (int node : path) sb.append(names[node]).append(" -> ");
            sb.setLength(sb.length() - 4);
            System.out.println("Augmenting path: " + sb + "   | bottleneck flow = " + pathFlow);

            maxFlow += pathFlow;
        }

        boolean[] visited = new boolean[V];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(s);
        visited[s] = true;
        while (!stack.isEmpty()) {
            int u = stack.pop();
            for (int v = 0; v < V; v++) {
                if (!visited[v] && residual[u][v] > 0) {
                    visited[v] = true;
                    stack.push(v);
                }
            }
        }

        System.out.println("\nNodes reachable from source A after max flow (S-side of min-cut):");
        for (int i = 0; i < V; i++) if (visited[i]) System.out.print(names[i] + " ");
        System.out.println();

        System.out.println("\nMinimum-cut edges (capacity = flow on these edges):");
        for (int u = 0; u < V; u++) {
            for (int v = 0; v < V; v++) {
                if (visited[u] && !visited[v] && capacity[u][v] > 0) {
                    System.out.println("  " + names[u] + " -> " + names[v] + "  (capacity " + capacity[u][v] + ")");
                }
            }
        }

        return maxFlow;
    }

    public static void main(String[] args) {
        int n = 7;
        int[][] capacity = new int[n][n];

        int A=0, B=1, C=2, D=3, E=4, F=5, G=6;

        capacity[A][D] = 3;
        capacity[A][C] = 3;
        capacity[A][B] = 3;
        capacity[C][D] = 1;
        capacity[C][B] = 4;
        capacity[C][E] = 2;
        capacity[D][F] = 6;
        capacity[D][E] = 2;
        capacity[F][G] = 9;
        capacity[E][G] = 1;
        capacity[B][E] = 1;

        System.out.println("=== Edmonds-Karp Algorithm: Maximum Flow from A to G ===\n");
        int maxFlow = edmondsKarp(capacity, A, G);
        System.out.println("\nMAXIMUM FLOW from A to G = " + maxFlow);
    }
}