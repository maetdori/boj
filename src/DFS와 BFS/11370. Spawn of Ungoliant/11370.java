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

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while((line = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(line);

			col = Integer.parseInt(st.nextToken());
			row = Integer.parseInt(st.nextToken());

			map = new char[row][col];

			for (int r = 0; r < row; r++) {
				map[r] = br.readLine().toCharArray();
				for (int c = 0; c < col; c++) {
					if (map[r][c] == 'S')
						queue.offer(new Node(r, c));
				}
			}

			bfs();

			for(int r=0; r<row; r++) {
				for(int c=0; c<col; c++) {
					System.out.print(map[r][c]);
				}
				System.out.println();
			}
		}
	}

	private static void bfs() {
		int[] rMove = new int[]{-1,1,0,0};
		int[] cMove = new int[]{0,0,-1,1};

		while(!queue.isEmpty()) {
			Node node = queue.poll();

			for(int i=0; i<4; i++) {
				int nr = node.r + rMove[i];
				int nc = node.c + cMove[i];

				if(nr<0 || nc<0 || nr>=row || nc>=col)
					continue;

				if(map[nr][nc] == 'T') {
					map[nr][nc] = 'S';
					queue.offer(new Node(nr,nc));
				}
			}
		}
	}
}