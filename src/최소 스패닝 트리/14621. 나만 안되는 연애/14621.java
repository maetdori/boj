import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Edge implements Comparable<Edge> {
	int node1;
	int node2;
	int cost;

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
	static char[] sex;
	static int[] parent;
	static PriorityQueue<Edge> edges = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		sex = new char[N+1];
		parent = new int[N+1];

		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			sex[i] = st.nextToken().charAt(0);
			parent[i] = i;
		}

		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			if(sex[n1] == sex[n2]) continue;

			edges.offer(new Edge(n1,n2,cost));
		}

		System.out.println(getMinimalCost());
	}

	private static int getMinimalCost() {
		int cnt = 0;
		int cost = 0;

		while(!edges.isEmpty()) {
			Edge edge = edges.poll();

			if(!union(edge.node1, edge.node2)) continue;

			cost += edge.cost;
			cnt++;
		}

		if(cnt != N-1) return -1;
		return cost;
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
		return parent[node] = find(parent[node]);
	}
}