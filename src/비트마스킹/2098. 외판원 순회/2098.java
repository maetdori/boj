import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int N;
	static int[][] cost;
	static int[][] dp;
	static final int INF = 1000000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cost = new int[N][N];
		dp = new int[N][1<<N];

		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for(int i=0; i<N; i++) {
			Arrays.fill(dp[i], INF);
		}

		System.out.println(travel(0,1));
	}

	static int travel(int cur, int visit) {
		if(dp[cur][visit] != INF)
			return dp[cur][visit];
		if(visit == (1<<N)-1)
			return dp[cur][visit] = cost[cur][0] != 0 ? cost[cur][0] : INF;

		for(int i=0; i<N; i++) {
			if(cost[cur][i] == 0 || (visit & (1<<i)) > 0)
				continue;
			dp[cur][visit] = Math.min(dp[cur][visit], cost[cur][i] + travel(i, visit | (1<<i)));
		}

		return dp[cur][visit];
	}
}