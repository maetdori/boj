import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		System.out.println(getChar(N));
	}

	private static char getChar(int N) {
		int add = 3;
		int len = 3;
		int prevLen = 0;

		while (N > len) {
			prevLen = len;
			len *= 2;
			len += (++add);
		}

		if(N == prevLen+1) return 'm';
		if(N <= prevLen+add) return 'o';
		return getChar(N-(prevLen+add));
	}
}