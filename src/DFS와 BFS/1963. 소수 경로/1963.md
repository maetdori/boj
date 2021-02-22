import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int start;
	static int target;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());

		for(int i=0; i<tc; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			target = Integer.parseInt(st.nextToken());

			int result = bfs();
			if(result == -1) {
				System.out.println("Impossible");
				continue;
			}
			System.out.println(result);
		}
	}

	private static int bfs() {
		Queue<Integer> queue = new LinkedList<>();
		int[] visited = new int[10000];

		queue.offer(start);
		visited[start] = 1;

		while(!queue.isEmpty()) {
			int now = queue.poll();
			if(now == target)
				return visited[now]-1;

			char[] nowStr = Integer.toString(now).toCharArray();

			for(int i=0; i<4; i++) {
				int[] digit = new int[4];
				for(int j=0; j<4; j++)
					digit[j] = Character.getNumericValue(nowStr[j]);

				for(int j=0; j<10; j++) {
					if(i==0 && j==0) continue;
					if(digit[i] == j) continue;

					digit[i] = j;
					int next = digit[0] * 1000 + digit[1] * 100 + digit[2] * 10 + digit[3];

					if(visited[next]==0 && isPrime(next)) {
						visited[next] = visited[now]+1;
						queue.offer(next);
					}
				}
			}
		}
		return -1;
	}

	private static boolean isPrime(int n) {
		for(int i=2; i<=Math.sqrt(n); i++) {
			if(n%i == 0)
				return false;
		}
		return true;
	}
}