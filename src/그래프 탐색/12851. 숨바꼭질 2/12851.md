import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int start;
	static int target;
	static int min = Integer.MAX_VALUE;
	static int route = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		start = Integer.parseInt(st.nextToken());
		target = Integer.parseInt(st.nextToken());

		if(start >= target) {
			System.out.println(start-target);
			System.out.println(1);
			return;
		}

		bfs();
		System.out.println(min);
		System.out.println(route);
	}

	private static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		int[] visited = new int[100001];

		queue.offer(start);
		visited[start] = 1;

		while(!queue.isEmpty()) {
			int now = queue.poll();

			if(min < visited[now]) return;

			int[] move = new int[]{now-1, now+1, now*2};
			for(int i=0; i<3; i++) {
				int next = move[i];

				if(next < 0 || next > 100000) continue;

				if(next == target) {
					min = visited[now];
					route++;
				}

				if(visited[next] == 0 || visited[next] == visited[now]+1) {
					queue.offer(next);
					visited[next] = visited[now] + 1;
				}
			}
		}
	}
}
