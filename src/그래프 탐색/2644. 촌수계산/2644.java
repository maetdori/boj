import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static List<Integer>[] adj;
	static boolean[] visited;
	static int vertex;
	static int edge;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		vertex = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int p1 = Integer.parseInt(st.nextToken());
		int p2 = Integer.parseInt(st.nextToken());
		edge = Integer.parseInt(br.readLine());

		adj = new ArrayList[vertex+1];
		visited = new boolean[vertex+1];
		for(int i=1; i<vertex+1; i++)
			adj[i] = new ArrayList<>();

		for(int i=0; i<edge; i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());

			adj[parent].add(child);
			adj[child].add(parent);
		}
		System.out.println(bfs(p1,p2));
	}

	private static int bfs(int p1, int p2) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[]{p1, 0});
		visited[p1] = true;

		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			int node = tmp[0];
			int cnt = tmp[1];

			if(adj[node].contains(p2))
				return cnt + 1;

			for(int neighbor: adj[node]) {
				if(!visited[neighbor]) {
					queue.offer(new int[]{neighbor, cnt + 1});
					visited[neighbor] = true;
				}
			}
		}
		return -1;
	}
}