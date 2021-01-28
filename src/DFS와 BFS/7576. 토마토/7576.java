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

		col = Integer.parseInt(st.nextToken());
		row = Integer.parseInt(st.nextToken());

		matrix = new int[row][col];
		visited = new boolean[row][col];

		for(int r=0; r<row; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<col; c++) {
				matrix[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		bfs();
	}

	private static void bfs() {
		Queue<int[]> queue = new LinkedList<>();
		int count = 0;

		for(int r=0; r<row; r++) {
			for(int c=0; c<col; c++) {
				if(matrix[r][c] == 1)
					queue.offer(new int[]{r, c, 0});
			}
		}

		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			int r = tmp[0];
			int c = tmp[1];
			count = tmp[2];

			if(r>0 && !visited[r-1][c] && matrix[r-1][c]==0) {
				queue.offer(new int[]{r-1, c, count+1});
				matrix[r-1][c] = 1;
			}
			if(c>0 && !visited[r][c-1] && matrix[r][c-1]==0) {
				queue.offer(new int[]{r, c-1, count+1});
				matrix[r][c-1] = 1;
			}
			if(r+1<row && !visited[r+1][c] && matrix[r+1][c]==0) {
				queue.offer(new int[]{r+1, c, count+1});
				matrix[r+1][c] = 1;
			}
			if(c+1<col && !visited[r][c+1] && matrix[r][c+1]==0) {
				queue.offer(new int[]{r, c+1, count+1});
				matrix[r][c+1] = 1;
			}
		}

		if(isThereZero())
			System.out.println(-1);
		else System.out.println(count);
	}

	private static boolean isThereZero() {
		for(int r=0; r<row; r++) {
			for(int c=0; c<col; c++) {
				if(matrix[r][c]==0)
					return true;
			}
		}
		return false;
	}
}