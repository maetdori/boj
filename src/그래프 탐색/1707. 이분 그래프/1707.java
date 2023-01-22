import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int testcase = Integer.parseInt(st.nextToken());

		for(int i=0; i<testcase; i++) {
			st = new StringTokenizer(br.readLine());
			int vertexNum = Integer.parseInt(st.nextToken());
			int edgeNum = Integer.parseInt(st.nextToken());

			List<Integer>[] vertex = new ArrayList[vertexNum+1];
			int[] color = new int[vertexNum+1];

			for(int j=1; j<=vertexNum; j++) {
				vertex[j] = new ArrayList<>();
			}

			for(int j=0; j<edgeNum; j++) {
				st = new StringTokenizer(br.readLine());
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());

				vertex[n1].add(n2);
				vertex[n2].add(n1);
			}

			bipartite(vertex, color);
		}
	}

	private static void bipartite(List<Integer>[] vertex, int[] color) {
		Queue<Integer> queue = new LinkedList<>();

		for(int i=1; i<vertex.length; i++) {
			if (color[i] == 0) {
				queue.offer(i);
				color[i] = 1;
			}

			while (!queue.isEmpty()) {
				int tmp = queue.poll();

				for (int adj : vertex[tmp]) {
					if (color[adj] == 0) {
						color[adj] = -color[tmp];
						queue.offer(adj);
					} else if (color[adj] == color[tmp]) {
						System.out.println("NO");
						return;
					}
				}
			}
		}
		System.out.println("YES");
	}
}