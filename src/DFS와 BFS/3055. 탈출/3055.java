import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Node {
	public int r;
	public int c;

	Node(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

class Main {
	static char[][] map;
	static int[] rMove = new int[]{-1,1,0,0};
	static int[] cMove = new int[]{0,0,-1,1};
	static int row, col;
	static Queue<Node> water = new LinkedList<>();
	static Queue<Node> dochi = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		map = new char[row][col];

		for(int r=0; r<row; r++) {
			map[r] = br.readLine().toCharArray();
			for(int c=0; c<col; c++) {
				if(map[r][c] == '*')
					water.add(new Node(r,c));
				if(map[r][c] == 'S') {
					dochi.add(new Node(r,c));
				}
			}
		}

		int cnt = 0;
		while(!dochi.isEmpty()) {
			cnt++;
			waterMove();
			if(dochiMove()) {
				System.out.println(cnt);
				return;
			}
		}
		System.out.println("KAKTUS");
	}

	public static void waterMove() {
		int size = water.size();
		for(int s=0; s<size; s++) {
			Node wat = water.poll();

			for(int i=0; i<4; i++) {
				int nr = wat.r + rMove[i];
				int nc = wat.c + cMove[i];

				if(nr<0 || nc<0 || nr>=row || nc>=col)
					continue;

				if(map[nr][nc] == '.') {
					map[nr][nc] = '*';
					water.offer(new Node(nr,nc));
				}
			}
		}
	}

	public static boolean dochiMove() {
		int size = dochi.size();

		for(int s=0; s<size; s++) {
			Node doc = dochi.poll();

			for(int i=0; i<4; i++) {
				int nr = doc.r + rMove[i];
				int nc = doc.c + cMove[i];

				if(nr<0 || nc<0 || nr>=row || nc>=col)
					continue;

				if(map[nr][nc] == 'D') {
					dochi.offer(new Node(nr,nc));
					return true;
				}

				if(map[nr][nc] == '.') {
					map[nr][nc] = 'S';
					dochi.offer(new Node(nr,nc));
				}
			}
		}
		return false;
	}
}