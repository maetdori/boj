import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int N;
	static int K;
	static int max = 0;
	static String[] words;
	static boolean[] learned = new boolean['z'+1];
	static List<Character> unknown;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()) - 5;
		words = new String[N];

		if(K < 0) {
			System.out.println(0);
			return;
		}

		for(int i=0; i<N; i++) {
			String str = br.readLine();
			words[i] = str.substring(4, str.length()-4);
		}

		learned['a'] = true;
		learned['n'] = true;
		learned['t'] = true;
		learned['i'] = true;
		learned['c'] = true;

		getUnknownChar();
		teachWords(0,0);
		System.out.println(max);
	}

	private static void teachWords(int depth, int start) {
		if(depth == Math.min(K, unknown.size())) { //K보다 unknown.size()가 작을 수 있다. 
			int num = 0;
			for(int i=0; i<N; i++) {
				if(canRead(words[i])) num++;
			}
			max = Math.max(max,num);
			return;
		}

		for(int i=start; i<unknown.size(); i++) {
			char ch = unknown.get(i);
			if(learned[ch]) continue;
			learned[ch] = true;
			teachWords(depth+1, i+1);
			learned[ch] = false;
		}
	}

	private static void getUnknownChar() {
		Set<Character> set = new HashSet<>();
		for(int i=0; i<N; i++) {
			for(char ch: words[i].toCharArray()) {
				if(!learned[ch]) set.add(ch);
			}
		}
		unknown = new ArrayList<>(set);
	}

	private static boolean canRead(String str) {
		for(char ch: str.toCharArray()) {
			if(!learned[ch]) return false;
		}
		return true;
	}
}