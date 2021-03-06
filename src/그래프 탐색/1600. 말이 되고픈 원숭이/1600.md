import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Node {
	int r;
	int c;
	int hor;
	int move;

	Node(int r, int c, int hor, int move) {
		this.r = r;
		this.c = c;
		this.hor = hor;
		this.move = move;
	}
}

class Main {
	static int K;
	static int row;
	static int col;
	static int[][] map;
	static boolean[][][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		col = Integer.parseInt(st.nextToken());
		row = Integer.parseInt(st.nextToken());
		map = new int[row][col];
		visited = new boolean[row][col][K+1];

		for(int r=0; r<row; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<col; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<Node> queue = new LinkedList<>();
		int[] rMove = new int[]{-1,1,0,0,-2,-2,-1,-1,1,1,2,2};
		int[] cMove = new int[]{0,0,-1,1,-1,1,-2,2,-2,2,-1,1};
		queue.offer(new Node(0,0,K,0));
		visited[0][0][K] = true;

		while(!queue.isEmpty()) {
			Node node = queue.poll();
			int k = node.hor;
			int move = node.move;

			if(node.r == row-1 && node.c == col-1)
				return move;

			for(int i=0; i<(k>0 ? 12 : 4); i++) {
				int nr = node.r + rMove[i];
				int nc = node.c + cMove[i];
				int nk = (i>=4 ? k-1 : k);
				int nmove = move+1;

				if(!valid(nr,nc,nk)) continue;

				queue.offer(new Node(nr, nc, nk, nmove));
				visited[nr][nc][nk] = true;
			}
		}
		return -1;
	}

	private static boolean valid(int r, int c, int k) {
		if(r<0 || c<0 || r>=row || c>=col)
			return false;
		if(visited[r][c][k])
			return false;
		if(map[r][c]==1)
			return false;
		return true;
	}
}