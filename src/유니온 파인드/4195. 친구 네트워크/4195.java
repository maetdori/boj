import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static Map<String, Integer> map;
	static int[] parent;
	static int[] count;
	static int F;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());

		for(int t=0; t<tc; t++) {
			map = new HashMap<>();
			parent = new int[200000];
			count = new int[200000];
			F = Integer.parseInt(br.readLine());
			Arrays.fill(count, 1);

			for(int i=0; i<200000; i++) {
				parent[i] = i;
			}

			int idx = 0;
			StringTokenizer st;
			for(int i=0; i<F; i++) {
				st = new StringTokenizer(br.readLine());
				String f1 = st.nextToken();
				String f2 = st.nextToken();

				if(!map.containsKey(f1))
					map.put(f1, idx++);
				if(!map.containsKey(f2))
					map.put(f2, idx++);

				printFriendsNum(map.get(f1), map.get(f2));
			}
		}
	}

	private static void printFriendsNum(int id1, int id2) {
		System.out.println(union(id1, id2));
	}

	private static int union(int id1, int id2) {
		id1 = find(id1);
		id2 = find(id2);

		if(id1 == id2)
			return count[id1];

		if(count[id1] > count[id2]) {
			parent[id2] = id1;
			return count[id1] += count[id2];
		}

		parent[id1] = id2;
		return count[id2] += count[id1];
	}

	private static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
}