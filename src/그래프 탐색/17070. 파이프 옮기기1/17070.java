import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Pos {
	public int r;
	public int c;

	Pos(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

class Pipe {
	public Pos p1;
	public Pos p2;

	Pipe(Pos p1, Pos p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
}

class Main {
	static int N;
	static int[][] map;
	static int count = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for(int r=0; r<N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(new Pipe(new Pos(0,0), new Pos(0,1)), 0);
		System.out.println(count);
	}

	private static void dfs(Pipe now, int status) {
		if(now.p2.r == N-1 && now.p2.c == N-1) {
			count++;
			return;
		}

		switch(status) {
			case 0: //가로
				if(valid(now.p2.r, now.p2.c, now.p2.r, now.p2.c+1)) { //가로
					dfs(new Pipe(now.p2, new Pos(now.p2.r, now.p2.c+1)), 0);
				}
				if(valid(now.p2.r, now.p2.c, now.p2.r+1, now.p2.c+1)) { //대각선
					dfs(new Pipe(now.p2, new Pos(now.p2.r+1, now.p2.c+1)), 2);
				}
				break;

			case 1: //세로
				if(valid(now.p2.r, now.p2.c, now.p2.r+1, now.p2.c)) { //세로
					dfs(new Pipe(now.p2, new Pos(now.p2.r+1, now.p2.c)), 1);
				}
				if(valid(now.p2.r, now.p2.c, now.p2.r+1, now.p2.c+1)) { //대각선
					dfs(new Pipe(now.p2, new Pos(now.p2.r+1, now.p2.c+1)), 2);
				}
				break;

			case 2: //대각선
				if(valid(now.p2.r, now.p2.c, now.p2.r, now.p2.c+1)) { //가로
					dfs(new Pipe(now.p2, new Pos(now.p2.r, now.p2.c+1)), 0);
				}
				if(valid(now.p2.r, now.p2.c, now.p2.r+1, now.p2.c)) { //세로
					dfs(new Pipe(now.p2, new Pos(now.p2.r+1, now.p2.c)), 1);
				}
				if(valid(now.p2.r, now.p2.c, now.p2.r+1, now.p2.c+1)) { //대각선
					dfs(new Pipe(now.p2, new Pos(now.p2.r+1, now.p2.c+1)), 2);
				}
				break;
		}
	}

	//시간초과
	private static int bfs() {
		Queue<Pipe> queue = new LinkedList<>();
		queue.offer(new Pipe(new Pos(0,0), new Pos(0,1)));
		int cnt = 0;

		while(!queue.isEmpty()) {
			Queue<Pos> next = new LinkedList<>();
			Pipe now = queue.poll();

			if(now.p2.r == N-1 && now.p2.c == N-1) {
				cnt++;
				continue;
			}

			if(now.p1.r == now.p2.r) { //가로
				next.offer(new Pos(now.p2.r, now.p2.c+1)); //가로
				next.offer(new Pos(now.p2.r+1, now.p2.c+1)); //대각선
			}

			else if(now.p1.c == now.p2.c) { //세로
				next.offer(new Pos(now.p2.r+1, now.p2.c)); //세로
				next.offer(new Pos(now.p2.r+1, now.p2.c+1)); //대각선
			}

			else { //대각선
				next.offer(new Pos(now.p2.r, now.p2.c+1)); //가로
				next.offer(new Pos(now.p2.r+1, now.p2.c)); //세로
				next.offer(new Pos(now.p2.r+1, now.p2.c+1)); //대각선
			}

			while(!next.isEmpty()) {
				Pos candidate = next.poll();
				int nr = candidate.r;
				int nc = candidate.c;

				if(valid(now.p2.r, now.p2.c, nr, nc))
					queue.offer(new Pipe(now.p2, new Pos(nr, nc)));
			}
		}
		return cnt;
	}

	private static boolean valid(int r, int c, int nr, int nc) {
		if(nr<0 || nc<0 || nr>=N || nc>=N)
			return false;

		if(map[nr][nc] == 1)
			return false;

		if(r!=nr && c!=nc) //대각선 이동일 경우
			if(map[r][nc] == 1 || map[nr][c] == 1)
				return false;

		return true;
	}
}