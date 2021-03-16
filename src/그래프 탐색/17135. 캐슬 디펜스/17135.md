import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Node {
	int row;
	int col;

	Node(int row, int col) {
		this.row = row;
		this.col = col;
	}
}

class Main {
	static int R;
	static int C;
	static int D;
	static int[][] map;
	static int maxKill = 0;
	static Node[] hunter = new Node[3];
	static List<Node> enemy = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[R][C];

		for(int r=0; r<R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 1)
					enemy.add(new Node(r,c));
			}
		}

		placeHunter(0,0);
		System.out.println(maxKill);
	}

	static void placeHunter(int start, int depth) {
		if(depth == 3) {
			maxKill = Math.max(maxKill, startGame());
			return;
		}

		for(int i=start; i<C; i++) {
			hunter[depth] = new Node(R, i);
			placeHunter(i+1, depth+1);
		}
	}

	static int startGame() {
		int killed = 0;
		List<Node> left = new ArrayList<>();

		for(Node node: enemy)
			left.add(new Node(node.row,node.col));

		while(left.size() != 0) {
			List<Node> kill = new ArrayList<>();
			for (int i = 0; i < 3; i++) {
				Node target = findNearest(left, hunter[i].col);
				if(target.col == C) continue;
				kill.add(target);
			}

			for(int i=0; i<kill.size(); i++) {
				Node target = kill.get(i);
				for(int j=0; j<left.size(); j++) {
					Node node = left.get(j);
					if(node.row == target.row && node.col == target.col) {
						left.remove(j);
						killed++;
						j--;
					}
				}
			}

			for(int i=0; i<left.size(); i++) {
				Node node = left.get(i);
				if(node.row+1 == R) {
					left.remove(i);
					i--;
					continue;
				}
				node.row += 1;
			}
		}
		return killed;
	}

	static Node findNearest(List<Node> left, int c) {
		Node nearest = new Node(0,C);
		int nearestDist = R+C;

		Collections.sort(left, Comparator.comparingInt((Node n) -> n.col));
		for(Node node: left) {
			int dist = R-node.row + Math.abs(c-node.col);
			if(dist > D) continue;
			if(dist < nearestDist) {
				nearest = node;
				nearestDist = dist;
			}
		}
		return nearest;
	}
}