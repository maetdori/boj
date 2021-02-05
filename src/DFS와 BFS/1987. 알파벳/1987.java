import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static char[][] matrix;
	static boolean[] visited;
	static int[] rMove = new int[]{-1,1,0,0};
	static int[] cMove = new int[]{0,0,-1,1};
	static int max = 0;
	static int row;
	static int col;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());

		matrix = new char[row][col];
		visited = new boolean[26];

		for(int r=0; r<row; r++)
			matrix[r] = br.readLine().toCharArray();

		visited[matrix[0][0]-'A'] = true;
		count(0,0,1);

		System.out.println(max);
	}

	private static void count(int r, int c, int cnt) {
		int flag = 0;

		for(int i=0; i<4; i++) {
			int nr = r + rMove[i];
			int nc = c + cMove[i];

			if(nr<0 || nc<0 || nr>=row || nc>=col)
				continue;

			if(!visited[matrix[nr][nc]-'A']) {
				flag = 1;
				visited[matrix[nr][nc]-'A'] = true;
				count(nr, nc, cnt+1);
				visited[matrix[nr][nc]-'A'] = false;
			}
		}

		if(flag==0)
			max = Math.max(max,cnt);
	}
}