import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int R;
	static int C;
	static int[][] map;
	static boolean[][] visited;
	static boolean found = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		R = H;
		C = N*2-1;
		map = new int[R][C];

		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())*2 -1;
			map[r][c] = 1;
		}

		for(int i=0; i<=3; i++) {
			visited = new boolean[R][C];
			if((N-1)*H-M < i) break;
			dfs(0,i);
			if(found) {
				System.out.println(i);
				return;
			}
		}
		System.out.println(-1);
	}

	private static void dfs(int depth, int num) {
		if(depth == num) {
			if(checkOneToOne())
				found = true;
			return;
		}

		for(int c=1; c<C; c+=2) {
			for(int r=0; r<R; r++) {
				if(visited[r][c] || !isAvailable(r,c)) continue;
				map[r][c] = 1;
				visited[r][c] = true;
				dfs(depth+1, num);
				map[r][c] = 0;
				visited[r][c] = false;
			}
		}
	}

	private static boolean checkOneToOne() {
		for(int c=0; c<C; c+=2) {
			int start = c;
			int col = c;
			for(int r=0; r<R; r++) {
				if(col != 0 && map[r][col-1]==1) {
					col -= 2;
					continue;
				}
				if(col != C-1 && map[r][col+1]==1) {
					col += 2;
					continue;
				}
			}
			if(col != start) return false;
		}
		return true;
	}

	private static boolean isAvailable(int r, int c) {
		if(map[r][c] == 1) return false;
		if(c-2 >= 0 && map[r][c-2] == 1) return false;
		if(c+2 < C && map[r][c+2] == 1) return false;
		return true;
	}
}