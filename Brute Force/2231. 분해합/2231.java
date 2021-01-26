import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int start = N/2;
		int result = 0;
		
		if(N==1) { 
			System.out.println("0");
			return;
		}
	
		while(true) {
			
			if(start >= N) {
				result = 0;
				break;
			}
			
			int sum = 0;
			int temp = start;
			for(int i=1000000; i>0; i/=10) {
				sum += temp/i;
				temp = temp%i;
			}
			sum += start;
			
			if(sum == N) {
				result = start;
				break;
			}
			else
				start++;
		}
		
		System.out.println(result);
	}
}