import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		for(int i=0; i<n; i++) {
			check(br.readLine().toCharArray());
		}
		System.out.println(sb.toString());
	}

	private static void check(char[] seq) {
		Stack<Character> stack = new Stack<>();

		for(int i=0; i<seq.length; i++) {
			if(seq[i]=='(') stack.push(seq[i]);
			else {
				if(stack.isEmpty()) {
					sb.append("NO\n");
					return;
				}
				stack.pop();
			}
		}

		if(stack.size()==0) sb.append("YES\n");
		else sb.append("NO\n");
	}
}