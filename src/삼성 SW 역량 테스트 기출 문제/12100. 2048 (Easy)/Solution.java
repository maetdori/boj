import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int N;
	static int[][] map;
	static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for(int r=0; r<N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0);
		System.out.println(max);
	}

	private static void dfs(int depth) {
		if(depth == 5) {
			findMax();
			return;
		}

		int[][] tmpMap = new int[N][N];
		copy(tmpMap, map); //현재 단계에서의 map 상태를 tmpMap에 저장해둔다.

		for(int i=0; i<4; i++) {
			move(i);
			dfs(depth+1);
			copy(map, tmpMap); //dfs를 빠져나온 후 이전의 map 상태로 다시 되돌린다.
		}
	}

	private static int[][] move(int i) {
		switch(i) {
			case 0:
				moveUpward();
				break;
			case 1:
				moveDownward();
				break;
			case 2:
				moveLeftward();
				break;
			case 3:
				moveRightward();
				break;
		}
		return map;
	}

	private static void operateStack(int num, Stack<int[]> stack) {
		//스택 맨 위 원소가 num과 같고, 아직 합쳐지지 않은 블럭이라면
		if(!stack.isEmpty() && stack.peek()[0] == num && stack.peek()[1] == 0) {
			stack.pop();
			stack.push(new int[] {num*2, 1});
			return;
		}
		stack.push(new int[] {num, 0});
	}

	private static void moveUpward() {
		for(int c=0; c<N; c++) {
			Stack<int[]> stack = new Stack<>(); //int[2] {숫자, 합쳐진 블럭인지 표시(0,1)}
			for(int r=0; r<N; r++) {
				if(map[r][c] == 0) continue;
				operateStack(map[r][c], stack);
			}

			for(int i=0; i<stack.size(); i++) {
				map[i][c] = stack.get(i)[0];
			}
			for(int i=stack.size(); i<N; i++) {
				map[i][c] = 0;
			}
		}
	}

	private static void moveDownward() {
		for(int c=0; c<N; c++) {
			Stack<int[]> stack = new Stack<>();
			for(int r=N-1; r>=0; r--) {
				if(map[r][c] == 0) continue;
				operateStack(map[r][c], stack);
			}

			for(int i=0; i<stack.size(); i++) {
				map[N-1-i][c] = stack.get(i)[0];
			}
			for(int i=0; i<N-stack.size(); i++) {
				map[i][c] = 0;
			}
		}
	}

	private static void moveLeftward() {
		for(int r=0; r<N; r++) {
			Stack<int[]> stack = new Stack<>();
			for (int c=0; c<N; c++) {
				if (map[r][c] == 0) continue;
				operateStack(map[r][c], stack);
			}

			for (int i=0; i<stack.size(); i++) {
				map[r][i] = stack.get(i)[0];
			}
			for (int i=stack.size(); i<N; i++) {
				map[r][i] = 0;
			}
		}
	}

	private static void moveRightward() {
		for(int r=0; r<N; r++) {
			Stack<int[]> stack = new Stack<>();
			for(int c=N-1; c>=0; c--) {
				if(map[r][c] == 0) continue;
				operateStack(map[r][c], stack);
			}

			for(int i=0; i<stack.size(); i++) {
				map[r][N-1-i] = stack.get(i)[0];
			}
			for(int i=0; i<N-stack.size(); i++) {
				map[r][i] = 0;
			}
		}
	}

	private static void findMax() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				max = Math.max(max, map[r][c]);
			}
		}
	}

	private static void copy(int[][] arr1, int[][] arr2) {
		for(int i=0; i<N; i++) {
			arr1[i] = arr2[i].clone();
		}
	}
}