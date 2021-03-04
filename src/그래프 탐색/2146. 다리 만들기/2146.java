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
	static int n;
	static int[][] map;
	static int[] rMove = new int[]{-1,1,0,0};
	static int[] cMove = new int[]{0,0,-1,1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		map = new int[n][n];

		for(int r=0; r<n; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int c=0; c<n; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		color();
		System.out.println(connect());
	}

	private static int connect() {
		Queue<Node> queue = new LinkedList<>();
		int min = Integer.MAX_VALUE;

		for(int r=0; r<n; r++) {
			for(int c=0; c<n; c++) {
				if(map[r][c] != 0) {
					int[][] visited = new int[n][n];
					int color = map[r][c];
					queue.offer(new Node(r,c));
					visited[r][c] = 1;

					while(!queue.isEmpty()) {
						Node node = queue.poll();
						int ro = node.r;
						int co = node.c;

						for(int i=0; i<4; i++) {
							int nr = ro + rMove[i];
							int nc = co + cMove[i];

							if(nr<0 || nc<0 || nr>=n || nc>=n)
								continue;

							if(visited[nr][nc] == 0) {
								if(map[nr][nc] == 0) {
									queue.offer(new Node(nr,nc));
									visited[nr][nc] = visited[ro][co] + 1;
								}

								else if(map[nr][nc] != color){
									min = Math.min(min, visited[ro][co]-1);
								}
							}
						}
					}
				}
			}
		}
		return min;
	}

	private static void color() {
		Queue<Node> queue = new LinkedList<>();
		boolean[][] visited = new boolean[n][n];
		int color = 1;

		for(int r=0; r<n; r++) {
			for(int c=0; c<n; c++) {
				if(map[r][c] == 1) {
					queue.offer(new Node(r,c));
					visited[r][c] = true;
					map[r][c] = ++color;

					while(!queue.isEmpty()) {
						Node node = queue.poll();

						for(int i=0; i<4; i++) {
							int nr = node.r + rMove[i];
							int nc = node.c + cMove[i];

							if(nr<0 || nc<0 || nr>=n || nc>=n)
								continue;

							if(!visited[nr][nc] && map[nr][nc]==1) {
								queue.offer(new Node(nr,nc));
								visited[nr][nc] = true;
								map[nr][nc] = color;
							}
						}
					}
				}
			}
		}
	}
}