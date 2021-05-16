import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int N;
	static int[] inhabitants;
	static List<Integer>[] tree;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		inhabitants = new int[N+1];
		tree = new ArrayList[N+1];
		dp = new int[N+1][2];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<N+1; i++) {
			inhabitants[i] = Integer.parseInt(st.nextToken());
			tree[i] = new ArrayList<>();
		}

		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			tree[n1].add(n2);
			tree[n2].add(n1);
		}

		dfs(1,0);
		System.out.println(Math.max(dp[1][0], dp[1][1]));
	}

	private static void dfs(int cur, int parent) {
		for(int child: tree[cur]) {
			if(child == parent) continue;
			dfs(child, cur);
			dp[cur][0] += Math.max(dp[child][0], dp[child][1]);
			dp[cur][1] += dp[child][0];
		}
		dp[cur][1] += inhabitants[cur];
	}
}