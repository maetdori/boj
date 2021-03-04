import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int[][] matrix;
	static int[][] visited;
	static int row;
	static int col;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());

		matrix = new int[row][col];
		visited = new int[row][col];

		for(int r = 0; r < row; r++) {
			String[] str = br.readLine().split("");
			for(int c = 0; c < col; c++) {
				matrix[r][c] = Integer.parseInt(str[c]);
			}
		}

		maze();

		System.out.println(visited[row-1][col-1]);
	}

	private static void maze() {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[]{0,0});
		visited[0][0] = 1;

		while(!queue.isEmpty()) {
			int[] pos = queue.poll();
			int r = pos[0];
			int c = pos[1];

			if(r>0 && visited[r-1][c]==0 && matrix[r-1][c]==1) {
				queue.offer(new int[]{r-1,c});
				visited[r-1][c] = visited[r][c] + 1;
			}
			if(c>0 && visited[r][c-1]==0 && matrix[r][c-1]==1) {
				queue.offer(new int[]{r,c-1});
				visited[r][c-1] = visited[r][c] + 1;
			}
			if(r+1<row && visited[r+1][c]==0 && matrix[r+1][c]==1) {
				queue.offer(new int[]{r+1,c});
				visited[r+1][c] = visited[r][c] + 1;
			}
			if(c+1<col && visited[r][c+1]==0 && matrix[r][c+1]==1) {
				queue.offer(new int[]{r,c+1});
				visited[r][c+1] = visited[r][c] + 1;
			}
		}
	}
}