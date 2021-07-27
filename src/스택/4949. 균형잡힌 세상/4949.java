import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static StringBuilder sb = new StringBuilder();
	static Stack<Character> stack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;

		while(!(str = br.readLine()).equals(".")) {
			sb.append(check(str.toCharArray()) ? "yes\n" : "no\n");
		}
		System.out.println(sb.toString());
	}

	private static boolean isBracket(char ch) {
		if(ch!='(' && ch!=')' && ch!='[' && ch!=']') return false;
		return true;
	}
	private static boolean check(char[] seq) {
		stack = new Stack<>();

		for(int i=0; i<seq.length; i++) {
			if(!isBracket(seq[i])) continue;

			if(seq[i] == '(' || seq[i] == '[') {
				stack.push(seq[i]);
				continue;
			}

			if(stack.isEmpty()) return false;
			if(stack.peek()=='(' && seq[i]==']') return false;
			if(stack.peek()=='[' && seq[i]==')') return false;
			stack.pop();
		}

		return stack.isEmpty() ? true : false;
	}
}