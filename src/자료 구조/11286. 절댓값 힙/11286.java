import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Math.abs(o1) == Math.abs(o2) ? o1-o2 : Math.abs(o1)-Math.abs(o2));

		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());

			if(num==0) {
				if (pq.isEmpty()) {
					System.out.println(0);
					continue;
				}
				System.out.println(pq.poll());
				continue;
			}
			
			pq.offer(num);
		}
	}
}
