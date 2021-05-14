import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Edge implements Comparable<Edge> {
	int v1;
	int v2;
	int cost;

	Edge(int v1, int v2, int cost) {
		this.v1 = v1;
		this.v2 = v2;
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
	static int[] parent;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken()) + 1;
		E = Integer.parseInt(st.nextToken());
		parent = new int[V];

		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			pq.add(new Edge(v1,v2,cost));
		}

		System.out.println(kruskal());
	}

	private static int kruskal() {
		for(int i=1; i<V; i++) {
			parent[i] = i;
		}

		int sum = 0;
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			int v1 = edge.v1;
			int v2 = edge.v2;
			int cost = edge.cost;

			int v1Parent = findParent(v1);
			int v2Parent = findParent(v2);

			if(v1Parent == v2Parent) continue;

			sum += cost;
			if(v1Parent < v2Parent)
				parent[v2Parent] = v1Parent;
			else parent[v1Parent] = v2Parent;
		}
		return sum;
	}

	private static int findParent(int node) {
		if(parent[node] == node) return node;
		return parent[node] = findParent(parent[node]);
	}
}