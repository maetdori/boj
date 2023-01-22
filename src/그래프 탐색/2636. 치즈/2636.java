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

		int cheese = 0;
		int hour = 0;
		for(int r=0; r<row; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<col; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 1) cheese++;
			}
		}

		if(cheese == 0) {
			System.out.println(0);
			System.out.println(0);
			return;
		}

		while(true) {
			hour++;
			checkOutside();
			int melted = meltCheese();
			if(cheese-melted == 0) {
				System.out.println(hour);
				System.out.println(melted);
				return;
			}
			checkOutside();
			cheese -= melted;
		}
	}

	public static int meltCheese() {
		int melted = 0;

		for(int r=0; r<row; r++) {
			for(int c=0; c<col; c++) {
				if(map[r][c] == 1 && nextToOutside(r,c)) {
					map[r][c] = 0;
					melted++;
				}
			}
		}
		return melted;
	}

	public static void checkOutside() {
		Queue<Node> queue = new LinkedList<>();
		boolean[][] visited = new boolean[row][col];

		for(int r=0; r<row; r++) {
			for(int c=0; c<col; c++) {

				if(map[r][c] == 1) continue;

				queue.offer(new Node(r, c));
				visited[r][c] = true;

				while (!queue.isEmpty()) {
					Node node = queue.poll();
					map[node.r][node.c] = 2;

					for (int i = 0; i < 4; i++) {
						int nr = node.r + rMove[i];
						int nc = node.c + cMove[i];

						if (nr < 0 || nc < 0 || nr >= row || nc >= col)
							continue;

						if (!visited[nr][nc] && map[nr][nc] != 1) {
							visited[nr][nc] = true;
							queue.offer(new Node(nr, nc));
						}
					}
				}
				return;
			}
		}
	}

	public static boolean nextToOutside(int r, int c) {
		for(int i=0; i<4; i++) {
			int nr = r + rMove[i];
			int nc = c + cMove[i];

			if(map[nr][nc] == 2)
				return true;
		}

		return false;
	}
}