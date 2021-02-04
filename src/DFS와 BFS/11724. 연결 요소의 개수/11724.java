import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static List<Integer>[] link;
	static int[] group;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());

		link = new ArrayList[v+1];
		group = new int[v+1];

		for(int i=0; i<v+1; i++) {
			link[i] = new ArrayList<>();
		}

		for(int i=0; i<e; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());

			link[v1].add(v2);
			link[v2].add(v1);
		}
		bfs();
	}

	private static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		int cnt = 1;

		for (int i=1; i<link.length; i++) {
			if(group[i] == 0) {
				group[i] = cnt++;
				queue.offer(i);
			}

			while(!queue.isEmpty()) {
				int node = queue.poll();

				for(int neighbor: link[node]) {
					if(group[neighbor] == 0) {
						group[neighbor] = group[node];
						queue.offer(neighbor);
					}
				}
			}
		}
		System.out.println(cnt-1);
	}
}