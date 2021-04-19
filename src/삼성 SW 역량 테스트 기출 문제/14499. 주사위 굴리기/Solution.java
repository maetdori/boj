import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int R;
	static int C;
	static Pos cur;
	static int[][] map;
	static int[][] dice;
	static int[] command;
	static final Pos TOP = new Pos(1,1);
	static final Pos BOTTOM = new Pos(3,1);
	static int[] nextR = new int[] {0, 0, 0, -1, 1};
	static int[] nextC = new int[] {0, 1, -1, 0, 0};
	static Pos[][] move = new Pos[5][4];
	static {
		move[1] = new Pos[] {new Pos(3, 1), new Pos(1,0), new Pos(1, 1), new Pos(1, 2)};
		move[2] = new Pos[] {new Pos(3, 1), new Pos(1,2), new Pos(1, 1), new Pos(1, 0)};
		move[3] = new Pos[] {new Pos(3, 1), new Pos(2,1), new Pos(1, 1), new Pos(0, 1)};
		move[4] = new Pos[] {new Pos(0, 1), new Pos(1,1), new Pos(2, 1), new Pos(3, 1)};
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		dice = new int[4][3];
		cur = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		command = new int[Integer.parseInt(st.nextToken())];

		for(int r=0; r<R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<command.length; i++) {
			command[i] = Integer.parseInt(st.nextToken());
		}

		for(int i=0; i<command.length; i++)
			moveDice(command[i]);
	}

	private static void moveDice(int dir) {
		int nr = cur.r + nextR[dir];
		int nc = cur.c + nextC[dir];

		//바깥으로 이동시키려는 경우 해당 명령 무시
		if(nr<0 || nc<0 || nr>=R || nc>= C) return;

		cur.r = nr;
		cur.c = nc;

		rollTheDice(move[dir]);

		updateBottomNumber(nr,nc);

		System.out.println(dice[TOP.r][TOP.c]);
	}

	private static void rollTheDice(Pos[] diceMove) {
		int[] tmp = new int[4];

		tmp[0] = dice[diceMove[3].r][diceMove[3].c];
		for(int i=0; i<3; i++) {
			tmp[i+1] = dice[diceMove[i].r][diceMove[i].c];
		}

		for(int i=0; i<4; i++) {
			dice[diceMove[i].r][diceMove[i].c] = tmp[i];
		}
	}

	private static void updateBottomNumber(int nr, int nc) {
		if(map[nr][nc] == 0)
			map[nr][nc] = dice[BOTTOM.r][BOTTOM.c];
		else {
			dice[BOTTOM.r][BOTTOM.c] = map[nr][nc];
			map[nr][nc] = 0;
		}
	}
}

class Pos {
	int r;
	int c;

	Pos(int r, int c) {
		this.r = r;
		this.c = c;
	}
}
