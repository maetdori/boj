import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Edge {
	int dest;
	int weight;

	Edge(int dest, int weight) {
		this.dest = dest;
		this.weight = weight;
	}
}

class Main {
	static int V;
	static int E;
	static int start;
	static int[] distance;
	static boolean[] visited;
	static List<Edge>[] connected;
	static final int INF = 10000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(br.readLine());
		distance = new int[V+1];
		visited = new boolean[V+1];
		connected = new ArrayList[V+1];

		Arrays.fill(distance, INF);
		distance[start] = 0;

		for(int i=1; i<V+1; i++) {
			connected[i] = new ArrayList<>();
		}

		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			connected[from].add(new Edge(to, weight));
		}

		dijkstra();

		for(int i=1; i<V+1; i++) {
			if (distance[i] == INF)
				System.out.println("INF");
			else System.out.println(distance[i]);
		}
	}

	private static void dijkstra() {
		for(int i=0; i<V; i++) {
			int next = closestIndex();
			visited[next] = true;
			for(Edge edge: connected[next]) {
				int v = edge.dest;
				int w = edge.weight;
				distance[v] = Math.min(distance[v], distance[next] + w);
			}
		}
	}

	private static int closestIndex() {
		int min = INF;
		int minIdx = -1;

		for(int i=1; i<V+1; i++) {
			if(visited[i]) continue;
			if(distance[i] <= min) {
				min = distance[i];
				minIdx = i;
			}
		}
		return minIdx;
	}
}
