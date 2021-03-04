import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int vertex;
	static List<Integer>[] adj;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		vertex = Integer.parseInt(br.readLine());
		adj = new ArrayList[vertex+1];
		parent = new int[vertex+1];

		for(int i=1; i<=vertex; i++)
			adj[i] = new ArrayList<>();

		for(int i=0; i<vertex-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());

			adj[node1].add(node2);
			adj[node2].add(node1);
		}

		bfs();

		for(int i=2; i<=vertex; i++)
			System.out.println(parent[i]);
	}

	private static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[vertex+1];

		queue.offer(1);
		visited[1] = true;

		while(!queue.isEmpty()) {
			int node = queue.poll();

			for(int neighbor: adj[node]) {
				if(!visited[neighbor]) {
					parent[neighbor] = node;
					visited[neighbor] = true;
					queue.offer(neighbor);
				}
			}
		}
	}
}