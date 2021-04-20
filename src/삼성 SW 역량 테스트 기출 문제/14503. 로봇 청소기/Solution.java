import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Robo {
	int r;
	int c;
	int dir;

	Robo(int r, int c, int dir) {
		this.r = r;
		this.c = c;
		this.dir = dir;
	}
}

class Main {
	static int R;
	static int C;
	static Robo robot;
	static int[][] map;
	static int[] leftR = {0,-1,0,1};
	static int[] leftC = {-1,0,1,0};
	static int[] backR = {1,0,-1,0};
	static int[] backC = {0,-1,0,1};
	static int count = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];

		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		robot = new Robo(r,c,d);

		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		map[robot.r][robot.c] = 2;
		clean();
		System.out.println(count);
	}

	private static void clean() {
		while(true) {
			if(!canMove()) {
				int br = robot.r + backR[robot.dir];
				int bc = robot.c + backC[robot.dir];
				if (map[br][bc] == 1)
					return;
				robot.r = br;
				robot.c = bc;
				continue;
			}

			int lr = robot.r + leftR[robot.dir];
			int lc = robot.c + leftC[robot.dir];
			robot.dir = robot.dir==0 ? 3 : robot.dir-1;

			if(map[lr][lc] != 0) continue;

			robot.r = lr;
			robot.c = lc;
			map[lr][lc] = 2;
			count++;
		}
	}

	private static boolean canMove() {
		for(int i=0; i<4; i++) {
			int nr = robot.r + leftR[i];
			int nc = robot.c + leftC[i];
			if(map[nr][nc] == 0) return true;
		}
		return false;
	}
}