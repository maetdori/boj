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
	static int start;
	static int g;
	static int h;
	static int ghCost;
	static List<Integer> dest;
	static List<Edge>[] edges;
	static int INF = 2000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());

		for(int t=0; t<tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			int candi = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			dest = new ArrayList<>();
			edges = new ArrayList[V+1];
			for(int i=1; i<V+1; i++) edges[i] = new ArrayList<>();

			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int node1 = Integer.parseInt(st.nextToken());
				int node2 = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());

				if((node1==g && node2==h) || (node1==h && node2==g))
					ghCost = cost;

				edges[node1].add(new Edge(node2, cost));
				edges[node2].add(new Edge(node1, cost));
			}

			for(int i=0; i<candi; i++) {
				int candidate = Integer.parseInt(br.readLine());
				if(goToDest(candidate))
					dest.add(candidate);
			}

			Collections.sort(dest);
			for(int node: dest) {
				System.out.print(node + " ");
			}System.out.println();
		}
	}

	private static boolean goToDest(int dest) {
		int minCost = dijkstra(start, dest);
		int route1 = dijkstra(start, g) + ghCost + dijkstra(h, dest); 
		int route2 = dijkstra(start, h) + ghCost + dijkstra(g, dest);

		if(minCost == Math.min(route1, route2)) return true;
		return false;
	}

	private static int dijkstra(int start, int dest) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		int[] dist = new int[V+1];

		Arrays.fill(dist, INF);
		dist[start] = 0;
		pq.offer(new Edge(start, 0));

		while(!pq.isEmpty()) {
			Edge cur = pq.poll();

			for(Edge next: edges[cur.vertex]) {
				if(dist[next.vertex] > dist[cur.vertex] + next.cost) {
					dist[next.vertex] = dist[cur.vertex] + next.cost;
					pq.offer(next);
				}
			}
		}
		return dist[dest];
	}
}