import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
	static String str0;
	static String str1;
	static int[][] cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str0 = br.readLine();
		str1 = br.readLine();
		cnt = new int[str0.length()+1][str1.length()+1];
		
		for(int i=1; i<=str0.length(); i++) {
			for(int j=1; j<=str1.length(); j++) {
				if(str0.charAt(i-1) == str1.charAt(j-1)) 
					cnt[i][j] = cnt[i-1][j-1] + 1;
				else 
					cnt[i][j] = Math.max(cnt[i-1][j], cnt[i][j-1]);
			}
		}
		
		System.out.println(cnt[str0.length()][str1.length()]);
	}
}
