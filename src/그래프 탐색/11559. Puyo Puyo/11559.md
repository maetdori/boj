import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static List<Character> puyo[];
	static int cnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		puyo = new ArrayList[6];

		for(int i=0; i<6; i++)
			puyo[i] = new ArrayList<>();

		for(int i=0; i<12; i++) {
			char[] ch = br.readLine().toCharArray();
			for(int j=0; j<6; j++) {
				puyo[j].add(ch[j]);
			}
		}

		for(int i=0; i<6; i++)
			Collections.reverse(puyo[i]);

		while(pang() != -1)
			cnt++;
		System.out.println(cnt);
	}

	private static int pang() {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[12][6];
		int[] rMove = new int[]{-1,1,0,0};
		int[] cMove = new int[]{0,0,-1,1};

		for(int c=0; c<6; c++) {
			for(int r=0; r<puyo[c].size(); r++) {
				if (!visited[r][c] && puyo[c].get(r) != '.') {
					queue.offer(new int[]{r, c});
					visited[r][c] = true;
					Queue<int[]> candidate = new LinkedList<>();

					while(!queue.isEmpty()) {
						int[] node = queue.poll();
						int row = node[0];
						int col = node[1];
						char ch = puyo[col].get(row);
						candidate.offer(node);

						for (int i = 0; i < 4; i++) {
							int nr = row + rMove[i];
							int nc = col + cMove[i];

							if (nr < 0 || nc < 0 || nc >= 6 || nr >= puyo[nc].size()) continue;

							if (!visited[nr][nc] && puyo[nc].get(nr) == ch) {
								queue.offer(new int[]{nr, nc});
								visited[nr][nc] = true;
							}
						}
					}
					if (candidate.size() >= 4) {
						while (!candidate.isEmpty()) {
							int[] node = candidate.poll();
							int row = node[0];
							int col = node[1];
							puyo[col].set(row, '0');
						}
					}
				}
			}
		}

		int flag = 0;
		for(int c=0; c<6; c++) {
			for(int r=0; r<puyo[c].size(); r++) {
				if(puyo[c].get(r) == '0') {
					puyo[c].remove(r);
					flag = 1;
					r--;
				}
			}
		}

		if(flag == 0) return -1;
		return 0;
	}
}