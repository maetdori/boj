import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int[][][] matrix;
	static int row;
	static int col;
	static int height;
	static int[] moveR = {0,0,0,0,-1,1};
	static int[] moveC = {0,0,-1,1,0,0};
	static int[] moveH = {-1,1,0,0,0,0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		col = Integer.parseInt(st.nextToken());
		row = Integer.parseInt(st.nextToken());
		height = Integer.parseInt(st.nextToken());

		matrix = new int[row][col][height];

		for(int h=0; h<height; h++) {
			for(int r=0; r<row; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<col; c++) {
					matrix[r][c][h] = Integer.parseInt(st.nextToken());
				}
			}
		}
		bfs();
	}

	private static void bfs() {
		Queue<int[]> queue = new LinkedList<>();
		int day = 0;

		for(int h=0; h<height; h++) {
			for(int r=0; r<row; r++) {
				for(int c=0; c<col; c++) {
					if(matrix[r][c][h]==1)
						queue.offer(new int[]{r,c,h,0});
				}
			}
		}

		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();

			for(int i=0; i<6; i++) {
				int r = tmp[0] + moveR[i];
				int c = tmp[1] + moveC[i];
				int h = tmp[2] + moveH[i];
				day = tmp[3];

				if(r>=0 && r<row && c>=0 && c<col && h>=0 && h<height && matrix[r][c][h]==0) {
					queue.offer(new int[]{r,c,h,day+1});
					matrix[r][c][h] = 1;
				}
			}
		}

		for(int h=0; h<height; h++) {
			for(int r=0; r<row; r++) {
				for(int c=0; c<col; c++) {
					if(matrix[r][c][h]==0) {
						System.out.println(-1);
						return;
					}
				}
			}
		}
		System.out.println(day);
	}
}
