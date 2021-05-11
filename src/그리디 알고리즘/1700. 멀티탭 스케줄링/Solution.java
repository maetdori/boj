import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	static int N;
	static int K;
	static int[] sequence;
	static List<Integer> inUse = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		sequence = new int[K];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K ;i++) {
			sequence[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(getOptimal());
	}

	private static int getOptimal() {
		int cnt = 0;
		for(int i=0; i<K; i++) {
			int cur = sequence[i];
			if(inUse.contains(cur)) continue;
			if(inUse.size() == N) {
				int targetIdx = -1;
				List<Integer> inQueue = new ArrayList<>();
				for(int j=i+1; j<K; j++) {
					int next = sequence[j];
					if(inQueue.size() == N) break;
					if(!inUse.contains(next)) continue;
					if(!inQueue.contains(next))
						inQueue.add(next);
				}
				if(inQueue.size() == N) {
					int last = inQueue.get(inQueue.size()-1);
					targetIdx = inUse.indexOf(last);
				}
				else {
					for(int j=0; j<N; j++) {
						if(inQueue.contains(inUse.get(j))) continue;
						targetIdx = j;
						break;
					}
				}
				cnt++;
				inUse.remove(targetIdx);
			}
			inUse.add(cur);
		}
		return cnt;
	}
}