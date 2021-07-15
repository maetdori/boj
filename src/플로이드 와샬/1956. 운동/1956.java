import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int V;
	static int E;
	static int[][] edge;
	static int INF = 10000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		edge = new int[V+1][V+1];
		for(int i=1; i<V+1; i++) Arrays.fill(edge[i], INF);
		for(int i=1; i<V+1; i++) edge[i][i] = 0;

		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			edge[from][to] = cost;
		}

		System.out.println(getMinimalCycle());
	}

	private static int getMinimalCycle() {
		floydWarshall();
		int min = INF;

		for(int i=1; i<V+1; i++) {
			for(int j=1; j<V+1; j++) {
				if(i==j) continue;
				min = Math.min(min, edge[i][j] + edge[j][i]);
			}
		}

		return min==INF ? -1 : min;
	}

	private static void floydWarshall() {
		for(int k=1; k<V+1; k++) { //경유노드
			for(int i=1; i<V+1; i++) { //출발노드
				if(i==k) continue;
 				for(int j=1; j<V+1; j++) { //도착노드
					if(i==j || k==j) continue;
					edge[i][j] = Math.min(edge[i][j], edge[i][k] + edge[k][j]);
				}
			}
		}
	}
}