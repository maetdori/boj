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
	static int[][] route; //route[i][j]: (i,j)에서 도착점까지 가는 방법의 수 저장
	static int[] rMove = new int[]{-1,1,0,0};
	static int[] cMove = new int[]{0,0,-1,1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		route = new int[R][C];

		for(int r=0; r<R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				route[r][c] = -1;
			}
		}

		findRoute(0,0);
		System.out.println(route[0][0]);
	}

	private static int findRoute(int r, int c) {
		if(r==R-1 && c==C-1)
			return 1;

		if(route[r][c] != -1)
			return route[r][c];

		route[r][c] = 0;
		for(int i=0; i<4; i++) {
			int nr = r + rMove[i];
			int nc = c + cMove[i];

			if(nr<0 || nc<0 || nr>=R || nc>=C) continue;
			if(map[nr][nc] >= map[r][c]) continue;

			route[r][c] += findRoute(nr, nc);
		}

		return route[r][c];
	}
}