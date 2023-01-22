import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	private static int[][] matrix;
	private static boolean[][] visited;
	private static int row;
	private static int col;

	private static int[] rMove = {-1, 1, 0, 0};
	private static int[] cMove = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		col = Integer.parseInt(st.nextToken());
		row = Integer.parseInt(st.nextToken());

		matrix = new int[row][col];
		visited = new boolean[row][col];

		for (int r = 0; r < row; r++) {
			String[] tmp = br.readLine().split("");
			for (int c = 0; c < col; c++) {
				matrix[r][c] = Integer.parseInt(tmp[c]);
			}
		}

		System.out.println(tryWithBfs());
	}

	private static int tryWithBfs() {
		PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.cnt));
		queue.offer(new Node(0, 0, 0));

		while (!queue.isEmpty()) {
			Node node = queue.poll();
			int r = node.row;
			int c = node.col;
			int cnt = node.cnt;

			if (r == row - 1 && c == col - 1) {
				return cnt;
			}

			for (int i = 0; i < 4; i ++) {
				int nr = r + rMove[i];
				int nc = c + cMove[i];

				if (nr < 0 || nc < 0 || nr >= row || nc >= col) {
					continue;
				}

				if (!visited[nr][nc]) {
					visited[nr][nc] = true;
					queue.offer(new Node(nr, nc, matrix[nr][nc] == 0 ? cnt : cnt + 1));
				}
			}
		}
		return 0;
	}
}

class Node {
	int row;
	int col;
	int cnt;

	Node(int row, int col, int cnt) {
		this.row = row;
		this.col = col;
		this.cnt = cnt;
	}
}
