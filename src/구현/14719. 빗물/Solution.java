import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int H;
	static int W;
	static int[] height;
	static int sum = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		height = new int[W];

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<W; i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}

		rain();
		System.out.println(sum);
	}

	private static void rain() {
		for(int i=1; i<W-1; i++) {
			int left = 0;
			int right = 0;

			for(int j=0; j<i; j++) {
				left = Math.max(left, height[j]);
			}

			for(int j=i+1; j<W; j++) {
				right = Math.max(right, height[j]);
			}

			if(left > height[i] && right > height[i]) {
				sum += Math.min(left, right) - height[i];
			}
		}
	}
}