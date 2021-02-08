import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Fish implements Comparable<Fish> {
	public int r;
	public int c;
	public int dist;

	Fish(int r, int c, int dist) {
		this.r = r;
		this.c = c;
		this.dist = dist;
	}

	@Override
	public int compareTo(Fish fish) {
		if(this.dist == fish.dist) {
			if(this.r == fish.r) {
				return this.c - fish.c;
			}
			return this.r - fish.r;
		}
		return this.dist - fish.dist;
	}
}

class Shark {
	public int r;
	public int c;
	public int size;
	public int exp;
	public int move;

	Shark(int r, int c, int size, int exp, int move) {
		this.r = r;
		this.c = c;
		this.size = size;
		this.exp = exp;
		this.move = move;
	}

	Shark(){

	}
}

class Main {
	static int n;
	static int[][] map;
	static Shark shark;
	static List<Fish> edible;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];

		for(int r=0; r<n; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int c=0; c<n; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 9) {
					map[r][c] = 0;
					shark = new Shark(r, c, 2, 0, 0);
				}
			}
		}

		while(moveShark()) {}
		System.out.println(shark.move);
	}

	private static boolean moveShark() {
		if(!check()) return false;

		Fish next = edible.get(0);

		shark.r = next.r;
		shark.c = next.c;
		shark.exp++;
		shark.move += next.dist;

		if(shark.size == shark.exp) {
			shark.size++;
			shark.exp = 0;
		}

		map[next.r][next.c] = 0;

		return true;
	}

	private static int calcDist(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		int[][] visited = new int[n][n];
		int[] rMove = new int[]{-1,1,0,0};
		int[] cMove = new int[]{0,0,-1,1};

		queue.offer(new int[]{shark.r, shark.c});
		visited[shark.r][shark.c] = 1;

		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			int ro = tmp[0];
			int co = tmp[1];

			for(int i=0; i<4; i++) {
				int nr = ro + rMove[i];
				int nc = co + cMove[i];

				if(nr<0 || nc<0 || nr>=n || nc>=n)
					continue;

				if(visited[nr][nc]==0 && map[nr][nc]<=shark.size) {
					queue.offer(new int[]{nr,nc});
					visited[nr][nc] = visited[ro][co] + 1;
				}
			}
		}
		return visited[r][c]-1;
	}

	private static boolean check() {
		edible = new ArrayList<>();

		for(int r=0; r<n; r++) {
			for(int c=0; c<n; c++) {
				if(0 < map[r][c] && map[r][c] < shark.size) {
					int dist = calcDist(r,c);
					if(dist > 0) //이 부분 반드시 체크해야함 ! 
						edible.add(new Fish(r, c, dist));
				}
			}
		}

		if(edible.isEmpty())
			return false;

		Collections.sort(edible);
		return true;
	}
}