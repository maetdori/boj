import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int[][] matrix;
	static boolean[][] visited;
	static int row;
	static int col;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int testcase = Integer.parseInt(st.nextToken());

		for(int i=0; i<testcase; i++) {
			st = new StringTokenizer(br.readLine());
			col = Integer.parseInt(st.nextToken());
			row = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());

			matrix = new int[row][col];
			visited = new boolean[row][col];

			for(int j=0; j<num; j++) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				matrix[r][c] = 1;
			}

			int count = 0;
			for(int r=0; r<row; r++) {
				for(int c=0; c<col; c++) {
					if(!visited[r][c] && matrix[r][c]==1) {
						visited[r][c] = true;
						check(r, c);
						count++;
					}
				}
			}

			System.out.println(count);
		}
	}

	private static void check(int r, int c) {
		if(r>0 && !visited[r-1][c] && matrix[r-1][c]==1) { //상
			visited[r-1][c] = true;
			check(r-1, c);
		}
		if(c>0 && !visited[r][c-1] && matrix[r][c-1]==1) { //좌
			visited[r][c-1] = true;
			check(r, c-1);
		}
		if(r+1<row && !visited[r+1][c] && matrix[r+1][c]==1) { //하
			visited[r+1][c] = true;
			check(r+1, c);
		}
		if(c+1<col && !visited[r][c+1] && matrix[r][c+1]==1) { //좌
			visited[r][c+1] = true;
			check(r, c+1);
		}
	}
}
