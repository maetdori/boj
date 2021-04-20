import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int N;
	static int[] num;
	static int[] oper; //{0,1,1,2,3,3,3} == {덧셈,뺄셈,뺄셈,곱셈,나눗셈,나눗셈,나눗셈};
	static int[] seq;
	static boolean[] visited;
	static boolean[][] checked;
	static long max = -Long.MAX_VALUE;
	static long min = Long.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		num = new int[N];
		oper = new int[N-1];
		seq = new int[N-1];
		visited = new boolean[N-1];
		checked = new boolean[N-1][4];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int idx = 0;
		for(int i=0; i<4; i++) {
			int n = Integer.parseInt(st.nextToken());
			for(int j=0; j<n; j++) {
				oper[idx++] = i;
			}
		}
		
		dfs(0);
		System.out.println(max);
		System.out.println(min);
	}
	
	private static void dfs(int depth) {
		if(depth == N-1) {
			max = Math.max(max, calculate());
			min = Math.min(min, calculate());
			return;
		}
		
		for(int i=0; i<N-1; i++) {
			int operator = oper[i];
			if(visited[i] || checked[depth][operator]) continue;
			visited[i] = true;
			checked[depth][operator] = true;
			seq[depth] = operator;
			dfs(depth+1);
			visited[i] = false;
			checked[depth][operator] = false;
		}
	}
	
	private static long calculate() {
		long tmp = num[0];
		
		for(int i=1; i<N; i++) {
			int op = seq[i-1];
			long n1 = tmp;
			long n2 = num[i];
			tmp = operate(op, n1, n2);
		}
		
		return tmp;
	}
	
	private static long operate(int i, long n1, long n2) {
		switch(i) {
		case 0:
			return n1 + n2;
		case 1: 
			return n1 - n2;
		case 2:
			return n1 * n2;
		case 3:
			return n1<0 ? -((-n1) / (n2)) : n1 / n2;
		}
		return 0;
	}
}
