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
	static int N;
	static int M;
	static int map[][];
	static List<Node> virus;
	static int[] rMove = new int[]{-1,1,0,0};
	static int[] cMove = new int[]{0,0,-1,1};
	static int min = Integer.MAX_VALUE;
	static int emptySpace = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		virus = new ArrayList<>();
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 2)
					virus.add(new Node(r, c, 0));
				if(map[r][c] == 0)
					emptySpace++;
			}
		}

		if(emptySpace == 0) {
			System.out.println(0);
			return;
		}

		activate(0, 0, new Node[M]);

		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}

		System.out.println(min);
	}

	private static void activate(int depth, int start, Node[] active) {
		if(depth == M) {
			spread(active);
			return;
		}

		for(int i=start; i<virus.size(); i++) {
			active[depth] = virus.get(i);
			activate(depth+1, i+1, active);
		}
	}

	private static void spread(Node[] active) {
		Queue<Node> queue = new LinkedList<>();
		boolean[][] infected = new boolean[N][N];
		int empty = emptySpace;

		for(Node node: active) {
			queue.offer(node);
			infected[node.r][node.c] = true;
		}

		while(!queue.isEmpty()) {
			Node node = queue.poll();

			for(int i=0; i<4; i++) {
				int nr = node.r + rMove[i];
				int nc = node.c + cMove[i];
				int cnt = node.cnt;

				if(nr<0 || nc<0 || nr>=N || nc>=N) continue;

				if(infected[nr][nc] || map[nr][nc] == 1) continue;

				if(map[nr][nc] == 0)
					empty--;

				if(empty == 0)
					min = Math.min(min, cnt+1);

				infected[nr][nc] = true;
				queue.offer(new Node(nr, nc, cnt+1));
			}
		}
	}
}