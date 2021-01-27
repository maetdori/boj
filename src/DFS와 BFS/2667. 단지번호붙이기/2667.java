import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int[][] matrix;
	static boolean[][] visited;
	static int size;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		size = Integer.parseInt(br.readLine());
		matrix = new int[size][size];
		visited = new boolean[size][size];

		for(int i=0; i<size; i++) {
			String[] line = br.readLine().split("");
			for (int j = 0; j < size; j++) {
				matrix[i][j] = Integer.parseInt(line[j]);
			}
		}

		List<Integer> village = new ArrayList<>();
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if(!visited[i][j] && matrix[i][j]==1) {
					cnt = 1;
					visited[i][j] = true;
					check(i, j);
					village.add(cnt);
				}
			}
		}
		Collections.sort(village);
		System.out.println(village.size());
		for(Integer v: village) {
			System.out.println(v);
		}
	}

	private static void check(int r, int c) {
		if(r>0 && !visited[r-1][c] && matrix[r-1][c]==1) { //상
			visited[r-1][c] = true;
			cnt++;
			check(r-1, c);
		}
		if(c>0 && !visited[r][c-1] && matrix[r][c-1]==1) { //좌
			visited[r][c-1] = true;
			cnt++;
			check(r, c-1);
		}
		if(r+1<size && !visited[r+1][c] && matrix[r+1][c]==1) { //하
			visited[r+1][c] = true;
			cnt++;
			check(r+1, c);
		}
		if(c+1<size && !visited[r][c+1] && matrix[r][c+1]==1) { //우
			visited[r][c+1] = true;
			cnt++;
			check(r, c+1);
		}
	}
}
