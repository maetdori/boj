import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Node {
	public int r;
	public int c;

	Node(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

class Main {
	static int n;
	static int[][] map;
	static List<Node> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		map = new int[n][n];

		for(int r=0; r<n; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int c=0; c<n; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				list.add(new Node(r,c));
			}
		}

		Collections.sort(list, (n1, n2) -> map[n2.r][n2.c] - map[n1.r][n1.c]);
		System.out.println(dp());
	}

	private static int dp() {
		int max = 0;
		int[][] memo = new int[n][n];
		int[] rMove = new int[]{-1,1,0,0};
		int[] cMove = new int[]{0,0,-1,1};

		for(Node node: list) {
			int r = node.r;
			int c = node.c;

			for(int i=0; i<4; i++) {
				int nr = r + rMove[i];
				int nc = c + cMove[i];

				if(nr<0 || nc<0 || nr>=n || nc>=n)
					continue;

				if(map[r][c] != map[nr][nc])
					memo[r][c] = Math.max(memo[r][c], memo[nr][nc]+1);
			}

			if(memo[r][c] == 0)
				memo[r][c] = 1;

			max = Math.max(max,memo[r][c]);
		}
		return max;
	}
}