import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {
	static int N;
	static int[] cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cnt = new int[N+1];
		cnt[0] = 0;
		cnt[1] = 0;
		
		for(int i=2; i<N+1; i++) {
			if(i%2==0 && i%3==0) 
				cnt[i] = Math.min(cnt[i-1]+1, Math.min(cnt[i/2]+1, cnt[i/3]+1));
			else if(i%3==0)
				cnt[i] = Math.min(cnt[i-1]+1, cnt[i/3]+1);
			else if(i%2==0)
				cnt[i] = Math.min(cnt[i-1]+1, cnt[i/2]+1);
			else cnt[i] = cnt[i-1]+1;
		}
		System.out.println(cnt[N]);
	}
}