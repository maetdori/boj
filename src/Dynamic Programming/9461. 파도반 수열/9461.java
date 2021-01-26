import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {
	
	static int testcase;
	static int N;
	static long[] arr = new long[101];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		testcase = Integer.parseInt(br.readLine());
		
		for(int i=0; i<testcase; i++) {
			
			N = Integer.parseInt(br.readLine());
			arr[0]=0; arr[1]=1; arr[2]=1; arr[3]=1; arr[4]=2;
			
			for(int j=5; j<N+1; j++) {
				arr[j] = arr[j-1] + arr[j-5];
			}
			sb.append(arr[N] + "\n");
		}
		
		System.out.println(sb);
	}
}