import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int R;
	static int C;
	static int[][] map;
	static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];

		for(int r=0; r<R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		boolean[][] visited = new boolean[R][C];
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				visited[r][c] = true;
				dfs(1, new Node(r,c,map[r][c]), visited);
				visited[r][c] = false;
			}
		}
		//bfs();
		except();

		System.out.println(max);
	}

	private static void dfs(int depth, Node node, boolean[][] visited) {
		int[] rMove = new int[]{-1,1,0,0};
		int[] cMove = new int[]{0,0,-1,1};

		if(depth == 4) {
			max = Math.max(max, node.sum);
			return;
		}

		for(int i=0; i<4; i++) {
			int nr = node.r + rMove[i];
			int nc = node.c + cMove[i];

			if(nr<0 || nc<0 || nr>=R || nc>=C) continue;
			if(visited[nr][nc]) continue;

			visited[nr][nc] = true;
			dfs(depth+1, new Node(nr, nc, node.sum + map[nr][nc]), visited);
			visited[nr][nc] = false;
		}
	}

	//BFS: 시간초과 발생
	private static void bfs() {
		int[] rMove = new int[]{-1,1,0,0};
		int[] cMove = new int[]{0,0,-1,1};

		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				Queue<Node> queue = new LinkedList<>();
				int[][] visited = new int[R][C]; //이 부분에서 시간초과 발생(불필요하게 큰 배열 선언)

				queue.offer(new Node(r,c,map[r][c]));
				visited[r][c] = 1;

				while(!queue.isEmpty()) {
					Node node = queue.poll();

					if(visited[node.r][node.c] > 4) break;

					if(visited[node.r][node.c] == 4)
						max = Math.max(max,node.sum);

					for(int i=0; i<4; i++) {
						int nr = node.r + rMove[i];
						int nc = node.c + cMove[i];

						if(nr<0 || nc<0 || nr>=R || nc>=C) continue;
						if(visited[nr][nc] != 0) continue;

						visited[nr][nc] = visited[node.r][node.c] + 1;
						queue.offer(new Node(nr, nc, node.sum + map[nr][nc]));
					}
				}
			}
		}
	}

	//凸 모양
	private static void except() {
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				if(r>0 && c+2<C)
					max = Math.max(max, map[r][c] + map[r][c+1] + map[r][c+2] + map[r-1][c+1]);
				if(r+1<R && c+2<C)
					max = Math.max(max, map[r][c] + map[r][c+1] + map[r][c+2] + map[r+1][c+1]);
				if(c>0 && r+2<R)
					max = Math.max(max, map[r][c] + map[r+1][c] + map[r+2][c] + map[r+1][c-1]);
				if(c+1<C && r+2<R)
					max = Math.max(max, map[r][c] + map[r+1][c] + map[r+2][c] + map[r+1][c+1]);
			}
		}
	}
}

class Node {
	int r;
	int c;
	int sum;

	Node(int r, int c, int sum) {
		this.r = r;
		this.c = c;
		this.sum = sum;
	}
}