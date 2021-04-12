import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int S = 0;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int line = Integer.parseInt(br.readLine());

		for(int i=0; i<line; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			int elm;
			if(st.hasMoreTokens())
				elm = Integer.parseInt(st.nextToken());
			else elm = 0;
			operate(cmd, elm);
		}

		System.out.println(sb.toString());
	}

	private static void operate(String cmd, int elm) {
		switch(cmd) {
			case "add":
				add(1 << (elm-1));
				break;
			case "remove":
				remove(~(1 << (elm-1)));
				break;
			case "check":
				check(1 << (elm-1));
				break;
			case "toggle":
				toggle(1 << (elm-1));
				break;
			case "all":
				all();
				break;
			case "empty":
				empty();
				break;
		}
	}

	private static void add(int elm) {
		S |= elm;
	}

	private static void remove(int elm) {
		S &= elm;
	}

	private static void check(int elm) {
		sb.append(((S & elm) == elm) ? "1\n" : "0\n");
	}

	private static void toggle(int elm) {
		S ^= elm;
	}

	private static void all() {
		S |= ~0;
	}

	private static void empty() {
		S = 0;
	}
}