import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Node {
	int less;
	int more;
}

class Main {
	static int V;
	static int E;
	static List<Integer>[] adj;
	static Node[] node;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		adj = new ArrayList[V+1];
		node = new Node[V+1];

		for(int i=0; i<V+1; i++) {
			adj[i] = new ArrayList<>();
			node[i] = new Node();
		}

		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			adj[n1].add(n2);
		}

		nodeUpdate();

		int cnt = 0;
		for(Node student: node) {
			if(student.less + student.more == V-1)
				cnt++;
		}

		System.out.println(cnt);
	}

	public static void nodeUpdate() {
		for(int i=1; i<V+1; i++) {
			Queue<Integer> queue = new LinkedList<>();
			boolean[] visited = new boolean[V+1];

			queue.offer(i);
			visited[i] = true;

			while(!queue.isEmpty()) {
				int now = queue.poll();

				for(int next: adj[now]) {
					if(visited[next]) continue;
					visited[next] = true;
					queue.offer(next);
					node[next].less++;
					node[i].more++;
				}
			}
		}
	}
}