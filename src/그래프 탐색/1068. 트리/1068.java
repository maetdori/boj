import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Node {
	public int parent;
	public List<Integer> children;

	Node() {
		this.children = new ArrayList<>();
	}

	void removeChild(int c) {
		children.remove(children.indexOf(c));
	}
}

class Main {
	static int N;
	static List<Node> tree;
	static int del;
	static boolean[] deleted;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		tree = new ArrayList<>();
		deleted = new boolean[N];

		for(int i=0; i<N; i++)
			tree.add(new Node());

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int parent = Integer.parseInt(st.nextToken());

			tree.get(i).parent = parent;
			if(parent != -1)
				tree.get(parent).children.add(i);
		}

		del = Integer.parseInt(br.readLine());
		bfs();
		int cnt = 0;
		for(int i=0; i<N; i++) {
			if(!deleted[i] && tree.get(i).children.size()==0)
				cnt++;
		}
		System.out.println(cnt);
	}

	public static void bfs() {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(tree.get(del));
		deleted[del] = true;

		while(!queue.isEmpty()) {
			Node now = queue.poll();
			int parent = now.parent;

			if(parent != -1 && !deleted[parent])
				tree.get(parent).removeChild(tree.indexOf(now));

			for(int i: now.children) {
				queue.offer(tree.get(i));
				deleted[i] = true;
			}
		}
	}
}