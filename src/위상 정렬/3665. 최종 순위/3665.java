import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int N;
	static int[] indegree;
	static boolean[][] edges;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		for(int t=0; t<TC; t++) {
			N = Integer.parseInt(br.readLine());
			indegree = new int[N+1];
			edges = new boolean[N+1][N+1];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				int num = Integer.parseInt(st.nextToken());
				indegree[num] = i;

				for(int j=1; j<=N; j++) {
					if(j!=num && !edges[j][num])
						edges[num][j] = true;
				}
			}

			int m = Integer.parseInt(br.readLine());
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());

				swap(n1,n2);
			}
			System.out.println(topologicalSort());
		}
	}

	private static String topologicalSort() {
		Queue<Integer> queue = new LinkedList<>();
		StringBuilder sb = new StringBuilder();

		for(int i=1; i<=N; i++) {
			//indegree가 0개인 정점을 찾는다.
			if(indegree[i] == 0)
				queue.offer(i);
		}

		//정점의 개수만큼 반복한다.
		for(int i=1; i<=N; i++) {
			//진행과정에서 사이클이 형성된 경우
			if(queue.size() == 0) return "IMPOSSIBLE";

			/* Queue에 저장된 정점이 2개 이상이면 그들간의 우선순위를 알 수 없다.
			 * 동시에 여러개의 원소가 진입한 상태이다.
			 * 일반적인 위상정렬이라면 어떤 것이든 상관없지만 이 문제는 그렇지 않다. */
			if(queue.size() > 1) return "?";

			int cur = queue.poll();
			sb.append(cur + " ");

			for(int j=1; j<=N; j++) {
				if(edges[cur][j]) {
					edges[cur][j] = false;
					if(--indegree[j] == 0) queue.offer(j);
				}
			}
		}
		return sb.toString();
	}

	private static void swap(int n1, int n2) {
		if(!edges[n1][n2]) {
			edges[n1][n2] = true;
			edges[n2][n1] = false;
			indegree[n1]--;
			indegree[n2]++;
		}
		else {
			edges[n1][n2] = false;
			edges[n2][n1] = true;
			indegree[n1]++;
			indegree[n2]--;
		}
	}
}