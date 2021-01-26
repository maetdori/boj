import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {
	static int N;
	static int[][] cnt;
	static int result;
	static int mod = 1000000000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cnt = new int[N+1][10]; //cnt[n][k]: 길이 n짜리 계단수에서 마지막 숫자가 k가 되는 경우의 수  
		for(int i=1; i<=9; i++) {
			cnt[1][i] = 1;
		}
		
		for(int i=2; i<=N; i++) {
			for(int j=0; j<=9; j++) {
				if(j==0)
					cnt[i][0] = cnt[i-1][1] % mod;
				else if(j==9)
					cnt[i][9] = cnt[i-1][8] % mod;
				else 
					cnt[i][j] = (cnt[i-1][j-1] + cnt[i-1][j+1]) % mod;
			}
		}
		
		result = 0;
		for(int i=0; i<=9; i++) {
			result = (result + cnt[N][i]) % mod;
		}
		
		System.out.println(result);
	}
}