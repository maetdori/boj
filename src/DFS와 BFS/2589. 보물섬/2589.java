import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Node {
	public int r;
	public int c;
	public int cnt;

	Node(int r, int c, int cnt) {
		this.r = r;
		this.c = c;
		this.cnt = cnt;
	}
}

class Main {
	static int row;
	static int col;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());

		map = new char[row][col];

		for(int r=0; r<row; r++) {
			map[r] = br.readLine().toCharArray();
		}

		int max = 0;
		for(int r=0; r<row; r++) {
			for(int c=0; c<col; c++) {
				if(map[r][c] == 'L')
					max = Math.max(max, bfs(new Node(r,c,0)));
			}
		}

		System.out.println(max);
	}

	private static int bfs(Node start) {
		Queue<Node> queue = new LinkedList<>();
		boolean[][] visited = new boolean[row][col];
		int[] rMove = new int[]{-1,1,0,0};
		int[] cMove = new int[]{0,0,-1,1};
		int cnt = 0;

		queue.offer(start);
		visited[start.r][start.c] = true;

		while(!queue.isEmpty()) {
			Node node = queue.poll();
			cnt = node.cnt;

			for(int i=0; i<4; i++) {
				int nr = node.r + rMove[i];
				int nc = node.c + cMove[i];

				if(nr<0 || nc<0 || nr>=row || nc>=col)
					continue;

				if(!visited[nr][nc] && map[nr][nc] == 'L') {
					visited[nr][nc] = true;
					queue.offer(new Node(nr,nc,cnt+1));
				}
			}
		}
		return cnt;
	}
}