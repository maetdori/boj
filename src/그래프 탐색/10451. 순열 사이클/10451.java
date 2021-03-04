import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());

		for(int i=0; i<tc; i++) {
			int len = Integer.parseInt(br.readLine()) + 1;
			int[] perm = new int[len];
			StringTokenizer st = new StringTokenizer(br.readLine());

			for(int j=1; j<len; j++) {
				perm[j] = Integer.parseInt(st.nextToken());
			}

			int cycle = 0;
			boolean[] visited = new boolean[len];
			for(int j=1; j<len; j++) {
				if(visited[j]) continue;

				cycle++;
				while(!visited[j]) {
					visited[j] = true;
					j = perm[j];
				}
			}
			System.out.println(cycle);
		}
	}
}