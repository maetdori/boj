import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();
		int sum = 0;

		for(int k=0; k<K; k++) {
			int num = Integer.parseInt(br.readLine());
			if(num == 0)
				sum -= stack.pop();
			else sum += stack.push(num);
		}
		System.out.println(sum);
	}
}