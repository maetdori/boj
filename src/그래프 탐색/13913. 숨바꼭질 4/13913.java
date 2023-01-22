import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int subin;
	static int sister;
	static int[] count;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		subin = Integer.parseInt(st.nextToken());
		sister = Integer.parseInt(st.nextToken());
		count = new int[100001];
		parent = new int[100001];

		bfs();

		int cnt= count[sister]-1;
		int[] path = new int[cnt+1];
		int node = sister;
		for(int i=cnt-1; i>=0; i--) {
			path[i] = parent[node];
			node = parent[node];
		}
		path[cnt] = sister;

		System.out.println(cnt);
		for(int i: path)
			System.out.print(i + " ");
	}

	private static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(subin);
		count[subin] = 1;

		while(!queue.isEmpty()) {
			int now = queue.poll();

			if(now == sister)
				return;

			if(now*2 <= sister+1 && count[now*2] == 0) {
				queue.offer(now*2);
				count[now*2] = count[now]+1;
				parent[now*2] = now;
			}

			if(now-1 >= 0 && count[now-1] == 0) {
				queue.offer(now-1);
				count[now-1] = count[now]+1;
				parent[now-1] = now;
			}

			if(now+1 <= sister && count[now+1] == 0) {
				queue.offer(now+1);
				count[now+1] = count[now]+1;
				parent[now+1] = now;
			}
		}
	}
}