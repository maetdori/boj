import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		System.out.println(getNumber(N));
	}

	private static int getNumber(int n) {
		int cnt = 0;
		int num = 666;

		while(cnt < n) {
			if(has666(num++)) cnt++;
		}
		return num-1;
	}

	private static boolean has666(int num) {
		String str = String.valueOf(num);

		if(str.contains("666")) return true;
		return false;
	}
}