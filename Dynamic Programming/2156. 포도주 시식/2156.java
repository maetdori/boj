import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {
	static int n;
	static int[] wine; //wine[k]: k번째 와인 양
	static long[] sum; //sum[k]: k번째 위치에서의 최대 누적 와인 양
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		wine = new int[n];
		sum = new long[n];
		for(int i=0; i<n; i++) {
			wine[i] = Integer.parseInt(br.readLine());
		}
		if(n==1) 
			System.out.println(wine[0]);
		else if(n==2)
			System.out.println(wine[0]+wine[1]);
		else {
			sum[0] = wine[0];
			sum[1] = wine[0] + wine[1];
			sum[2] = Math.max(wine[0]+wine[2], Math.max(wine[1]+wine[2], sum[1]));
			for(int i=3; i<n; i++) {
				sum[i] = Math.max(sum[i-2]+wine[i], Math.max(sum[i-3]+wine[i-1]+wine[i], sum[i-1]));
			}
			
			System.out.println(sum[n-1]);
		}
	}
}