import java.awt.Point;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int start;
	static int target;
	static int[] count = new int[200001];
	static boolean[] visited = new boolean[200001];
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		start = Integer.parseInt(st.nextToken());
		target = Integer.parseInt(st.nextToken());

		bfs();
		System.out.println(min);
	}

	private static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(start,0));

		while(!queue.isEmpty()) {
			Point point = queue.poll();
			visited[point.x] = true;

			if(point.x == target)
				min = Math.min(point.y, min);

			if(0 <= point.x-1 && !visited[point.x-1])
				queue.offer(new Point(point.x-1, point.y+1));

			if(point.x+1 <= 100000 && !visited[point.x+1])
				queue.offer(new Point(point.x+1, point.y+1));

			if(point.x*2 <= 100000 && !visited[point.x*2])
				queue.offer(new Point(point.x*2, point.y));
		}
	}
}