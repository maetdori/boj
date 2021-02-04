import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int[][] matrix;
	static boolean[][] visited;
	static int row;
	static int col;
	static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());

		matrix = new int[row][col];
		visited = new boolean[row][col];

		for(int r=0; r<row; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<col; c++) {
				matrix[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		wall(0);
		System.out.println(max);
	}

	private static void wall(int depth) {
		if(depth == 3) {
			max = Math.max(max, virus());
			return;
		}

		for(int r=0; r<row; r++) {
			for(int c=0; c<col; c++) {
				if(!visited[r][c] && matrix[r][c]==0) {
					visited[r][c] = true;
					matrix[r][c] = 1;
					wall(depth+1);
					visited[r][c] = false;
					matrix[r][c] = 0;
				}
			}
		}
	}

	private static int virus() {
		Queue<int[]> queue = new LinkedList<>();
		int[][] map = new int[row][col];
		int[] rMove = new int[]{-1,1,0,0};
		int[] cMove = new int[]{0,0,-1,1};

		for(int r=0; r<row; r++) {
			for(int c=0; c<col; c++) {
				map[r][c] = matrix[r][c];
				if(map[r][c]==2)
					queue.offer(new int[] {r,c});
			}
		}

		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			int r = tmp[0];
			int c = tmp[1];

			for(int i=0; i<4; i++) {
				int nr = r + rMove[i];
				int nc = c + cMove[i];

				if(nr<0 || nc<0 || nr>=row || nc>=col)
					continue;

				if(map[nr][nc] == 0) {
					map[nr][nc] = 2;
					queue.offer(new int[] {nr,nc});
				}
 			}
		}

		return count(map);
	}

	private static int count(int[][] map) {
		int cnt = 0;

		for(int r=0; r<row; r++) {
			for(int c=0; c<col; c++) {
				if(map[r][c] == 0)
					cnt++;
			}
		}

		return cnt;
	}
}