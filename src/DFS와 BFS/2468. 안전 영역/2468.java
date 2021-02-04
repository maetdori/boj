import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int[][] map;
	static int size;
	static int tallest = 0;
	static int maxSafe = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		size = Integer.parseInt(br.readLine());
		map = new int[size][size];

		for(int r=0; r<size; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int c=0; c<size; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				tallest = Math.max(tallest, map[r][c]);
			}
		}

		for(int i=0; i<=tallest; i++)
			rain(i);

		System.out.println(maxSafe);
 	}

 	private static void rain(int h) {
		int[][] sunk = new int[size][size];
		for(int r=0; r<size; r++) {
			for(int c=0; c<size; c++) {
				if(map[r][c] <= h)
					sunk[r][c] = 1;
			}
		}
		maxSafe = Math.max(maxSafe, count(sunk));
	}

	private static int count(int[][] sunk) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[size][size];
		int[] rMove = new int[]{-1,1,0,0};
		int[] cMove = new int[]{0,0,-1,1};
		int count = 0;

		for(int r=0; r<size; r++) {
			for(int c=0; c<size; c++) {
				if(!visited[r][c] && sunk[r][c]==0) {
					visited[r][c] = true;
					queue.offer(new int[]{r,c});
					count++;
				}

				while(!queue.isEmpty()) {
					int[] tmp = queue.poll();
					int ro = tmp[0];
					int co = tmp[1];

					for(int i=0; i<4; i++) {
						int nr = ro + rMove[i];
						int nc = co + cMove[i];

						if(nr<0 || nc<0 || nr>=size || nc>=size)
							continue;

						if(!visited[nr][nc] && sunk[nr][nc]==0) {
							visited[nr][nc] = true;
							queue.offer(new int[]{nr,nc});
						}
					}
				}
			}
		}
		return count;
	}
}