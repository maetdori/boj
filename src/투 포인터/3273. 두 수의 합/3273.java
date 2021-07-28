import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int target = Integer.parseInt(br.readLine());

		int left = 0;
		int right = N-1;
		int count = 0;
		while(left < right) {
			int sum = arr[left] + arr[right];

			if(sum == target) {
				count++;
				left++;
				right--;
			}
			else if(sum < target) left++;
			else right--;
		}

		System.out.println(count);
	}
}