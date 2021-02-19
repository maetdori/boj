import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Node {
	public int val;
	public int cost;

	Node(int val, int cost) {
		this.val = val;
		this.cost = cost;
	}
}

class Main {
	static int v;
	static List<Node> listArr[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		v = Integer.parseInt(br.readLine());
		listArr = new ArrayList[v+1];

		for(int i=1; i<v+1; i++)
			listArr[i] = new ArrayList<>();

		for(int i=0; i<v; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int vertex = Integer.parseInt(st.nextToken());
			int next;
			while((next = Integer.parseInt(st.nextToken())) != -1) {
				int weight = Integer.parseInt(st.nextToken());
				listArr[vertex].add(new Node(next, weight));
			}
		}
		Node farthest = getFarthest(1);
		System.out.println(getFarthest(farthest.val).cost);
	}

	private static Node getFarthest(int start) {
		Queue<Node> queue = new LinkedList<>();
		boolean[] visited = new boolean[v+1];
		Node farthestNode = new Node(0,0);

		queue.offer(new Node(start, 0));
		visited[start] = true;

		while(!queue.isEmpty()) {
			Node now = queue.poll();
			farthestNode = now.cost > farthestNode.cost ? now : farthestNode;

			for(Node node: listArr[now.val]) {
				if(!visited[node.val]) {
					visited[node.val] = true;
					queue.offer(new Node(node.val, node.cost + now.cost));
				}
			}
		}
		return farthestNode;
	}
}