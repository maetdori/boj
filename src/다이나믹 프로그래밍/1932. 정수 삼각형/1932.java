import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[][] triangle;
	static int[][] cost;
	static int max;
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		triangle = new int[n][n];
		cost = new int[n][n];
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<i+1; j++) {
				triangle[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cost[0][0] = triangle[0][0];
		for(int i=1; i<n; i++) {
			for(int j=0; j<i+1; j++) {
				if(j==0)
					cost[i][j] = cost[i-1][j] + triangle[i][j];
				else if(j==i) 
					cost[i][j] = cost[i-1][j-1] + triangle[i][j];
				else 
					cost[i][j] = Math.max(cost[i-1][j-1], cost[i-1][j]) + triangle[i][j];
			}
		}
		
		max = 0;
		for(int i=0; i<n; i++) {
			max = Math.max(max, cost[n-1][i]);
		}
		
		System.out.println(max);
	}
}