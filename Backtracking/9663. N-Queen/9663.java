import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {
	
	static int N;
	static int[] row;
	static int count;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		row = new int[N];
		count = 0;
		
		nqueen(0);
		
		System.out.println(count);
	}
	
	public static boolean possibility(int depth) {
		
		for(int i=0; i<depth; i++) {
			if(row[i] == row[depth])
				return false;
			else if(Math.abs(i-depth) == Math.abs(row[i]-row[depth]))
				return false;
		}
		return true;
	}
	
	public static void nqueen(int depth) {
		
		if(depth == N) {
			count++;
			return;
		}
		
		for(int i=0; i<N; i++) {
			row[depth] = i;
			if(possibility(depth)) 
				nqueen(depth+1);
		}
	}
}