import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int N;
	static int[] weight;
	static int maxBall = 40000;
	static boolean[][] dp; //dp[i][j]: i번째 추까지 포함하여 무게 j 구슬 측정할 수 있는지 여부

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		weight = new int[N];
		dp = new boolean[N+1][maxBall+1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
		}

		measure(0,0);

		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M; i++) {
			int ball = Integer.parseInt(st.nextToken());
			sb.append(getAnswer(ball)).append(" ");
		}

		System.out.println(sb.toString());
	}

	private static char getAnswer(int ball) {
		for(int i=0; i<=N; i++) {
			if(dp[i][ball]) return 'Y';
		}
		return 'N';
	}

	private static void measure(int cnt, int w) {

		if(w > maxBall || dp[cnt][w]) return;

		dp[cnt][w] = true;

		if(cnt == N) return;

		measure(cnt+1, w);
		measure(cnt+1, w + weight[cnt]);
		measure(cnt+1, Math.abs(w-weight[cnt]));
	}
}