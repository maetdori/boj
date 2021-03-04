import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static int testcase;
	static int n;
	static int[] arr = new int[42];
	
	public static void main(String[] args) throws IOException {
			
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		testcase = Integer.parseInt(br.readLine());
		
		for(int i=0; i<testcase; i++) {
			n = Integer.parseInt(br.readLine()); 
			Fibonacci();
			sb.append(arr[n] + " " + arr[n+1] + "\n");
		}
		
		System.out.println(sb);
	}
	
	public static void Fibonacci() {
		arr[0] = 1;
		arr[1] = 0;
		for(int i=2; i<42; i++) {
			arr[i] = arr[i-1] + arr[i-2];
		}
	}
}