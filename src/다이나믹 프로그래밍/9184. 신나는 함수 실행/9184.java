import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int[][][] w = new int[101][101][101];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			if(a==-1 && b==-1 && c==-1) break;

			System.out.printf("w(%d, %d, %d) = %d", a, b, c, operate(a,b,c));
			System.out.println();
		}
	}

	private static int operate(int a, int b, int c) {
		if(w[a+50][b+50][c+50] != 0)
			return w[a+50][b+50][c+50];

		if(a<=0 || b<=0 || c<=0)
			return w[a+50][b+50][c+50] = 1;

		if(a>20 || b>20 || c>20)
			return w[70][70][70] = operate(20,20,20);

		if(a<b && b<c)
			return w[a+50][b+50][c+50] = operate(a,b,c-1) + operate(a,b-1,c-1) -operate(a,b-1,c);

		return w[a+50][b+50][c+50] = operate(a-1,b,c) + operate(a-1,b-1,c) + operate(a-1,b,c-1) - operate(a-1,b-1,c-1);
	}
}