import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class Main {
	static int N;
	static int[][] house;
	static int[][] cost;
	static int mincost;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		house = new int[N][3];
		cost = new int[N][3];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				house[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cost[0][0] = house[0][0];
		cost[0][1] = house[0][1];
		cost[0][2] = house[0][2];
		
 		for(int i=1; i<N; i++) {
			cost[i][0] = Math.min(cost[i-1][1], cost[i-1][2]) + house[i][0];
			cost[i][1] = Math.min(cost[i-1][0], cost[i-1][2]) + house[i][1];
			cost[i][2] = Math.min(cost[i-1][0], cost[i-1][1]) + house[i][2];
		}
 		
 		mincost = Math.min(cost[N-1][0], Math.min(cost[N-1][1], cost[N-1][2]));
 		System.out.println(mincost);
	}
}