import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {
		
	static int N;
	static int[] arr;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		fib();
		
		System.out.println(arr[N]);
	}
	
	public static void fib() {
		arr[0] = 1;
		arr[1] = 1;
		for(int i=2; i<N+1; i++) {
			arr[i] = arr[i-1] + arr[i-2];
			arr[i] %= 15746;
		}
	}
}