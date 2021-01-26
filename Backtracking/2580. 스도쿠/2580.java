import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Main {
	static int[][] sudoku = new int[9][9];
	static ArrayList<int[]> zeroList = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=0; i<9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) {
				sudoku[i][j] = Integer.parseInt(st.nextToken());
				if(sudoku[i][j] == 0) {
					zeroList.add(new int[] {i,j});
				}
			}
		}
		dfs(0);
	}
	
	public static boolean check(int row, int col, int input) {
		for(int i=0; i<9; i++) {
			//가로 검사
			if(sudoku[row][i] == input) 
				return false;
			//세로 검사
			if(sudoku[i][col] == input) 
				return false;
		}
		
		//박스 검사
		int r = (row/3)*3;
		int c = (col/3)*3;
		for(int i=r; i<r+3; i++) {
			for(int j=c; j<c+3; j++) {
				if(sudoku[i][j] == input)
					return false;
			}
		}
		
		return true;
	}
	
	public static void dfs(int depth) {
		if(depth == zeroList.size()) {
			for(int i=0; i<9; i++) {
				for(int j=0; j<9; j++) {
					sb.append(sudoku[i][j] + " ");
				}
				sb.append("\n");
			}
			System.out.println(sb);
			System.exit(0);
		}
		
		int row = zeroList.get(depth)[0];
		int col = zeroList.get(depth)[1];
		
		for(int i=1; i<10; i++) {
			if(check(row, col, i)) {
				sudoku[row][col] = i;
				dfs(depth+1);
				sudoku[row][col] = 0;
			}
		}
	}
}