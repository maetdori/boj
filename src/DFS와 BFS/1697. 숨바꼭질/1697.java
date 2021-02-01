import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int subin = Integer.parseInt(st.nextToken());
		int sister = Integer.parseInt(st.nextToken());

		if(subin==sister) {
			System.out.println(0);
			return;
		}
		bfs(subin, sister);
	}

	private static void bfs(int subin, int dest) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[] visited = new boolean[100001];

		queue.offer(new int[]{subin, 0}); //현재위치, 시간
		visited[subin] = true;

		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			int now = tmp[0];
			int cnt = tmp[1];

			for(int i=0; i<3; i++) {
				int next = 0;
				if(i==0)
					next = now-1;
				else if(i==1)
					next = now+1;
				else next = now*2;

				if(next == dest) {
					System.out.println(cnt+1);
					return;
				}

				if(next>0 && next<visited.length && !visited[next]) {
					visited[next] = true;
					queue.offer(new int[]{next, cnt+1});
				}
			}
		}
	}
}