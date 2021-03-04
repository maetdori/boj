import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int n;
	static int cnt;
	static int[] arr;
	static boolean[] visited;
	static boolean[] finished;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());

		for(int i=0; i<tc; i++) {
			n = Integer.parseInt(br.readLine());
			arr = new int[n+1];
			visited = new boolean[n+1];
			finished = new boolean[n+1];
			cnt = 0;

			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=n; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}

			for(int j=1; j<=n; j++) {
				dfs(j);
			}
			System.out.println(n-cnt);
		}
	}

	private static void dfs(int now) {
		if(visited[now])
			return;

		visited[now] = true;
		int next = arr[now];

		if(!visited[next])
			dfs(next);

		else {
			if(!finished[next]) {
				cnt++;
				for(int i=next; i!=now; i = arr[i])
					cnt++;
			}
		}
		finished[now] = true;
	}
}