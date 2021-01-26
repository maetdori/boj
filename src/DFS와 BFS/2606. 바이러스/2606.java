import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int[][] matrix;
	static boolean[] visited;
	static int count = -1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int vertex = Integer.parseInt(br.readLine());
		int edge = Integer.parseInt(br.readLine());
		
		matrix = new int[vertex+1][vertex+1];
		visited = new boolean[vertex+1];
		
		for(int i=0; i<edge; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			matrix[v1][v2] = 1;
			matrix[v2][v1] = 1;
		}
		
		bfs(1, vertex);
		
		System.out.println(count);
	}
	
	private static void bfs(int node, int vertex) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(node);
		visited[node] = true;
		
		while(!queue.isEmpty()) {
			int temp = queue.poll();
			count++;
			
			for(int i=0; i<=vertex; i++) {
				if(!visited[i] && matrix[temp][i]==1) {
					visited[i] = true;
					queue.offer(i);
				}
			}
		}
	}
}