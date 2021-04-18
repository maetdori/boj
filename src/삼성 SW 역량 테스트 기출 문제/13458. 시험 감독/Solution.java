import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int N;
	static int[] student;
	static int B;
	static int C;
	static long need;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		student = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			student[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		need = N;
		calculate();

		System.out.println(need);
	}

	private static void calculate() {
		for(int i=0; i<N; i++) {
			howMany(student[i]);
		}
	}

	private static void howMany(int num) {
		num -= B;
		if(num <= 0) return;
		if(num % C == 0) need += num / C;
		else need += num / C + 1;
	}
}