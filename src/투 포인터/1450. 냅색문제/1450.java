import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int N;
	static int C;
	static int[] element;
	static List<Integer> aSum, bSum;
	static int index;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		element = new int[N];
		aSum = new ArrayList<>();
		bSum = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			element[i] = Integer.parseInt(st.nextToken());
		}

		aBruteForce(0,0);
		bBruteForce(N/2, 0);

		Collections.sort(bSum);

		int cnt = 0;
		for(int i=0; i<aSum.size(); i++) {
			index = -1;
			binarySearch(0, bSum.size()-1, aSum.get(i));
			cnt += index+1;
		}

		System.out.println(cnt);
	}

	private static void binarySearch(int start, int end, int val) {
		if(start > end) return;

		int mid = (start + end)/2;

		if(bSum.get(mid) + val <= C) {
			index = mid;
			binarySearch(mid+1, end, val);
		}
		else {
			binarySearch(start, mid-1, val);
		}
	}

	private static void aBruteForce(int idx, int sum) {
		if(sum > C) return;

		if(idx == N/2) {
			aSum.add(sum);
			return;
		}

		aBruteForce(idx+1, sum); //현재 element 선택X
		aBruteForce(idx+1, sum + element[idx]); //현재 element 선택
	}

	private static void bBruteForce(int idx, int sum) {
		if(sum > C) return;

		if(idx == N) {
			bSum.add(sum);
			return;
		}

		bBruteForce(idx+1, sum); //현재 element 선택X
		bBruteForce(idx+1, sum + element[idx]); //현재 element 선택
	}
}