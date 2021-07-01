import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int N;
	static int C;
	static int[] weight;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		weight = new int[N];

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
			if(weight[i] == C) {
				System.out.println(1);
				return;
			}
		}

		Arrays.sort(weight);

		if(takeTwo(0,C) || takeThree(C)) System.out.println(1);
		else System.out.println(0);
	}

	private static boolean takeThree(int sum) {
		for(int i=0; i<N-2; i++) {
			if(weight[i]+weight[i+1]+weight[i+2] > sum) break;
			if(takeTwo(i+1, sum-weight[i])) return true;
		}
		return false;
	}

	private static boolean takeTwo(int start, int sum) {
		for(int i=start; i<N-1; i++) {
			if(weight[i]+weight[i+1] > sum) break;
			if(isThere(i+1, N-1, sum-weight[i])) return true;
		}
		return false;
	}

	private static boolean isThere(int leftIdx, int rightIdx, int target) {
		if(leftIdx > rightIdx) return false;

		int midIdx = (leftIdx + rightIdx) / 2;

		if(target == weight[midIdx]) return true;
		if(target > weight[midIdx]) return isThere(midIdx+1, rightIdx, target);
		if(target < weight[midIdx]) return isThere(leftIdx, midIdx-1, target);

		return false;
	}
}