import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Stage {
	public Node red;
	public Node blue;
	public int cnt;

	Stage(Node red, Node blue, int cnt) {
		this.red = red;
		this.blue = blue;
		this.cnt = cnt;
	}
}

class Node {
	public int row;
	public int col;

	Node(int row, int col) {
		this.row = row;
		this.col = col;
	}

	Node() {

	}
}

class Main {
	static char[][] matrix;
	static boolean[][][][] visited;
	static int R;
	static int C;

	public static void main(String[] args) throws IOException {
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(buf.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		matrix = new char[R][C];
		visited = new boolean[R][C][R][C];

		Node red = new Node();
		Node blue = new Node();

		for(int r=0; r<R; r++) {
			matrix[r] = buf.readLine().toCharArray();
			for(int c=0; c<C; c++) {
				if(matrix[r][c] == 'R')
					red = new Node(r,c);
				if(matrix[r][c] == 'B')
					blue = new Node(r,c);
			}
		}

		Stage now = new Stage(red, blue, 0);
		System.out.println(bfs(now));
	}

	private static int bfs(Stage start) {
		Queue<Stage> queue = new LinkedList<>();

		queue.offer(start);
		visited[start.red.row][start.red.col][start.blue.row][start.blue.col] = true;

		while(!queue.isEmpty()) {
			Stage stage = queue.poll();
			Node red = stage.red;
			Node blue = stage.blue;
			int cnt = stage.cnt;

			if(cnt>=10) return -1;

			for(int i=0; i<4; i++) {
				Node nextR = roll(red, i);
				Node nextB = roll(blue, i);

				if(matrix[nextB.row][nextB.col] == 'O')
					continue;

				if(matrix[nextR.row][nextR.col] == 'O')
					return cnt+1;

				if(nextR.row==nextB.row && nextR.col==nextB.col) {
					switch(i) {
						case 0: //상
							if(red.row < blue.row)
								nextB.row++;
							else
								nextR.row++;
							break;

						case 1: //하
							if(red.row < blue.row)
								nextR.row--;
							else
								nextB.row--;
							break;

						case 2: //좌
							if(red.col < blue.col)
								nextB.col++;
							else
								nextR.col++;
							break;

						case 3: //우
							if(red.col < blue.col)
								nextR.col--;
							else
								nextB.col--;
							break;
					}
				}

				if(!visited[nextR.row][nextR.col][nextB.row][nextB.col]) {
					visited[nextR.row][nextR.col][nextB.row][nextB.col] = true;
					queue.offer(new Stage(nextR, nextB, cnt+1));
				}
			}
		}
		return -1;
	}

	private static Node roll(Node marble, int dir) {
		int[] rMove = new int[]{-1,1,0,0}; //상,하,좌,우
		int[] cMove = new int[]{0,0,-1,1};
		int r = marble.row;
		int c = marble.col;

		while(r>0 && c>0 && r<R-1 && c<C-1 && matrix[r+rMove[dir]][c+cMove[dir]] != '#') {
			r += rMove[dir];
			c += cMove[dir];

			if(matrix[r][c] == 'O')
				break;
		}

		return new Node(r,c);
	}
}