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
	static char[][] map;
	static int row;
	static int col;
	static Queue<Node> queue = new LinkedList<>();
	static int count = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());

		map = new char[row][col];

		for(int r=0; r<row; r++)
			map[r] = br.readLine().toCharArray();

		bfs();

		System.out.println(count);
	}

	private static void bfs() {
		int[] rMove = new int[]{-1,1,0,0};
		int[] cMove = new int[]{0,0,-1,1};
		boolean[][] visited = new boolean[row][col];

		for(int r=0; r<row; r++) {
			for(int c=0; c<col; c++) {
				if(!visited[r][c] && map[r][c] == 'L') {
					queue.offer(new Node(r,c));
					count++;
				}

				while (!queue.isEmpty()) {
					Node node = queue.poll();

					for (int i = 0; i < 4; i++) {
						int nr = node.r + rMove[i];
						int nc = node.c + cMove[i];

						if (nr < 0 || nc < 0 || nr >= row || nc >= col)
							continue;

						if(!visited[nr][nc] && map[nr][nc] == 'L') {
							queue.offer(new Node(nr,nc));
							visited[nr][nc] = true;
						}

						if (map[nr][nc] == 'C') {
							map[nr][nc] = 'L';
							queue.offer(new Node(nr, nc));
							visited[nr][nc] = true;
						}
					}
				}
			}
		}
	}
}