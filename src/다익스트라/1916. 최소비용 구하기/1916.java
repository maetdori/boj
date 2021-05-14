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
	static int N;
	static int M;
	static List<Edge>[] bus;
	static final int INF = 100_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine()) + 1;
		M = Integer.parseInt(br.readLine());
		bus = new ArrayList[N];

		for(int i=1; i<N; i++) {
			bus[i] = new ArrayList<>();
		}

		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			bus[from].add(new Edge(to, cost));
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int dest = Integer.parseInt(st.nextToken());

		System.out.println(minimalCost(start,dest));
	}

	private static int minimalCost(int start, int target) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		int[] cost = new int[N];
		Arrays.fill(cost, INF);
		cost[start] = 0;
		pq.offer(new Edge(start,0));

		while(!pq.isEmpty()) {
			Edge cheapest = pq.poll();
			int cur = cheapest.vertex;

			for(Edge next: bus[cur]) {
				if(cost[next.vertex] > cost[cur] + next.cost) {
					cost[next.vertex] = cost[cur] + next.cost;
					pq.offer(next);
				}
			}
		}
		return cost[target];
	}
}