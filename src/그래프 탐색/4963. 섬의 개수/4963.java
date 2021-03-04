import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int[][] map;
	static boolean[][] visited;
	static int row;
	static int col;
	static int[] rMove = new int[] {-1,0,1,-1,1,-1,0,1};
	static int[] cMove = new int[] {-1,-1,-1,0,0,1,1,1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;

		while((line=br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(line);
			col = Integer.parseInt(st.nextToken());
			row = Integer.parseInt(st.nextToken());
			map = new int[row][col];
			visited = new boolean[row][col];

			if(row==0 || col==0) continue;

			for(int r=0; r<row; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<col; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			System.out.println(island());
		}
	}

	private static int island() {
		Queue<int[]> queue = new LinkedList<>();
		int count = 0;

		for(int r=0; r<row; r++) {
			for(int c=0; c<col; c++) {
				if(!visited[r][c] && map[r][c]==1) {
					visited[r][c] = true;
					queue.offer(new int[]{r,c});
					count++;

					while(!queue.isEmpty()) {
						int[] tmp = queue.poll();
						int ro = tmp[0];
						int co = tmp[1];

						for(int i=0; i<8; i++) {
							int nr = ro + rMove[i];
							int nc = co + cMove[i];

							if(nr<0 || nc<0 || nr>=row || nc>=col)
								continue;

							if(!visited[nr][nc] && map[nr][nc]==1) {
								visited[nr][nc] = true;
								queue.offer(new int[]{nr,nc});
							}
						}
					}
				}
			}
		}
		return count;
	}
}