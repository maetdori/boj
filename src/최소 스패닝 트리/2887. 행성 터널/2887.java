import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Planet {
	int id;
	int x;
	int y;
	int z;

	Planet(int id, int x, int y, int z) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.z = z;
	}
}

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
	static int[] parent;
	static Planet[] planets;
	static PriorityQueue<Edge> edges = new PriorityQueue<Edge>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		parent = new int[N];
		planets = new Planet[N];

		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			planets[i] = new Planet(i,x,y,z);
			parent[i] = i;
		}

		getCandidates();

		System.out.println(getMinimalCost());
	}

	private static void getCandidates() {
		//x좌표 기준 정렬
		Arrays.sort(planets, (p1, p2) -> p1.x - p2.x);
		for(int i=0; i<N-1; i++) {
			Planet p1 = planets[i];
			Planet p2 = planets[i+1];
			edges.offer(new Edge(p1.id, p2.id, p2.x-p1.x));
		}

		//y좌표 기준 정렬
		Arrays.sort(planets, (p1, p2) -> p1.y - p2.y);
		for(int i=0; i<N-1; i++) {
			Planet p1 = planets[i];
			Planet p2 = planets[i+1];
			edges.offer(new Edge(p1.id, p2.id, p2.y-p1.y));
		}

		//z좌표 기준 정렬
		Arrays.sort(planets, (p1, p2) -> p1.z - p2.z);
		for(int i=0; i<N-1; i++) {
			Planet p1 = planets[i];
			Planet p2 = planets[i+1];
			edges.offer(new Edge(p1.id, p2.id, p2.z-p1.z));
		}
	}

	private static long getMinimalCost() {
		long sum = 0;

		while(!edges.isEmpty()) {
			Edge edge = edges.poll();

			if(!union(edge.node1, edge.node2)) continue;

			sum += edge.cost;
		}
		return sum;
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
