import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static boolean[] visited;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N+1];
		arr = new int[M];
		
		dfs(0);
		System.out.println(sb);
	}
	
	public static void dfs(int depth) {
		if(depth == M) {
			for(int i=0; i<M; i++) {
				sb.append(arr[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=1; i<N+1; i++) {
			if(!visited[i]) {
				visited[i] = true;
				arr[depth] = i;
				dfs(depth+1);
				visited[i] = false;
			}
		}
	}
}