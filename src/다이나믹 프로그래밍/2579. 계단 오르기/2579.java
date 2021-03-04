import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {
	static int N;
	static int[] stair;
	static long[][] cost;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		stair = new int[N];
		for(int i=0; i<N; i++) {
			stair[i] = Integer.parseInt(br.readLine());
		}
		
		cost = new long[N][2];
		cost[0][0] = stair[0];
		cost[0][1] = stair[0];
		if(N==1) {
			System.out.println(stair[0]);
			return;
		}
		cost[1][0] = stair[0] + stair[1];
		cost[1][1] = stair[1];
		for(int i=2; i<N; i++) {
			cost[i][0] = cost[i-1][1] + stair[i];
			cost[i][1] = Math.max(cost[i-2][0], cost[i-2][1]) + stair[i];
		}
		
		System.out.println(Math.max(cost[N-1][0], cost[N-1][1]));
	}
}