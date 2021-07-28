import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int liq1 = 0;
	static int liq2 = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int left = 0;
		int right = N-1;
		int val = Integer.MAX_VALUE;

		while(left < right) {
			int sum = arr[left] + arr[right];

			if(Math.abs(val) > Math.abs(sum)) {
				val = sum;
				liq1 = arr[left];
				liq2 = arr[right];
			}

			if(sum < 0)
				left++;
			else right--;
		}

		System.out.println(liq1 + " " + liq2);
	}
}