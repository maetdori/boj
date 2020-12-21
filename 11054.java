import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] arr;
	static int[] dpInc;
	static int[] dpDec;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		dpInc = new int[N];
		dpDec = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<N; i++) {
			dpInc[i] = 1;
			for(int j=0; j<i; j++) {
				if(arr[j]<arr[i] && dpInc[j]>=dpInc[i])
					dpInc[i] = dpInc[j] +1;
			}
		}
		
		for(int i=N-1; i>=0; i--) {
			dpDec[i] = 1;
			for(int j=N-1; j>i; j--) {
				if(arr[j]<arr[i] && dpDec[j]>=dpDec[i])
					dpDec[i] = dpDec[j] +1;
			}
		}
		
		int max = 0;
		for(int i=0; i<N; i++) {
			max = Math.max(max, dpInc[i] + dpDec[i]);
		}
		
		System.out.println(max-1);
	}
}