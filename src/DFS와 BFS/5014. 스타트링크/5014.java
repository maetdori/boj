import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int top;
	static int now;
	static int office;
	static int up;
	static int down;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		top = Integer.parseInt(st.nextToken());
		now = Integer.parseInt(st.nextToken());
		office = Integer.parseInt(st.nextToken());
		up = Integer.parseInt(st.nextToken());
		down = Integer.parseInt(st.nextToken());

		int result = bfs();
		if(result<0)
			System.out.println("use the stairs");
		else System.out.println(result);
	}

	private static int bfs() {
		Queue<Integer> queue = new LinkedList<>();
		int[] visited = new int[top+1];

		queue.offer(now);
		visited[now] = 1;

		while(!queue.isEmpty()) {
			int n = queue.poll();

			int upward = n + up;
			int downward = n - down;

			if(1 <= upward && upward <= top && visited[upward]==0) {
				visited[upward] = visited[n] + 1;
				queue.offer(upward);
			}

			if(1 <= downward && downward <= top && visited[downward]==0) {
				visited[downward] = visited[n] + 1;
				queue.offer(downward);
			}
		}
		return visited[office]-1;
	}
}