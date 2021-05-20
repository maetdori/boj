import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int N;
	static int[] cost;
	static List<Integer>[] dependency;
	static int[] dp;
	static int target;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());

		for(int t=0; t<tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			cost = new int[N+1];
			dependency = new ArrayList[N+1];
			dp = new int[N+1];
			Arrays.fill(dp, -1);

			st = new StringTokenizer(br.readLine());
			for(int i=1; i<N+1; i++) {
				cost[i] = Integer.parseInt(st.nextToken());
				dependency[i] = new ArrayList<>();
			}

			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());
				dependency[n2].add(n1);
			}

			target = Integer.parseInt(br.readLine());
			System.out.println(takeTimes(target));
		}
	}

	private static int takeTimes(int node) {
		if(dp[node] != -1)
			return dp[node];

		if(dependency[node].isEmpty())
			return dp[node] = cost[node];

		int wait = 0;
		for(int prev: dependency[node]) {
			int prevCost = takeTimes(prev);
			wait = Math.max(wait, prevCost);
		}
		return dp[node] = wait + cost[node];
	}
}