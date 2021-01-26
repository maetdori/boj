import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
	
	static int N,M;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[M];
		
		dfs(0,1);
		System.out.println(sb);
	}
	
	public static void dfs(int depth, int start) {
		
		if(depth == M) {
			for(int value: arr) {
				sb.append(value + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=start; i<=N; i++) {
			arr[depth] = i;
			dfs(depth+1, i+1);
		}
	}
}