import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

class Edge implements Comparable<Edge> {
	int node1;
	int node2;
	int cost;

	Edge(int node2, int cost) {
		this.node2 = node2;
		this.cost = cost;
	}

	Edge(int node1, int node2, int cost) {
		this.node1 = node1;
		this.node2 = node2;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge edge) {
		return this.cost - edge.cost;
	}
}

class Main {
	static int N;
	static int K;
	static int[] parent;
	static PriorityQueue<Edge> edges;
	static List<Edge>[] adjList;
	static boolean[] visited;
	static long maxLength = -1;
	static int start = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		parent = new int[N];
		edges = new PriorityQueue<>();
		adjList = new ArrayList[N];

		for(int i=0; i<N; i++) {
			parent[i] = i;
			adjList[i] = new ArrayList<>();
		}

		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			edges.add(new Edge(from, to, cost));
		}

		System.out.println(kruskal());
		System.out.println(getMaxLength());
	}

	private static long kruskal() {
		long sum = 0;

		while(!edges.isEmpty()) {
			Edge cur = edges.poll();

			if(!union(cur.node1, cur.node2)) continue;

			sum += cur.cost;
			adjList[cur.node1].add(new Edge(cur.node2, cur.cost));
			adjList[cur.node2].add(new Edge(cur.node1, cur.cost));
		}

		return sum;
	}

	private static long getMaxLength() {
		visited = new boolean[N];
		visited[start] = true;
		dfs(start, 0);

		visited = new boolean[N];
		visited[start] = true;
		dfs(start, 0);

		return maxLength;
	}

	private static void dfs(int idx, long sum) {
		if(maxLength < sum) {
			maxLength = sum;
			start = idx;
		}

		for(Edge edge: adjList[idx]) {
			if(visited[edge.node2]) continue;
			visited[edge.node2] = true;
			dfs(edge.node2, sum+edge.cost);
		}
	}

	private static boolean union(int n1, int n2) {
		n1 = find(n1);
		n2 = find(n2);

		if(n1 == n2) return false;

		parent[n2] = n1;
		return true;
	}

	private static int find(int node) {
		if(parent[node] == node) return node;
		return find(parent[node]);
	}
}