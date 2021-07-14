import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int N;
	static int M;
	static char[][] board;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][M];

		for(int i=0; i<N; i++) {
			board[i] = br.readLine().toCharArray();
		}

		getMinimalCost();
		System.out.println(min);
	}

	private static void getMinimalCost() {
		for(int i=0; i<=N-8; i++) {
			for(int j=0; j<=M-8; j++) {
				int cnt = getColored(i,j);
				min = Math.min(min, Math.min(cnt, 64-cnt));
			}
		}
	}

	private static int getColored(int r, int c) {
		int cnt = 0;
		char initColor = board[r][c];

		for(int i=r; i<r+8; i++) {
			for(int j=c; j<c+8; j++) {
				if(((i-r) + (j-c)) % 2 == 0) {
					if(board[i][j] != initColor) cnt++;
				}
				else {
					if(board[i][j] == initColor) cnt++;
				}
			}
		}
		return cnt;
	}
}