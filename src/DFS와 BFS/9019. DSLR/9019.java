import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());

		for(int i=0; i<tc; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int target = Integer.parseInt(st.nextToken());
			System.out.println(bfs(start, target));
		}
	}

	private static String bfs(int start, int target) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[10000];
		String[] instruction = new String[10000];
		char[] oper = new char[]{'D','S','L','R'};

		queue.offer(start);
		visited[start] = true;
		instruction[start] = "";

		while(!queue.isEmpty()) {
			int now = queue.poll();

			for(int i=0; i<4; i++) {
				int next = operation(i, now);
				instruction[next] = instruction[now] + oper[i];

				if(visited[next]) continue;

				if(next == target)
					return instruction[next];

				queue.offer(next);
				visited[next] = true;
			}
		}
		return "";
	}

	private static int operation(int i, int num) {
		switch(i) {
			case 0:
				return D(num);
			case 1:
				return S(num);
			case 2:
				return L(num);
			case 3:
				return R(num);
		}
		return 0;
	}

	private static int D(int n) {
		return n * 2 % 10000;
	}

	private static int S(int n) {
		return n==0 ? 9999 : n-1;
	}

	private static int L(int n) {
		return (n % 1000) * 10 + n/1000;
	}

	private static int R(int n) {
		return (n % 10) * 1000 + n/10;
	}
}