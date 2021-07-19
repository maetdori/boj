import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Cable {
	int from;
	int to;

	Cable(int from, int to) {
		this.from = from;
		this.to = to;
	}
}

class Main {
	static int N;
	static Cable[] cables;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cables = new Cable[N];

		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			cables[i] = new Cable(from, to);
		}

		System.out.println(removeCable());
	}

	//LIS(가장 긴 증가하는 부분수열)
	private static int removeCable() {
		int[] dp = new int[N];
		int minRemoval = 100;

		Arrays.sort(cables, (c1, c2) -> c1.from - c2.from);

		for(int i=0; i<N; i++) {
			dp[i] = 1; //i번째에 설치할 수 있는 최대 전깃줄 수

			for(int j=0; j<i; j++) {
				if(cables[j].to < cables[i].to)
					dp[i] = Math.max(dp[i], dp[j]+1);
			}

			minRemoval = Math.min(minRemoval, N-dp[i]);
		}
		return minRemoval;
	}
}