import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Node {
	int val;
	int weight;

	Node(int val, int weight) {
		this.val = val;
		this.weight = weight;
	}
}

class Main {
	static int N;
	static List<Node>[] edges;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		edges = new ArrayList[N+1];

		for(int i=1; i<=N; i++) edges[i] = new ArrayList<>();
		for(int i=0; i<N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			if(!st.hasMoreTokens()) break;
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges[n1].add(new Node(n2, w));
			edges[n2].add(new Node(n1, w));
		}

		Node endPoint1 = getFarthest(1,0);
		Node endPoint2 = getFarthest(endPoint1.val, 0);
		int dist1 = getFarthest(endPoint1.val, endPoint2.val).weight;
		int dist2 = getFarthest(endPoint2.val, endPoint1.val).weight;

		System.out.println(Math.max(dist1, dist2));

	}

	private static Node getFarthest(int start, int except) {
		Queue<Node> queue = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		Node farthest = new Node(start, 0);

		queue.offer(farthest);

		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			visited[cur.val] = true;

			if(cur.weight > farthest.weight && cur.val != except)
				farthest = cur;

			for(Node next: edges[cur.val]) {
				if(visited[next.val]) continue;
				visited[next.val] = true;
				queue.offer(new Node(next.val, cur.weight + next.weight));
			}
		}
		return farthest;
	}
}