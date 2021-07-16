import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Edge implements Comparable<Edge> {
	int vertex;
	int time;
	int cost;

	Edge(int vertex, int time, int cost) {
		this.vertex = vertex;
		this.time = time;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge edge) {
		if(this.time == edge.time)
			return this.cost - edge.cost;
		return this.time - edge.time;
	}
}

class Main {
	static int V;
	static int E;
	static int M;
	static int[][] dp;
	static List<Edge>[] edges;
	static int INF = 100000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int t=0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			dp = new int[V+1][M+1];
			edges = new ArrayList[V+1];
			for(int i=1; i<V+1; i++) {
				Arrays.fill(dp[i], INF);
				edges[i] = new ArrayList<>();
			}

			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				int time = Integer.parseInt(st.nextToken());

				edges[from].add(new Edge(to, time, cost));
			}
			dijkstra();
			print();
		}
	}

	private static void print() {
		Arrays.sort(dp[V]);
		System.out.println(dp[V][0] == INF ? "Poor KCM" : dp[V][0]);
	}

	private static void dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(1,0,0));
		dp[1][0] = 0;

		while(!pq.isEmpty()) {
			Edge cur = pq.poll();

			for(Edge next: edges[cur.vertex]) {
				int nextNode = next.vertex;
				int nextTime = cur.time + next.time;
				int nextCost = cur.cost + next.cost;

				if(nextCost > M) continue;

				if(dp[nextNode][nextCost] <= nextTime) continue;

				for(int i=nextCost; i<=M; i++) {
					if(dp[nextNode][i] > nextTime)
						dp[nextNode][i] = nextTime;
				}
				pq.offer(new Edge(nextNode, nextTime, nextCost));
			}
		}
	}
}