import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Dir {
	int when;
	char dir;

	Dir(int when, char dir) {
		this.when = when;
		this.dir = dir;
	}
}

class Pos {
	int r;
	int c;

	Pos(int r, int c) {
		this.c = c;
		this.r = r;
	}
}

class Main {
	static int N;
	static int[][] map;
	static Queue<Dir> changeDir = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		int apple = Integer.parseInt(br.readLine());
		for(int a=0; a<apple; a++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			map[r][c] = 1; //apple
		}

		int dirInfo = Integer.parseInt(br.readLine());
		for(int d=0; d<dirInfo; d++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int when = Integer.parseInt(st.nextToken());
			char dir = st.nextToken().charAt(0);
			changeDir.add(new Dir(when, dir));
		}

		System.out.println(dummy());
	}

	private static int dummy() {
		Pos[] dirArr = new Pos[]{new Pos(-1,0), new Pos(0,1), new Pos(1,0), new Pos(0,-1)};
		int curDirIdx = 1;
		Pos curDir = dirArr[curDirIdx]; //이동방향

		List<Pos> snake = new ArrayList<>();
		snake.add(new Pos(0,0));

		int runningTime = 0;
		map[0][0] = 2; //snake

		while(true) {
			runningTime++;

			Pos curHead = snake.get(snake.size()-1);
			Pos curTail = snake.get(0);

			int nr = curHead.r + curDir.r;
			int nc = curHead.c + curDir.c;

			if(!isValid(nr,nc)) return runningTime;

			snake.add(new Pos(nr,nc));

			if(map[nr][nc] != 1) {
				map[curTail.r][curTail.c] = 0;
				snake.remove(0);
			}
			map[nr][nc] = 2;

			if(!changeDir.isEmpty() && runningTime == changeDir.peek().when) {
				Dir nextDir = changeDir.poll();
				curDirIdx = findNextDirIdx(curDirIdx, nextDir.dir);
				curDir = dirArr[curDirIdx];
			}
		}
	}

	private static int findNextDirIdx(int curDirIdx, char dir) {
		if(dir == 'L')
			return curDirIdx > 0 ? curDirIdx-1 : 3;
		else
			return curDirIdx < 3 ? curDirIdx+1 : 0;
	}

	private static boolean isValid(int r, int c) {
		//벽에 부딪힐 경우
		if(r<0 || c<0 || r>=N || c>= N) return false;
		//자기자신의 몸과 부딪힐 경우
		if(map[r][c] == 2) return false;
		return true;
	}
}