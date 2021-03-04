import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int[][] matrix;
	static boolean[][][] visited;
	static int[] rMove = new int[]{-1,1,0,0};
	static int[] cMove = new int[]{0,0,-1,1};
	static int row;
	static int col;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());

		matrix = new int[row][col];
		visited = new boolean[2][row][col];

		for(int i=0; i<row; i++) {
			String[] tmp = br.readLine().split("");
			for(int j=0; j<col; j++) {
				matrix[i][j] = Integer.parseInt(tmp[j]);
			}
		}

		bfs();
	}

	private static void bfs() {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(0,0,1,0));

		while(!queue.isEmpty()) {
			Node node = queue.poll();
			int r = node.row;
			int c = node.col;
			int cnt = node.cnt;
			int punch = node.punch;

			if(r==row-1 && c==col-1) {
				System.out.println(cnt);
				return;
			}

			for(int i=0; i<4; i++) {
				int nr = r + rMove[i];
				int nc = c + cMove[i];

				if(nr<0 || nc<0 || row<=nr || col<=nc)
					continue;

				if(matrix[nr][nc]==0) { //벽이 아닐 경우
					if(!visited[punch][nr][nc]) {
						visited[punch][nr][nc] = true;
						queue.offer(new Node(nr, nc, cnt + 1, punch));
					}
				}
				else { //벽일 경우
					if(punch==0 && !visited[1][nr][nc]) { //벽을 부수지 않은 상태일 경우
						visited[1][nr][nc] = true;
						queue.offer(new Node(nr,nc,cnt+1,1));
					}
				}
			}
		}
		System.out.println(-1);
	}
}

class Node {
	int row;
	int col;
	int cnt;
	int punch;

	Node(int row, int col, int cnt, int punch) {
		this.row = row;
		this.col = col;
		this.cnt = cnt;
		this.punch = punch;
	}
}