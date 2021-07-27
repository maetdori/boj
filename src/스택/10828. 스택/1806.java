import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static List<Integer> stack = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		for(int n=0; n<N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();

			switch(cmd) {
				case "push":
					int num = Integer.parseInt(st.nextToken());
					push(num);
					break;
				case "pop":
					pop();
					break;
				case "size":
					size();
					break;
				case "empty":
					empty();
					break;
				case "top":
					top();
					break;
			}
		}
	}

	private static void push(int num) {
		stack.add(num);
	}

	private static void pop() {
		System.out.println(stack.isEmpty() ? -1 : stack.remove(stack.size()-1));
	}

	private static void size() {
		System.out.println(stack.size());
	}

	private static void empty() {
		System.out.println(stack.isEmpty() ? 1 : 0);
	}

	private static void top() {
		System.out.println(stack.isEmpty() ? -1 : stack.get(stack.size()-1));
	}
}