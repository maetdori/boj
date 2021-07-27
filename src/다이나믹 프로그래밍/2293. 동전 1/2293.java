import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int N;
	static int K;
	static int[] coin;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		coin = new int[N];
		dp = new int[K+1];

		for(int i=0; i<N; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}

		makeValue();
		System.out.println(dp[K]);
	}

	private static void makeValue() {
		dp[0] = 1;
		for(int i=0; i<N; i++) {
			for(int j=coin[i]; j<=K; j++) {
				dp[j] += dp[j - coin[i]];
			}
		}
	}
}