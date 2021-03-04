import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int[] rMove = {-1,1,0,0};
	static int[] cMove = {0,0,-1,1};
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		char[][] strong = new char[N][N];
		char[][] weak = new char[N][N];

		for(int r=0; r<N; r++) {
			strong[r] = br.readLine().toCharArray();
			for(int c=0; c<N; c++) {
				weak[r][c] = (strong[r][c]=='R' || strong[r][c]=='G') ? 'R' : strong[r][c];
			}
		}

		System.out.println(count(strong) + " " + count(weak));
	}

	private static int count(char[][] matrix) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		int part = 0;

		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(!visited[r][c]) {
					queue.offer(new int[]{r, c});
					visited[r][c] = true;
					part++;
				}

				while (!queue.isEmpty()) {
					int[] tmp = queue.poll();
					int ro = tmp[0];
					int co = tmp[1];

					for (int i = 0; i < 4; i++) {
						int nr = ro + rMove[i];
						int nc = co + cMove[i];

						if(nr<0 || nc<0 || nr>=N || nc>=N)
							continue;

						if(!visited[nr][nc] && matrix[ro][co]==matrix[nr][nc]) {
							queue.offer(new int[]{nr,nc});
							visited[nr][nc] = true;
						}
					}
				}
			}
		}
		return part;
	}
}