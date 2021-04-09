import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int X = Integer.parseInt(br.readLine());
		int cnt = 0;

		for(int i=1; i<=X; i<<=1) {
			if ((i & X) == i) cnt++;
		}

		System.out.println(cnt);
	}
}