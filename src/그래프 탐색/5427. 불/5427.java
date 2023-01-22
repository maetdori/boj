import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Node {
	int r;
	int c;
	int time;

	Node(int r, int c, int time) {
		this.r = r;
		this.c = c;
		this.time = time;
	}
}

class Main {
	static int R;
	static int C;
	static char[][] map;
	static Node start;
	static int[][] fired;
	static int[] rMove = new int[]{-1,1,0,0};
	static int[] cMove = new int[]{0,0,-1,1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());

		for(int t=0; t<tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			map = new char[R][C];
			fired = new int[R][C];

			for(int r=0; r<R; r++) {
				map[r] = br.readLine().toCharArray();
				for(int c=0; c<C; c++) {
					if(map[r][c] == '@')
						start = new Node(r,c,1);
				}
			}

			fire();
			int time = escape();
			if(time == -1) System.out.println("IMPOSSIBLE");
			else System.out.println(time);
		}
	}

	static int escape() {
		Queue<Node> queue = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];

		queue.offer(start);
		visited[start.r][start.c] = true;

		while(!queue.isEmpty()) {
			Node now = queue.poll();

			if(isOnBorder(now)) {
				return now.time;
			}

			for(int i=0; i<4; i++) {
				int nr = now.r + rMove[i];
				int nc = now.c + cMove[i];

				if(nr<0 || nc<0 || nr>=R || nc>=C || visited[nr][nc]) continue;

				if(map[nr][nc] == '.' && (fired[nr][nc]==0 || fired[nr][nc] > now.time+1)) {
					queue.offer(new Node(nr,nc,now.time+1));
					visited[nr][nc] = true;
				}
			}
		}
		return -1;
	}

	static void fire() {
		Queue<Node> queue = new LinkedList<>();

		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				if(map[r][c] == '*') {
					queue.offer(new Node(r,c,1));
					fired[r][c] = 1;
				}
			}
		}
		while(!queue.isEmpty()) {
			Node fire = queue.poll();

			for(int i=0; i<4; i++) {
				int nr = fire.r + rMove[i];
				int nc = fire.c + cMove[i];

				if(nr<0 || nc<0 || nr>=R || nc>=C) continue;
				if(map[nr][nc] == '#' || fired[nr][nc] != 0) continue;

				queue.offer(new Node(nr,nc,fire.time+1));
				fired[nr][nc] = fire.time + 1;
			}
		}
	}

	static boolean isOnBorder(Node node) {
		if(node.r == 0 || node.r == R-1 || node.c == 0 || node.c == C-1)
			return true;
		return false;
	}
}