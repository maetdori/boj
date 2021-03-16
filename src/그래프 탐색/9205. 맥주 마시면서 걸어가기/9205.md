import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Pos {
	int x;
	int y;

	Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Main {
	static Pos home;
	static Pos dest;
	static Pos[] store;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());

		for(int t=0; t<tc; t++) {
			int storeNum = Integer.parseInt(br.readLine());
			store = new Pos[storeNum];
			StringTokenizer st = new StringTokenizer(br.readLine());
			home = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			for(int s=0; s<storeNum; s++) {
				st = new StringTokenizer(br.readLine());
				store[s] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			st = new StringTokenizer(br.readLine());
			dest = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			if(walk()) System.out.println("happy");
			else System.out.println("sad");
		}
	}

	static boolean walk() {
		Queue<Pos> queue = new LinkedList<>();
		boolean[] visited = new boolean[store.length];

		queue.offer(home);

		while(!queue.isEmpty()) {
			Pos now = queue.poll();
			int dist = Math.abs(dest.x - now.x) + Math.abs(dest.y - now.y);
			if(dist <= 1000) return true;

			for(int i=0; i<store.length; i++) {
				if(visited[i]) continue;
				Pos next = store[i];
				int storeDist = Math.abs(next.x - now.x) + Math.abs(next.y - now.y);
				if(storeDist > 1000) continue;
				queue.offer(next);
				visited[i] = true;
			}
		}
		return false;
	}

}