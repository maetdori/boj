import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] weight = new int[N];
		int[] height = new int[N];
		int[] rank = new int[N];
		int cnt;
		
		for(int i=0; i<N; i++) {
			weight[i] = sc.nextInt();
			height[i] = sc.nextInt();
		}
		
		for(int i=0; i<N; i++) {
			cnt = 1;
			for(int j=0; j<N; j++) {
				if(weight[i]<weight[j] && height[i]<height[j])
					cnt++;
			}
			rank[i] = cnt;
		}
		
		for(int i=0; i<N; i++) {
			System.out.print(rank[i] + " ");
		}
	}
}
