import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] seq = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(getMaxSum(N, seq));
	}

	private static int getMaxSum(int N, int[] seq) {
		int[] dp = new int[N];
		int maxSum = seq[0];

		dp[0] = seq[0];
		for(int i=1; i<N; i++) {
			dp[i] = Math.max(seq[i], dp[i-1]+seq[i]);
			maxSum = Math.max(maxSum, dp[i]);
		}

		return maxSum;
	}
}