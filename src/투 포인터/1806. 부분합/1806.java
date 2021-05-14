import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int N;
	static int S;
	static int[] seq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		seq = new int[N];

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(getMinLen());
	}

	private static int getMinLen() {
		int minLen = Integer.MAX_VALUE;
		int left = 0;
		int right = 0;
		int sum = 0;

		while(true) {
			if(sum >= S) {
				sum -= seq[left++];
				minLen = Math.min(minLen, right-left+1);
			}
			else if(right == N) break;
			else sum += seq[right++];
		}
		return minLen == Integer.MAX_VALUE ? 0 : minLen;
	}
}