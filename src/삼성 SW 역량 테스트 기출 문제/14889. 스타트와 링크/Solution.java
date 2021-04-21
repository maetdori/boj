import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int N;
	static int[][] stat;
	static boolean[] visited;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		stat = new int[N][N];
		visited = new boolean[1<<N];
		
		for(int r=0; r<N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				stat[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		makeTeam(0, 0, 0);
	
		System.out.println(min);
	}
	
	private static void makeTeam(int depth, int idx, int member) {
		if(depth == N/2) {
			int start = member;
			int link = member ^ ((1<<N)-1);
			
			if(visited[start] || visited[link]) return;
			
			visited[start] = true;
			visited[link] = true;
			
			int startStat = calculate(start);
			int linkStat = calculate(link);
			
			min = Math.min(min, Math.abs(startStat-linkStat));
			
			return;
		}
		
		for(int i=idx; i<N; i++) {
			makeTeam(depth+1, i+1, member|(1<<i));
		}
	}
	
	private static int calculate(int member) {
		int sum = 0;
		
		for(int i=0; i<N; i++) {
			if((member & (1<<i)) == 0) continue;
			for(int j=i+1; j<N; j++) {
				if((member & (1<<j)) == 0) continue;
				sum += stat[i][j];
				sum += stat[j][i];
			}
		}
		
		return sum;
	}
}