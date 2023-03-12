import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    private static int N;
    private static int M;
    private static int X;
    private static List<Node>[] adjList;
    private static List<Node>[] reversedAdjList;
    private static int[] dist;
    private static int[] reversedDist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        adjList = new List[N + 1];
        reversedAdjList = new List[N + 1];
        dist = new int[N + 1];
        reversedDist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(reversedDist, Integer.MAX_VALUE);

        for (int i = 0; i < N + 1; i++) {
            adjList[i] = new ArrayList<>();
            reversedAdjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adjList[n1].add(new Node(n2, cost));
            reversedAdjList[n2].add(new Node(n1, cost));
        }

        dijkstra(adjList, dist, X);
        dijkstra(reversedAdjList, reversedDist, X);

        int ans = -1;
        for (int i = 1; i <= N; i++) {
            ans = Math.max(ans, dist[i] + reversedDist[i]);
        }
        System.out.println(ans);
    }

    private static void dijkstra(List<Node>[] list, int[] distArr, int start) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        boolean[] visited = new boolean[N + 1];

        queue.offer(new Node(start, 0));
        distArr[start] = 0;

        while (!queue.isEmpty()) {
            Node n1 = queue.poll();

            if (visited[n1.dest]) continue;
            visited[n1.dest] = true;

            for (Node n2 : list[n1.dest]) {
                if (distArr[n2.dest] > distArr[n1.dest] + n2.dist) {
                    distArr[n2.dest] = distArr[n1.dest] + n2.dist;
                    queue.offer(new Node(n2.dest, distArr[n2.dest]));
                }
            }
        }
    }

    private static class Node implements Comparable<Node> {
        public int dest;
        public int dist;

        Node(int dest, int dist) {
            this.dest = dest;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node node) {
            return this.dist - node.dist;
        }
    }
}