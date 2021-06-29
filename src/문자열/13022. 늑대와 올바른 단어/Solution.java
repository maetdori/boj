import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();

		if(isValid(str)) System.out.println(1);
		else System.out.println(0);
	}

	private static boolean isValid(String str) {
		char[] seq = new char[] {'w', 'o', 'l', 'f'};
		int idx = 3;
		int n = 0;
		int cnt = 0;

		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i) != seq[idx]) {

				if(idx == 0)
					n = cnt;

				if(str.charAt(i) != seq[(idx+1) % 4] || n != cnt)
					return false;

				idx = (idx+1) % 4;
				cnt = 1;
			}
			cnt++;
		}

		if(idx != 3) return false;
		if(n != cnt) return false;

		return true;
	}
}