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
	static List<Edge>[] edges;
	static int INF = 100000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		edges = new ArrayList[N+1];

		for(int i=1; i<N+1; i++) edges[i] = new ArrayList<>();

		StringTokenizer st;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			edges[from].add(new Edge(to, cost));
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		System.out.println(getMinimalCost(start, end));
	}

	private static int getMinimalCost(int start, int end) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		int[] dist = new int[N+1];
		Arrays.fill(dist, INF);

		dist[start] = 0;
		pq.offer(new Edge(start, 0));

		while(!pq.isEmpty()) {
			Edge cur = pq.poll();

			// 1 2 10
			// 1 2 2
			// 위와 같이 같은 edge에 다른 가중치가 부과될 수 있는데,
			// 더 큰 가중치가 앞에 올 경우 시간초과가 발생할 수 있다.
			// 따라서 다음 한 줄을 삽입하지 않으면 시간초과가 발생
			if(dist[cur.vertex] < cur.cost) continue;

			for(Edge next: edges[cur.vertex]) {
				if(dist[next.vertex] > dist[cur.vertex] + next.cost) {
					dist[next.vertex] = dist[cur.vertex] + next.cost;
					pq.offer(next);
				}
			}
		}

		return dist[end];
	}
}