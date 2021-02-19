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
	static int n;
	static List<Node> listArr[];
	static boolean[] visited;
	static boolean[] calced;
	static int farthest = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		listArr = new ArrayList[n+1];
		calced = new boolean[n+1];

		for(int i=0; i<n+1; i++)
			listArr[i] = new ArrayList<>();

		String line;
		while((line=br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(line);
			if(st.countTokens() < 3) break;
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			listArr[node1].add(new Node(node2, weight));
			listArr[node2].add(new Node(node1, weight));
		}

		Node farthest = getFarthest(1);
		System.out.println(getFarthest(farthest.val).cost);
	}

	private static Node getFarthest(int start) {
		Queue<Node> queue = new LinkedList<>();
		boolean[] visited = new boolean[n+1];
		Node farthestNode = new Node(0,0);

		queue.offer(new Node(start,0));
		visited[start] = true;

		while(!queue.isEmpty()) {
			Node now = queue.poll();
			farthestNode = now.cost > farthestNode.cost ? now : farthestNode;

			for(Node node: listArr[now.val]) {
				if(!visited[node.val]) {
					visited[node.val] = true;
					queue.offer(new Node(node.val, now.cost+node.cost));
				}
			}
		}
		return farthestNode;
	}
}