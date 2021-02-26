import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int N;
	static int M;
	static List<Integer> list[];
	static int[] plan;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		list = new ArrayList[N];
		plan = new int[M];

		for(int i=0; i<N; i++)
			list[i] = new ArrayList<>();

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				if(Integer.parseInt(st.nextToken()) == 1)
					list[i].add(j);
			}
		}

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++)
			plan[i] = Integer.parseInt(st.nextToken()) - 1;

		if(validate())
			System.out.println("YES");
		else
			System.out.println("NO");
	}

	public static boolean validate() {
		for(int i=0; i<M-1; i++) {
			int node1 = plan[i];
			int node2 = plan[i+1];

			if(!connected(node1, node2))
				return false;
		}
		return true;
	}

	public static boolean connected(int node1, int node2) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N];

		queue.offer(node1);

		while(!queue.isEmpty()) {
			int node = queue.poll();
			visited[node] = true;

			if(node == node2)
				return true;

			for(int n: list[node])
				if(!visited[n]) queue.offer(n);
		}
		return false;
	}
}