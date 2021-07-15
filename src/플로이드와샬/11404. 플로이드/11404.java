import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int N;
	static int M;
	static int[][] edge;
	static int INF = 10000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		edge = new int[N+1][N+1];
		for(int i=1; i<N+1; i++) Arrays.fill(edge[i], INF);
		for(int i=1; i<N+1; i++) edge[i][i] = 0;

		StringTokenizer st;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			edge[from][to] = Math.min(edge[from][to], cost);
		}

		floydWarshall();
		print();
	}

	private static void print() {
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<N+1; j++) {
				System.out.print((edge[i][j]==INF ? 0 : edge[i][j]) + " ");
			}System.out.println();
		}
	}

	private static void floydWarshall() {

		for(int i=1; i<N+1; i++) { //경유노드

			for(int j=1; j<N+1; j++) { //출발노드
				if(i==j) continue;

				for(int k=1; k<N+1; k++) { //도착노드
					if(i==k || j==k) continue;

					edge[j][k] = Math.min(edge[j][k], edge[j][i] + edge[i][k]);
				}
			}
		}
	}
}