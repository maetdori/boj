import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static List<Integer>[] adj;
	static int[] kebinBacon;
	static int vertex;
	static int edge;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		vertex = Integer.parseInt(st.nextToken());
		edge = Integer.parseInt(st.nextToken());

		adj = new ArrayList[vertex+1];
		kebinBacon = new int[vertex+1];

		for(int i=0; i<vertex+1; i++)
			adj[i] = new ArrayList<>();

		for(int i=0; i<edge; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());

			adj[node1].add(node2);
			adj[node2].add(node1);
		}

		int min = 5000;
		int minIndex = 0;
		for(int i=1; i<=vertex; i++) {
			for(int j=1; j<=vertex; j++) {
				if(j==i) continue;
				kebinBacon[i] += count(i,j);
			}
			if(min > kebinBacon[i]) {
				min = kebinBacon[i];
				minIndex = i;
			}
		}

		System.out.println(minIndex);
	}

	private static int count(int node1, int node2) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[] visited = new boolean[vertex+1];
		queue.offer(new int[]{node1, 0});
		visited[node1] = true;

		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			int node = tmp[0];
			int cnt = tmp[1];

			if(adj[node].contains(node2))
				return cnt+1;

			for(int neighbor: adj[node]) {
				if(!visited[neighbor]) {
					visited[neighbor] = true;
					queue.offer(new int[]{neighbor, cnt+1});
				}
			}
		}
		return -1;
	}
}