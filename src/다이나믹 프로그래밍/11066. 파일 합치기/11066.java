import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int K;
	static int[] files;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int t=0; t<T; t++) {
			K = Integer.parseInt(br.readLine());
			files = new int[K];
			StringTokenizer st = new StringTokenizer(br.readLine());

			for(int i=0; i<K; i++) {
				files[i] = Integer.parseInt(st.nextToken());
			}
			System.out.println(getMinimalCost());
		}
	}

	private static int getMinimalCost() {
		int[][] dp = new int[K][K]; //dp[i][j]: i장~j장 합치는데 드는 최소비용
		int[] sum = new int[K]; //sum[i]: i번째 파일까지의 누적합
		sum[0] = files[0];

		for(int i=1; i<K; i++) {
			dp[i-1][i] = files[i-1] + files[i];
			sum[i] = sum[i-1] + files[i];
		}

		for(int d=2; d<K; d++) {
			for(int i=0; i+d<K; i++) {
				int j = i+d;
				dp[i][j] = Integer.MAX_VALUE;

				for(int k=i; k<j; k++) {
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j]);
				}

				dp[i][j] += (i==0 ? sum[j] : sum[j]-sum[i-1]);
			}
		}
		return dp[0][K-1];
	}
}