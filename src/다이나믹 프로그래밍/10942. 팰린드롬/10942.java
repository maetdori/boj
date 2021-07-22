import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int N;
	static int[] seq;
	static boolean[][] dp;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		seq = new int[N];
		dp = new boolean[N][N];
		visited = new boolean[N][N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}

		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken())-1;
			int E = Integer.parseInt(st.nextToken())-1;

			sb.append(isPalindrome(S,E) ? 1 : 0).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static boolean isPalindrome(int S, int E) {

		visited[S][E] = true;

		if(seq[S] != seq[E])
			return dp[S][E] = false;

		if(S==E || S+1==E)
			return dp[S][E] = true;

		return dp[S][E] = visited[S+1][E-1] ? dp[S+1][E-1] : isPalindrome(S+1, E-1);
	}
}