import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Node {
	int r;
	int c;

	Node(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

class Main {
	static int R;
	static int C;
	static int[][] map;
	static int[][] air;
	static int[] rMove = new int[]{-1,1,0,0};
	static int[] cMove = new int[]{0,0,-1,1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		air = new int[R][C];

		for(int r=0; r<R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				air[r][c] = map[r][c];
			}
		}

		int cnt = 0;
		while(true) {
			outsideAir();
			if(!melt()) break;
			cnt++;
		}
		System.out.println(cnt);
	}

	private static boolean melt() {
		List<Node> melt = new ArrayList<>();
		boolean flag = false;

		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				if(map[r][c] == 1) {
					flag = true;
					if(closeToAir(r, c))
						melt.add(new Node(r, c));
				}
			}
		}

		for(Node node: melt) {
			map[node.r][node.c] = 0;
		}

		return flag;
	}

	private static boolean closeToAir(int r, int c) {
		int cnt = 0;

		for(int i=0; i<4; i++) {
			int nr = r + rMove[i];
			int nc = c + cMove[i];

			if(nr<0 || nc<0 || nr>=R || nc>=C) continue;

			if(air[nr][nc] == 2) cnt++;

			if(cnt == 2) return true;
		}
		return false;
	}

	private static void outsideAir() {
		Queue<Node> queue = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		queue.offer(new Node(0,0));

		while(!queue.isEmpty()) {
			Node node = queue.poll();
			air[node.r][node.c] = 2;

			for (int i = 0; i < 4; i++) {
				int nr = node.r + rMove[i];
				int nc = node.c + cMove[i];

				if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue;

				if (!visited[nr][nc] && map[nr][nc] == 0) {
					queue.offer(new Node(nr, nc));
					visited[nr][nc] = true;
				}
			}
		}

	}
}

