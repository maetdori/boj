import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int[] gear = new int[4];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for(int i=0; i<4; i++) {
			gear[i] = Integer.parseInt(br.readLine(),2);
		}

		int n = Integer.parseInt(br.readLine());
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken())-1;
			int dir = Integer.parseInt(st.nextToken());
			rotate(idx, dir);
		}

		System.out.println(getScore());
	}

	private static int getScore() {
		int score = 0;

		score += (gear[0] & (1<<7)) == 0 ? 0 : 1;
		score += (gear[1] & (1<<7)) == 0 ? 0 : 2;
		score += (gear[2] & (1<<7)) == 0 ? 0 : 4;
		score += (gear[3] & (1<<7)) == 0 ? 0 : 8;

		return score;
	}

	private static void rotate(int idx, int dir) {
		int[] direction = new int[4]; //-1:반시계, 0:회전X, 1:시계
		direction[idx] = dir;

		for(int i=idx; i>0; i--) {
			if(isSame(i-1, i)) break;
			direction[i-1] = -direction[i];
		}

		for(int i=idx; i<3; i++) {
			if(isSame(i, i+1)) break;
			direction[i+1] = -direction[i];
		}

		turnGearAltogether(direction);
	}

	private static void turnGearAltogether(int[] direction) {
		for(int i=0; i<4; i++) {
			if(direction[i] == 1) {
				turnInClockwise(i);
			}
			if(direction[i] == -1) {
				turnInCounterClockwise(i);
			}
		}
	}

	private static void turnInClockwise(int idx) {
		if((gear[idx] & 1) > 0) { //최하위 비트가 1이면
			gear[idx] >>>= 1;     //오른쪽으로 한 칸 밀고
			gear[idx] |= (1<<7);  //최상위 비트를 1로 채워준다.
		}
		else gear[idx] >>>= 1;
	}

	private static void turnInCounterClockwise(int idx) {
		if((gear[idx] & (1<<7)) > 0) { //최상위 비트가 1이면
			gear[idx] <<= 1;           //왼쪽으로 한 칸 밀고
			gear[idx] |= 1;            //최하위 비트를 1로 채워준다.
		}
		else gear[idx] <<= 1;
	}

	private static boolean isSame(int i, int j) {
		int pole1 = (gear[i] & (1 << 5)) > 0 ? 1 : 0;
		int pole2 = (gear[j] & (1 << 1)) > 0 ? 1 : 0;

		return pole1 == pole2;
	}
}