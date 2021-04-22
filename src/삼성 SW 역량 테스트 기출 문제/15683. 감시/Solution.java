import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Pos {
	int r;
	int c;

	Pos(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

class Main {
	static int R;
	static int C;
	static int[][] map;
	static int[] caseNum = new int[] {0,4,2,4,4,1};
	static List<Pos> cam = new ArrayList<>();
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];

		for(int r=0; r<R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] > 0 && map[r][c] < 6)
					cam.add(new Pos(r,c));
			}
		}
		dfs(0);
		System.out.println(min);
	}

	private static void dfs(int depth) {
		if(depth == cam.size()) {
			min = Math.min(min, countBlindSpot());
			return;
		}

		Pos cur = cam.get(depth);
		int camType = map[cur.r][cur.c];

		int[][] tmpMap = new int[R][C];
		copy(tmpMap, map);

		for(int i=0; i<caseNum[camType]; i++) {
			monitor(cur, i);
			dfs(depth+1);
			copy(map, tmpMap);
		}
	}

	private static void copy(int[][] arr1, int[][] arr2) {
		for(int r=0; r<R; r++) {
			arr1[r] = arr2[r].clone();
		}
	}

	private static int countBlindSpot() {
		int cnt = 0;
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				if(map[r][c] == 0) cnt++;
			}
		}
		return cnt;
	}

	private static void monitor(Pos cam, int dir) {
		int camType = map[cam.r][cam.c];
		switch(camType) {
			case 1:
				camNumOne(cam, dir);
				break;
			case 2:
				camNumTwo(cam, dir);
				break;
			case 3:
				camNumThree(cam, dir);
				break;
			case 4:
				camNumFour(cam, dir);
				break;
			case 5:
				camNumFive(cam);
		}
	}

	private static void camNumOne(Pos cam, int dir) {
		switch(dir) {
			case 0: //상
				goUpward(cam);
				break;
			case 1: //하
				goDownward(cam);
				break;
			case 2: //좌
				goLeftward(cam);
				break;
			case 3: //우
				goRightward(cam);
				break;
		}
	}

	private static void camNumTwo(Pos cam, int dir) {
		switch(dir) {
			case 0: //상하
				goUpward(cam);
				goDownward(cam);
				break;
			case 1: //좌우
				goLeftward(cam);
				goRightward(cam);
				break;
		}
	}

	private static void camNumThree(Pos cam, int dir) {
		switch(dir) {
			case 0: //북동
				goUpward(cam);
				goRightward(cam);
				break;
			case 1: //동남
				goRightward(cam);
				goDownward(cam);
				break;
			case 2: //남서
				goDownward(cam);
				goLeftward(cam);
				break;
			case 3: //북서
				goLeftward(cam);
				goUpward(cam);
				break;
		}
	}

	private static void camNumFour(Pos cam, int dir) {
		switch(dir) {
			case 0: //ㅗ
				goLeftward(cam);
				goUpward(cam);
				goRightward(cam);
				break;
			case 1: //ㅏ
				goUpward(cam);
				goRightward(cam);
				goDownward(cam);
				break;
			case 2: //ㅜ
				goLeftward(cam);
				goDownward(cam);
				goRightward(cam);
				break;
			case 3: //ㅓ
				goDownward(cam);
				goLeftward(cam);
				goUpward(cam);
				break;
		}
	}
	private static void camNumFive(Pos cam) {
		goUpward(cam);
		goDownward(cam);
		goLeftward(cam);
		goRightward(cam);
	}

	private static void goUpward(Pos cam) {
		for(int r=cam.r-1; r>=0; r--) {
			if(map[r][cam.c] == 6) break;
			if(map[r][cam.c] == 0)
				map[r][cam.c] = -1;
		}
	}

	private static void goDownward(Pos cam) {
		for(int r=cam.r+1; r<R; r++) {
			if(map[r][cam.c] == 6) break;
			if(map[r][cam.c] == 0)
				map[r][cam.c] = -1;
		}
	}

	private static void goLeftward(Pos cam) {
		for(int c=cam.c-1; c>=0; c--) {
			if(map[cam.r][c] == 6) break;
			if(map[cam.r][c] == 0)
				map[cam.r][c] = -1;
		}
	}

	private static void goRightward(Pos cam) {
		for(int c=cam.c+1; c<C; c++) {
			if(map[cam.r][c] == 6) break;
			if(map[cam.r][c] == 0)
				map[cam.r][c] = -1;
		}
	}
}