import java.util.*;

public class Dijkstra {

    static class Edge {
        int to, weight;
        Edge(int to, int weight) { this.to = to; this.weight = weight; }
    }

    public static void main(String[] args) {
        int V = 9; // vertices 0..8
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) graph.add(new ArrayList<>());

        int[][] edges = {
                {0,1,4}, {0,7,8},
                {1,2,8}, {1,7,11},
                {2,3,7}, {2,8,2}, {2,5,4},
                {3,4,9}, {3,5,14},
                {4,5,10},
                {5,6,2},
                {6,7,1}, {6,8,6},
                {7,8,7}
        };

        for (int[] e : edges) {
            graph.get(e[0]).add(new Edge(e[1], e[2]));
            graph.get(e[1]).add(new Edge(e[0], e[2]));
        }

        int src = 0;
        int[] dist = new int[V];
        int[] parent = new int[V];
        boolean[] visited = new boolean[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        dist[src] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.add(new int[]{0, src});

        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int d = top[0], u = top[1];
            if (visited[u]) continue;
            visited[u] = true;

            for (Edge e : graph.get(u)) {
                if (!visited[e.to] && dist[u] + e.weight < dist[e.to]) {
                    dist[e.to] = dist[u] + e.weight;
                    parent[e.to] = u;
                    pq.add(new int[]{dist[e.to], e.to});
                }
            }
        }

        System.out.println("=== Dijkstra's Algorithm: Shortest Paths from Source Node 0 ===\n");
        System.out.println("Node\tDistance from 0\tParent (Shortest Path Tree)");
        for (int i = 0; i < V; i++) {
            System.out.println(i + "\t" + dist[i] + "\t\t" + (parent[i] == -1 ? "-" : parent[i]));
        }

        System.out.println("\nShortest Path Tree edges:");
        for (int i = 0; i < V; i++) {
            if (parent[i] != -1) {
                System.out.println("  " + parent[i] + " -> " + i + "  (edge weight " + (dist[i] - dist[parent[i]]) + ")");
            }
        }

        System.out.println("\nShortest path from 0 to each node:");
        for (int i = 0; i < V; i++) {
            System.out.println("  0 -> " + i + " : " + buildPath(parent, i) + "   (distance = " + dist[i] + ")");
        }
    }

    static String buildPath(int[] parent, int node) {
        LinkedList<Integer> path = new LinkedList<>();
        for (int v = node; v != -1; v = parent[v]) path.addFirst(v);
        StringBuilder sb = new StringBuilder();
        for (int v : path) sb.append(v).append(" -> ");
        sb.setLength(Math.max(sb.length() - 4, 0));
        return sb.toString();
    }
}