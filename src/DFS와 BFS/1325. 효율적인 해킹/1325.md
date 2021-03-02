import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int N;
	static int M;
	static List<Integer>[] list;
	static int[] hack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList[N+1];
		hack = new int[N+1];

		for(int i=0; i<=N; i++)
			list[i] = new ArrayList<>();

		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			list[node2].add(node1);
		}

		for(int i=1; i<=N; i++)
			hacking(i);

		int max = 0;
		for(int i=1; i<=N; i++)
			max = Math.max(max, hack[i]);

		for(int i=1; i<=N; i++) {
			if(max == hack[i])
				System.out.print(i + " ");
		}
	}

	private static void hacking(int n) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		int cnt = 0;

		queue.offer(n);
		visited[n] = true;

		while(!queue.isEmpty()) {
			int now = queue.poll();
			cnt++;

			for(int i: list[now]) {
				if(visited[i]) continue;

				queue.offer(i);
				visited[i] = true;
			}
		}

		hack[n] = cnt;
	}
}