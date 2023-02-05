import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    private static final int INF = Integer.MAX_VALUE;
    private static int vertexCnt;
    private static int edgeCnt;
    private static int targetDist;
    private static int startingPoint;
    private static List<Edge>[] edgeFrom;
    private static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        vertexCnt = Integer.parseInt(st.nextToken()) + 1;
        edgeCnt = Integer.parseInt(st.nextToken());
        targetDist = Integer.parseInt(st.nextToken());
        startingPoint = Integer.parseInt(st.nextToken());

        edgeFrom = new ArrayList[vertexCnt];
        dist = new int[vertexCnt];

        for (int i = 1; i < vertexCnt; i++) {
            edgeFrom[i] = new ArrayList<>();
        }

        for (int i = 0; i < edgeCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int vertex1 = Integer.parseInt(st.nextToken());
            int vertex2 = Integer.parseInt(st.nextToken());
            edgeFrom[vertex1].add(new Edge(vertex2, 1));
        }

        dijkstra();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < vertexCnt; i++) {
            if (dist[i] == targetDist) {
                sb.append(i).append('\n');
            }
        }

        if (sb.length() == 0) {
            System.out.println(-1);
        } else {
            System.out.println(sb);
        }
    }

    public static void dijkstra() {
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        boolean[] visited = new boolean[vertexCnt];

        Arrays.fill(dist, INF);
        dist[startingPoint] = 0;

        queue.offer(new Edge(startingPoint, 0));

        while (!queue.isEmpty()) {
            Edge cheapest = queue.poll();
            int currentVertex = cheapest.vertex;

            if (visited[currentVertex]) continue;

            visited[currentVertex] = true;

            for (Edge next : edgeFrom[currentVertex]) {
                if (!visited[next.vertex] && dist[next.vertex] > dist[currentVertex] + next.cost) {
                    dist[next.vertex] = dist[currentVertex] + next.cost;
                    queue.offer(new Edge(next.vertex, dist[next.vertex]));
                }
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int vertex;
        int cost;

        public Edge(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.cost - edge.cost;
        }
    }
}