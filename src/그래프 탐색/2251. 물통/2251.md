import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Status {
	int a;
	int b;
	int c;

	Status(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
}

class Main {
	static Status capacity;
	static Queue<Status> queue;
	static boolean[][][] visited;
	static boolean[] cwater;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] capa = new int[3];

		for(int i=0; i<3; i++)
			capa[i] = Integer.parseInt(st.nextToken());

		capacity = new Status(capa[0], capa[1], capa[2]);

		bfs();

		for(int i=0; i<capacity.c+1; i++) {
			if(cwater[i])
				System.out.print(i + " ");
		}
	}

	private static void bfs() {
		queue = new LinkedList<>();
		visited = new boolean[capacity.a+1][capacity.b+1][capacity.c+1];
		cwater = new boolean[capacity.c+1];

		queue.offer(new Status(0, 0, capacity.c));
		visited[0][0][capacity.c] = true;

		while(!queue.isEmpty()) {
			Status status = queue.poll();
			if(status.a == 0)
				cwater[status.c] = true;
			offerQueue(status);
		}
	}

	private static void offerQueue(Status status) {
		int aSparse = capacity.a - status.a;
		int bSparse = capacity.b - status.b;
		int cSparse = capacity.c - status.c;

		if(status.a <= bSparse) {
			if(!visited[0][status.a+status.b][status.c]) {
				visited[0][status.a+status.b][status.c] = true;
				queue.offer(new Status(0, status.a+status.b, status.c));
			}
		}
		else {
			if(!visited[status.a-bSparse][capacity.b][status.c]) {
				visited[status.a-bSparse][capacity.b][status.c] = true;
				queue.offer(new Status(status.a-bSparse, capacity.b, status.c));
			}
		}

		if(status.a <= cSparse) {
			if(!visited[0][status.b][status.a+status.c]) {
				visited[0][status.b][status.a+status.c] = true;
				queue.offer(new Status(0, status.b, status.a+status.c));
			}
		}
		else {
			if(!visited[status.a-cSparse][status.b][capacity.c]) {
				visited[status.a-cSparse][status.b][capacity.c] = true;
				queue.offer(new Status(status.a-cSparse, status.b, capacity.c));
			}
		}

		if(status.b <= aSparse) {
			if(!visited[status.a+status.b][0][status.c]) {
				visited[status.a+status.b][0][status.c] = true;
				queue.offer(new Status(status.a+status.b, 0, status.c));
			}
		}
		else {
			if(!visited[capacity.a][status.b-aSparse][status.c]) {
				visited[capacity.a][status.b-aSparse][status.c] = true;
				queue.offer(new Status(capacity.a, status.b-aSparse, status.c));
			}
		}

		if(status.b <= cSparse) {
			if(!visited[status.a][0][status.b+status.c]) {
				visited[status.a][0][status.b+status.c] = true;
				queue.offer(new Status(status.a, 0, status.b+status.c));
			}
		}
		else {
			if(!visited[status.a][status.b-cSparse][capacity.c]) {
				visited[status.a][status.b-cSparse][capacity.c] = true;
				queue.offer(new Status(status.a, status.b-cSparse, capacity.c));
			}
		}

		if(status.c <= aSparse) {
			if(!visited[status.a+status.c][status.b][0]) {
				visited[status.a+status.c][status.b][0] = true;
				queue.offer(new Status(status.a+status.c, status.b, 0));
			}
		}
		else {
			if(!visited[capacity.a][status.b][status.c-aSparse]) {
				visited[capacity.a][status.b][status.c-aSparse] = true;
				queue.offer(new Status(capacity.a, status.b, status.c-aSparse));
			}
		}

		if(status.c <= bSparse) {
			if(!visited[status.a][status.b+status.c][0]) {
				visited[status.a][status.b+status.c][0] = true;
				queue.offer(new Status(status.a, status.b+status.c, 0));
			}
		}
		else {
			if(!visited[status.a][capacity.b][status.c-bSparse]) {
				visited[status.a][capacity.b][status.c-bSparse] = true;
				queue.offer(new Status(status.a, capacity.b, status.c-bSparse));
			}
		}
	}
}