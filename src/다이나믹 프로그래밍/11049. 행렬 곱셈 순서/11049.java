import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Sequence {
	int r;
	int c;

	Sequence(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

class Main {
	static int N;
	static Sequence[] seq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		seq = new Sequence[N];

		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			seq[i] = new Sequence(r,c);
		}
		System.out.println(getMinimum());
	}

	private static int getMinimum() {
		int[][] dp = new int[N][N]; //dp[i][j]: 행렬 i~j까지 곱셈연산횟수의 최소값

		for(int i=0; i<N-1; i++) {
			dp[i][i+1] = seq[i].r * seq[i].c * seq[i+1].c;
		}

		for(int d=2; d<N; d++) {
			for(int i=0; i+d<N; i++) {
				int j = i+d;
				dp[i][j] = Integer.MAX_VALUE;

				for(int k=i; k<j; k++) {
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + seq[i].r * seq[k].c * seq[j].c);
				}
			}
		}
		return dp[0][N-1];
	}
}