import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
	static int[][] matrix;
	static boolean[] visited;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int vertex = Integer.parseInt(st.nextToken());
		int edge = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());

		matrix = new int[vertex+1][vertex+1];

		for(int i=0; i<edge; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			matrix[v1][v2] = 1;
			matrix[v2][v1] = 1;
		}

		visited = new boolean[vertex+1];
		sb = new StringBuilder();

		dfs(start, vertex);
		System.out.println(sb.toString());

		visited = new boolean[vertex+1];
		sb = new StringBuilder();

		bfs(start, vertex);
		System.out.println(sb.toString());
	}

	private static void dfs(int node, int vertex) {
		visited[node] = true;
		sb.append(node+ " ");

		for(int i=1; i<=vertex; i++) {
			if(!visited[i] && matrix[node][i] == 1) {
				dfs(i, vertex);
			}
		}
	}

	private static void bfs(int node, int vertex) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(node);
		visited[node] = true;

		while(!queue.isEmpty()) {
			int tmp = queue.poll();
			sb.append(tmp + " ");

			for(int i=1; i<=vertex; i++) {
				if(!visited[i] && matrix[tmp][i] == 1) {
					visited[i] = true;
					queue.offer(i);
				}
			}
		}
	}
}
​