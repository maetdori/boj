import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		Stack<Integer> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		int next = 1;
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			if(next <= num) {
				for(int j=next; j<=num; j++) {
					stack.push(j);
					sb.append("+\n");
				}
				next = num+1;
			}

			if(stack.isEmpty() || stack.peek() != num) {
				System.out.println("NO");
				return;
			}

			stack.pop();
			sb.append("-\n");
		}

		System.out.println(sb.toString());
	}
}