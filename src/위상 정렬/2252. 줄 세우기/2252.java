import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int N;
	static int M;
	static List<Integer>[] list;
	static int[] indegree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()) + 1;
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList[N];
		indegree = new int[N];

		for(int i=1; i<N; i++) {
			list[i] = new ArrayList<>();
		}

		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			list[node1].add(node2);
			indegree[node2]++;
		}

		System.out.println(sorting());
	}

	private static String sorting() {
		StringBuilder sb = new StringBuilder();
		Queue<Integer> queue = new LinkedList<>();

		for(int i=1; i<N; i++) {
			if (indegree[i] > 0) continue;
			queue.offer(i);
		}

		while(!queue.isEmpty()) {
			int cur = queue.poll();
			sb.append(cur + " ");

			for(int next: list[cur]) {
				if(--indegree[next] == 0) {
					queue.offer(next);
				}
			}
		}
		return sb.toString();
	}
}