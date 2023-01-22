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
	static Queue<Node> wolf = new LinkedList<>();
	static boolean[][] visited;
	static int[] rMove = new int[]{-1,1,0,0};
	static int[] cMove = new int[]{0,0,-1,1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());

		map = new char[row][col];
		visited = new boolean[row][col];

		for(int r=0; r<row; r++) {
			map[r] = br.readLine().toCharArray();
			for(int c=0; c<col; c++) {
				if(map[r][c] == 'W') {
					visited[r][c] = true;
					wolf.offer(new Node(r, c));
				}
			}
		}

		if(!bfs())
			System.out.println(0);
		else {
			System.out.println(1);
			for(int r=0; r<row; r++) {
				for(int c=0; c<col; c++) {
					System.out.print(map[r][c]);
				}
				System.out.println();
			}
		}
	}

	private static boolean bfs() {
		while(!wolf.isEmpty()) {
			Node node = wolf.poll();

			for(int i=0; i<4; i++) {
				int nr = node.r + rMove[i];
				int nc = node.c + cMove[i];

				if(nr<0 || nc<0 || nr>=row || nc>=col)
					continue;

				if(map[nr][nc] == 'S')
					return false;

				if(!visited[nr][nc] && map[nr][nc] == '.') {
					if(sheepCheck(nr,nc))
						map[nr][nc] = 'D';
					else {
						visited[nr][nc] = true;
						wolf.offer(new Node(nr,nc));
					}
				}
			}
		}
		return true;
	}

	private static boolean sheepCheck(int r, int c) {
		for(int i=0; i<4; i++) {
			int nr = r + rMove[i];
			int nc = c + cMove[i];

			if(nr<0 || nc<0 || nr>=row || nc>=col)
				continue;

			if(map[nr][nc] == 'S')
				return true;
		}
		return false;
	}
}