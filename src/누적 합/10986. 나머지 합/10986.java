import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int sum = 0;
		int[] remainder = new int[M];

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			sum += Integer.parseInt(st.nextToken()) % M;
			remainder[sum%M]++;
		}

		long ans = remainder[0];
		for(int i=0; i<M; i++) {
			int n = remainder[i];
			ans += (long)n*(n-1)/2;
		}
		System.out.println(ans);
	}
}