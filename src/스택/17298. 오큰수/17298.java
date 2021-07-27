import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nge = new int[N];
		Stack<int[]> stack = new Stack<>(); //int[]{숫자, 인덱스}

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());

			if(stack.isEmpty()) {
				stack.push(new int[]{num,0});
				continue;
			}

			while(!stack.isEmpty() && stack.peek()[0] < num) {
				nge[stack.pop()[1]] = num;
			}

			stack.push(new int[]{num,i});
		}

		StringBuilder sb = new StringBuilder();
		for(int n: nge) {
			sb.append(n==0 ? -1 : n).append(" ");
		}
		System.out.println(sb.toString());
	}
}