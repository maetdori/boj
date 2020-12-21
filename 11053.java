import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
	static int[] seq;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		seq = new int[N];
		dp = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}
		
		int max=-1;
		for(int i=0; i<N; i++) {
			dp[i] = 1;
			
			for(int j=0; j<i; j++) {
				if(seq[j]<seq[i] && dp[j]>=dp[i]) {
					dp[i] = dp[j] + 1;
				}
			}
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
	}
}
