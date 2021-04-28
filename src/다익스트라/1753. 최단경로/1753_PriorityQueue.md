import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
 
class Edge implements Comparable<Edge> {
	int vertex;
	int weight;
 
	Edge(int vertex, int weight) {
		this.vertex = vertex;
		this.weight = weight;
	}
 
	@Override
	public int compareTo(Edge e) {
		return this.weight - e.weight;
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
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		queue.offer(new Edge(start, distance[start]));
 
		while(!queue.isEmpty()) {
			Edge closest = queue.poll();
 
			if(visited[closest.vertex]) continue;
			visited[closest.vertex] = true;
 
			for(Edge dest: connected[closest.vertex]) {
				if(distance[dest.vertex] > distance[closest.vertex] + dest.weight) {
					distance[dest.vertex] = distance[closest.vertex] + dest.weight;
					queue.offer(new Edge(dest.vertex, distance[dest.vertex]));
				}
			}
		}
	}
}