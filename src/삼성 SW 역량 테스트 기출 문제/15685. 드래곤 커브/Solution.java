import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Pos {
	int x;
	int y;

	Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Dragon {
	Pos start;
	int gen;
	List<Integer> curve;

	Dragon(Pos start, int dir, int gen) {
		this.start = start;
		this.gen = gen;
		this.curve = new ArrayList<>();
		this.curve.add(dir);
	}
}

class Main {
	static int N;
	static Dragon[] dragons;
	static int[] xMove = {1,0,-1,0};
	static int[] yMove = {0,-1,0,1};
	static boolean[][] plane = new boolean[101][101];
	static int maxX = 0;
	static int maxY = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dragons = new Dragon[N];

		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int gen = Integer.parseInt(st.nextToken());
			dragons[i] = new Dragon(new Pos(x,y), dir, gen);
		}

		for(int i=0; i<N; i++) {
			int gen = dragons[i].gen;
			for(int j=0; j<gen; j++) {
				getNextGen(dragons[i]);
			}
		}

		displayCurve();
		System.out.println(findSquare());
	}

	private static int findSquare() {
		int cnt = 0;
		for(int x=0; x<maxX; x++) {
			for(int y=0; y<maxY; y++) {
				if(!plane[x][y]) continue;
				if(plane[x+1][y] && plane[x][y+1] && plane[x+1][y+1])
					cnt++;
			}
		}
		return cnt;
	}

	private static void displayCurve() {
		for(int i=0; i<N; i++) {
			Dragon dragon = dragons[i];
			int x = dragon.start.x;
			int y = dragon.start.y;
			plane[x][y] = true;
			updateMax(x,y);

			for(int dir: dragon.curve) {
				x += xMove[dir];
				y += yMove[dir];
				plane[x][y] = true;
				updateMax(x,y);
			}
		}
	}

	private static void getNextGen(Dragon dragon) {
		List<Integer> cur = dragon.curve;
		int size = cur.size();
		int[] next = new int[size];
		for(int i=0; i<size; i++) {
			int curDir = cur.get(size-1-i);
			next[i] = curDir == 3 ? 0 : curDir+1;
		}
		for(int i=0; i<size; i++) {
			cur.add(next[i]);
		}
	}

	private static void updateMax(int x, int y) {
		maxX = Math.max(x, maxX);
		maxY = Math.max(y, maxY);
	}
}