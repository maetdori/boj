import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Square {
	int durability;
	boolean occupied;

	Square(int dur, boolean occ) {
		this.durability = dur;
		this.occupied = occ;
	}
}

class Main {
	static int N;
	static int K;
	static int zero = 0;
	static LinkedList<Square> conveyorBelt = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<2*N; i++) {
			int durability = Integer.parseInt(st.nextToken());
			conveyorBelt.offer(new Square(durability, false));
		}

		System.out.println(execute());
	}

	public static int execute() {
		int cnt = 0;

		while(zero < K) {
			cnt++;
			rotate();
			moveRobot();
			loadRobot();
		}
		return cnt;
	}

	public static void rotate() {
		Square unload = conveyorBelt.get(N-1);
		unload.occupied = false;
		Square last = conveyorBelt.pollLast();
		conveyorBelt.offerFirst(last);
		unload = conveyorBelt.get(N-1);
		unload.occupied = false;
	}

	public static void moveRobot() {
		for(int i=N-2; i>=0; i--) {
			Square cur = conveyorBelt.get(i);
			Square next = conveyorBelt.get(i+1);
			if(!cur.occupied || next.occupied || next.durability < 1) continue;
			cur.occupied = false;
			next.occupied = true;
			if(--next.durability == 0) zero++;
		}
	}

	public static void loadRobot() {
		Square load = conveyorBelt.peekFirst();
		if(load.durability == 0) return;
		load.occupied = true;
		if(--load.durability == 0) zero++;
	}
}