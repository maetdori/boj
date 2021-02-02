import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int len;
	static boolean[][] visited;
	static int[] rMove = new int[]{-2,-2,-1,-1,1,1,2,2};
	static int[] cMove = new int[]{-1,1,-2,2,-2,2,-1,1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int testcases = Integer.parseInt(st.nextToken());

		for(int i=0; i<testcases; i++) {
			len = Integer.parseInt(br.readLine());
			visited = new boolean[len][len];

			int[] now = new int[2];
			st = new StringTokenizer(br.readLine());
			now[0] = Integer.parseInt(st.nextToken());
			now[1] = Integer.parseInt(st.nextToken());

			int[] dest = new int[2];
			st = new StringTokenizer(br.readLine());
			dest[0] = Integer.parseInt(st.nextToken());
			dest[1] = Integer.parseInt(st.nextToken());

			bfs(now[0], now[1], dest[0], dest[1]);

		}
	}

	private static void bfs(int nowR, int nowC, int destR, int destC) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[]{nowR, nowC, 0});
		visited[nowR][nowC] = true;

		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();

			if(tmp[0] == destR && tmp[1] == destC) {
				System.out.println(tmp[2]);
				return;
			}

			for(int i=0; i<8; i++) {
				int nr = tmp[0] + rMove[i];
				int nc = tmp[1] + cMove[i];
				int cnt = tmp[2];

				if(nr<0 || nc<0 || nr>=len || nc>=len)
					continue;

				if(!visited[nr][nc]) {
					visited[nr][nc] = true;
					queue.offer(new int[]{nr, nc, cnt+1});
				}
			}
		}
	}
}