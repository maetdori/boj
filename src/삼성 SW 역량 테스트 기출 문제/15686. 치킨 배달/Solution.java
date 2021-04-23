import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int N;
	static int M;
	static int closing;
	static int[][] map;
	static List<Integer> closedIdx;
	static List<Pos> store = new ArrayList<>();
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 2)
					store.add(new Pos(r,c));
			}
		}
		closing = store.size() - M;
		closedIdx = new ArrayList<>();

		dfs(0, 0);

		System.out.println(min);
	}

	private static void dfs(int depth, int idx) {
		if(depth == closing) {
			min = Math.min(min, getCityChickenDist());
			return;
		}
		for(int i=idx; i<store.size(); i++) {
			closedIdx.add(i);
			dfs(depth+1, i+1);
			closedIdx.remove(closedIdx.size()-1);
		}
	}

	private static int getCityChickenDist() {
		int sum = 0;
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(map[r][c] == 1)
					sum += getChickenDist(r,c);
			}
		}
		return sum;
	}

	private static int getChickenDist(int r, int c) {
		int minDist = Integer.MAX_VALUE;

		for(int i=0; i<store.size(); i++) {
			if(closedIdx.contains(i)) continue;
			Pos thisStore = store.get(i);
			int dist = Math.abs(thisStore.r-r) + Math.abs(thisStore.c-c);
			minDist = Math.min(minDist, dist);
		}

		return minDist;
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