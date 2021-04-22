import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int N;
	static int L;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int valid = 0;
		for(int i=0; i<N; i++) {
			if(checkRow(i)) {
				valid++;
			}
			if(checkCol(i)) {
				valid++;
			}
		}
		System.out.println(valid);
	}

	public static boolean checkRow(int r) {
		boolean[] charged = new boolean[N];

		for(int c=0; c<N-1; c++) {
			int now = map[r][c];
			int next = map[r][c+1];

			if(next == now) continue;

			if(next == now+1) {
				for(int i=0; i<L; i++) {
					if(c-i < 0 || map[r][c-i] != now || charged[c-i]) return false;
				}
				continue;
			}

			if(next == now-1) {
				for(int i=1; i<=L; i++) {
					if(c+i >= N || map[r][c+i] != next) return false;
					charged[c+i] = true;
				}
				c += (L-1);
				continue;
			}
			return false;
		}
		return true;
	}

	public static boolean checkCol(int c) {
		boolean[] charged = new boolean[N];

		for(int r=0; r<N-1; r++) {
			int now = map[r][c];
			int next = map[r+1][c];

			if(next == now) continue;

			if(next == now+1) {
				for(int i=0; i<L; i++) {
					if(r-i < 0 || map[r-i][c] != now || charged[r-i]) return false;
				}
				continue;
			}

			if(next == now-1) {
				for(int i=1; i<=L; i++) {
					if(r+i >= N || map[r+i][c] != next) return false;
					charged[r+i] = true;
				}
				r += (L-1);
				continue;
			}
			return false;
		}
		return true;
	}
}
