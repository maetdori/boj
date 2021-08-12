import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int L;
	static int C;
	static String[] candidate;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		candidate = br.readLine().split(" ");

		Arrays.sort(candidate);
		makeCode(0,0,0,0,new StringBuilder());
	}

	private static void makeCode(int depth, int start, int consonant, int vowel, StringBuilder code) {
		if(depth == L) {
			if(consonant < 2 || vowel < 1) return;
			System.out.println(code);
			return;
		}

		for(int i=start; i<C; i++) {
			code.append(candidate[i]);
			if(isVowel(candidate[i].charAt(0)))
				makeCode(depth+1, i+1, consonant, vowel+1, code);
			else makeCode(depth+1, i+1, consonant+1, vowel, code);
			code.deleteCharAt(code.length()-1);
		}
	}

	private static boolean isVowel(char ch) {
		if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u')
			return true;
		return false;
	}
}
