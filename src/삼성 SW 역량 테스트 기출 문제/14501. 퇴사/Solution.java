import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Counsel {
	int end;
	int pay;

	Counsel(int end, int pay) {
		this.end = end;
		this.pay = pay;
	}
}

class Main {
	static int N;
	static Counsel[] counsels;
	static boolean[] visited;
	static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		counsels = new Counsel[N];
		visited = new boolean[N];

		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int end = i-1 + Integer.parseInt(st.nextToken());
			int pay = Integer.parseInt(st.nextToken());
			counsels[i] = new Counsel(end, pay);
		}

		dfs(0,0);
		System.out.println(max);
	}

	private static void dfs(int start, int pay) {
		for(int i=start; i<N; i++) {
			if(visited[i]) continue;
			if(counsels[i].end >= N) continue;
			visited[i] = true;
			dfs(counsels[i].end+1, pay+counsels[i].pay);
			visited[i] = false;
		}
		max = Math.max(max, pay);
	}
}