import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Edge {
	int from;
	int to;
	int cost;

	Edge(int from, int to, int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}
}

class Main {
	static int N;
	static int M;
	static Edge[] edges;
	static long INF = Long.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		edges = new Edge[M];

		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			edges[i] = new Edge(from, to, cost);
		}

		bellmanFord(1);
	}

	private static void bellmanFord(int start) {
		long[] dist = new long[N+1];
		Arrays.fill(dist, INF);
		dist[start] = 0;

		for(int n=0; n<N-1; n++) {
			for(Edge edge: edges) {
				if(dist[edge.from] == INF) continue;

				dist[edge.to] = Math.min(dist[edge.to], dist[edge.from] + edge.cost);
			}
		}

		if(hasNegativeCycle(dist)) {
			System.out.println(-1);
			return;
		}

		for(int i=2; i<N+1; i++) {
			System.out.println(dist[i]==INF ? -1 : dist[i]);
		}
	}

	private static boolean hasNegativeCycle(long[] dist) {
		for(Edge edge: edges) {
			if(dist[edge.from] == INF) continue;
			if(dist[edge.to] > dist[edge.from] + edge.cost) return true;
		}
		return false;
	}
}