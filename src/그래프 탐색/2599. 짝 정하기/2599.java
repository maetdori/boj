import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] data = new int[3][2];
		int result[][] = new int[3][2];
		boolean flag = false;

		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			data[i][0] = Integer.parseInt(st.nextToken());
			data[i][1] = Integer.parseInt(st.nextToken());
		}

		for(int i=0; i<=data[0][0]; i++) {
			result[0][0] = i;
			result[0][1] = data[0][0] - i;
			result[1][1] = data[2][1] - result[0][1];
			result[1][0] = data[1][0] - result[1][1];
			result[2][0] = data[0][1] - result[1][0];
			result[2][1] = data[2][0] - result[2][0];

			if(result[0][0]<0 || result[0][1]<0 || result[1][0]<0 || result[1][1]<0 || result[2][0]<0 || result[2][1]<0)
				continue;

			System.out.println(1);
			for(int j=0; j<3; j++) {
				System.out.println(result[j][0] + " " + result[j][1]);
			}
			flag = true;
			break;
		}

		if(!flag) System.out.println(0);
	}
}