import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Node {
	public int r;
	public int c;

	Node(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

class Main {
	static int row;
	static int col;
	static int[][] map;
	static int[] rMove = new int[]{-1,1,0,0};
	static int[] cMove = new int[]{0,0,-1,1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());

		map = new int[row][col];

		for(int r=0; r<row; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<col; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int num;
		int cnt = 0;
		while((num = iceberg()) != 0) {
			if(num >= 2) {
				System.out.println(cnt);
				return;
			}
			melt();
			cnt++;
		}
		System.out.println(0);
	}

	private static void melt() {
		int[][] meltNum = new int[row][col];

		for(int r=0; r<row; r++) {
			for(int c=0; c<col; c++) {
				if(map[r][c] != 0) {
					for(int i=0; i<4; i++) {
						int nr = r + rMove[i];
						int nc = c + cMove[i];

						if(nr<0 || nc<0 || nr>=row || nc>=col)
							 continue;

						if(map[nr][nc] == 0) {
							meltNum[r][c]++;
						}
					}
				}
			}
		}
		for(int r=0; r<row; r++) {
			for(int c=0; c<col; c++) {
				if(map[r][c] > meltNum[r][c])
					map[r][c] -= meltNum[r][c];
				else map[r][c] = 0;
			}
		}
	}

	private static int iceberg() {
		Queue<Node> queue = new LinkedList<>();
		boolean[][] visited = new boolean[row][col];
		int count = 0;

		for(int r=0; r<row; r++) {
			for(int c=0; c<col; c++) {
				if(!visited[r][c] && map[r][c] != 0) {
					count++;
					visited[r][c] = true;
					queue.offer(new Node(r,c));
				}

				while(!queue.isEmpty()) {
					Node node = queue.poll();

					for(int i=0; i<4; i++) {
						int nr = node.r + rMove[i];
						int nc = node.c + cMove[i];

						if(nr<0 || nc<0 || nr>=row || nc>=col)
							continue;

						if(!visited[nr][nc] && map[nr][nc] != 0) {
							visited[nr][nc] = true;
							queue.offer(new Node(nr,nc));
						}
					}
				}
			}
		}
		return count;
	}
}