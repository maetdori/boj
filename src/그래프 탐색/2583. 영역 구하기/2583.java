import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int[][] matrix;
	static int row;
	static int col;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		int square = Integer.parseInt(st.nextToken());

		matrix = new int[row][col];

		for(int i=0; i<square; i++) {
			st = new StringTokenizer(br.readLine());
			int[] pos = new int[4];
			for(int j=0; j<4; j++) {
				pos[j] = Integer.parseInt(st.nextToken());
			}
			drawSquare(pos);
		}

		count();
	}

	public static void drawSquare(int[] pos) {
		int c1 = pos[0];
		int r1 = pos[1];
		int c2 = pos[2];
		int r2 = pos[3];

		for(int r=r1; r<r2; r++)
			for(int c=c1; c<c2; c++)
				matrix[r][c] = 1;
	}

	public static void count() {
		Queue<int[]> queue = new LinkedList<>();
		List<Integer> parts = new ArrayList<>();
		boolean[][] visited = new boolean[row][col];
		int[] rMove = new int[]{-1,1,0,0};
		int[] cMove = new int[]{0,0,-1,1};
		int count = 0;

		for(int r=0; r<row; r++) {
			for(int c=0; c<col; c++) {
				if(!visited[r][c] && matrix[r][c] == 0) {
					visited[r][c] = true;
 					queue.offer(new int[]{r,c});
					count = 1;
				}

				while(!queue.isEmpty()) {
					int[] tmp = queue.poll();
					int ro = tmp[0];
					int co = tmp[1];

					for(int i=0; i<4; i++) {
						int nr = ro + rMove[i];
						int nc = co + cMove[i];

						if(nr<0 || nc<0 || nr>=row || nc>=col)
							continue;

						if(!visited[nr][nc] && matrix[nr][nc] == 0) {
							visited[nr][nc] = true;
							queue.offer(new int[]{nr,nc});
							count++;
						}
					}
				}
				if(count!=0) parts.add(count);
				count=0;
			}
		}
		Collections.sort(parts);
		System.out.println(parts.size());
		for(Integer p: parts)
			System.out.print(p + " ");
	}
}