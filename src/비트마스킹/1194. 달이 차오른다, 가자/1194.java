import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Node {
	int r;
	int c;
	int key;
	int cnt;

	Node(int r, int c, int key, int cnt) {
		this.r = r;
		this.c = c;
		this.key = key;
		this.cnt = cnt;
	}
}

class Main {
	static int R;
	static int C;
	static Node start;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];

		for(int r=0; r<R; r++) {
			map[r] = br.readLine().toCharArray();
			for(int c=0; c<C; c++) {
				if(map[r][c] == '0')
					start = new Node(r,c,0, 0);
			}
		}
		System.out.println(bfs());
	}

	private static int bfs() {
		int[] rMove = new int[]{-1,1,0,0};
		int[] cMove = new int[]{0,0,-1,1};
		Queue<Node> queue = new LinkedList<>();
		boolean[][][] visited = new boolean[R][C][1<<6];

		queue.offer(start);
		visited[start.r][start.c][0] = true;

		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			int key = cur.key;
			int cnt = cur.cnt;

			if(map[cur.r][cur.c] == '1')
				return cnt;

			for(int i=0; i<4; i++) {
				int nr = cur.r + rMove[i];
				int nc = cur.c + cMove[i];

				if(nr<0 || nc<0 || nr>=R || nc>=C) continue;
				if(visited[nr][nc][key] || map[nr][nc] == '#') continue;

				if('a' <= map[nr][nc] && map[nr][nc] <= 'f') {
					int nk = key | (1 << (map[nr][nc]-'a'));
					if(visited[nr][nc][nk]) continue;
					visited[nr][nc][key] = true;
					visited[nr][nc][nk] = true;
					queue.offer(new Node(nr,nc,nk,cnt+1));
				}

				else if('A' <= map[nr][nc] && map[nr][nc] <= 'F') {
					if((key & (1 << (map[nr][nc] - 'A'))) == 0) continue;
					visited[nr][nc][key] = true;
					queue.offer(new Node(nr,nc,key,cnt+1));
				}

				else {
					visited[nr][nc][key] = true;
					queue.offer(new Node(nr,nc,key,cnt+1));
				}
			}
		}
		return -1;
	}
}