import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Edge implements Comparable<Edge> {
	int vertex;
	int cost;

	Edge(int vertex, int cost) {
		this.vertex = vertex;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge edge) {
		return this.cost - edge.cost;
	}
}

class Main {
	static int V;
	static int E;
	static List<Edge>[] edgeFrom;
	static final int INF = 200_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken()) + 1;
		E = Integer.parseInt(st.nextToken());
		edgeFrom = new ArrayList[V];
		for(int i=0; i<V; i++) {
			edgeFrom[i] = new ArrayList<>();
		}

		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			edgeFrom[node1].add(new Edge(node2, cost));
			edgeFrom[node2].add(new Edge(node1, cost));
		}

		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());

		int path1 = getShortestPath(1,v1) + getShortestPath(v1,v2) + getShortestPath(v2,V-1);
		int path2 = getShortestPath(1,v2) + getShortestPath(v2,v1) + getShortestPath(v1,V-1);

		int shortestPath = Math.min(path1, path2);

		if(shortestPath >= INF) System.out.println(-1);
		else System.out.println(shortestPath);
	}

	public static int getShortestPath(int start, int target) {
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		int[] dist = new int[V];
		Arrays.fill(dist, INF);
		dist[start] = 0;

		queue.offer(new Edge(start, 0));

		while(!queue.isEmpty()) {
			Edge cheapest = queue.poll();
			int cur = cheapest.vertex;

			for (Edge next : edgeFrom[cur]) {
				if (dist[next.vertex] > dist[cur] + next.cost) {
					dist[next.vertex] = dist[cur] + next.cost;
					queue.offer(next);
				}
			}
		}
		return dist[target];
	}
}