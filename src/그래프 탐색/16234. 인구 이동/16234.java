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
	static int N;
	static int L;
	static int R;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int cnt = 0;
		while(open())
			cnt++;

		System.out.println(cnt);
	}

	private static boolean open() {
		Queue<Node> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		int[] rMove = new int[]{-1,1,0,0};
		int[] cMove = new int[]{0,0,-1,1};
		boolean flag = false;

		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				List<Node> country = new ArrayList<>();
				int sum = 0;

				if(!visited[r][c]) {
					visited[r][c] = true;
					queue.offer(new Node(r,c));

					while(!queue.isEmpty()) {
						Node node = queue.poll();
						sum += map[node.r][node.c];
						country.add(new Node(node.r,node.c));

						for(int i=0; i<4; i++) {
							int nr = node.r + rMove[i];
							int nc = node.c + cMove[i];

							if(nr<0 || nc<0 || nr>=N || nc>=N)
								continue;

							int diff = Math.abs(map[node.r][node.c] - map[nr][nc]);

							if(!visited[nr][nc] && L <= diff && diff <= R) {
								visited[nr][nc] = true;
								queue.offer(new Node(nr,nc));
								flag = true;
							}
						}
					}

					int average = sum / country.size();
					for(Node node: country) {
						map[node.r][node.c] = average;
					}
				}
			}
		}
		return flag;
	}
}